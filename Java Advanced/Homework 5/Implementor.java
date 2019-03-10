package ru.ifmo.rain.ibragimov.jarimpler;

import info.kgeorgiy.java.advanced.implementor.ImplerException;
import info.kgeorgiy.java.advanced.implementor.JarImpler;

import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

@SuppressWarnings("Duplicates")
public class Implementor implements JarImpler {

    private static final List<Integer> ARGS_LENGTHS = Arrays.asList(2, 3);

    private static final String JAVA = "java";
    private static final String CLASS_NAME_SUFFIX = "Impl";

    private static final String EMPTY = "";
    private static final String SPACE = " ";
    private static final String TAB = "\t";
    private static final String NEWLINE = System.lineSeparator();
    private static final String DOUBLE_NEWLINE = NEWLINE + NEWLINE;
    private static final String COLON = ";";
    private static final String CURLY_OPEN = "{";
    private static final String CURLY_CLOSE = "}";
    private static final String OPEN = "(";
    private static final String CLOSE = ")";
    private static final String COMMA = ",";
    private static final String DOT = ".";
    private static final String LESS = "<";
    private static final String GREATER = ">";
    private static final String TYPE_T = LESS + "T" + GREATER;

    private static final String PACKAGE = "package ";
    private static final String CLASS = "class ";
    private static final String IMPLEMENTS = "implements ";
    private static final String EXTENDS = "extends ";
    private static final String THROWS = "throws ";

    private static final String PUBLIC = "public ";
    private static final String PROTECTED = "protected ";

    private static final String RETURN = "return ";
    private static final String SUPER = "super";

    private static final String DEPRECATED = "@Deprecated" + NEWLINE;

    private String className;

    @SuppressWarnings("unchecked")
    private <T> T getDefaultValue(Class<T> clazz) {
        return (T) Array.get(Array.newInstance(clazz, 1), 0);
    }

    private String getPackageDeclaration(Class<?> clazz) {
        if (!clazz.getPackage().getName().equals(EMPTY)) {
            return PACKAGE + clazz.getPackageName() + COLON + DOUBLE_NEWLINE;
        }
        return EMPTY;
    }

    private String getTypeParameters(TypeVariable<?>[] typeParameters) {
        if (typeParameters.length == 0) return EMPTY;
        return String.format("<%s>", getJoinedStrings(typeParameters));
    }

    private String getClassDeclaration(Class<?> clazz) {
        var deriveKeyWord = clazz.isInterface() ? IMPLEMENTS : EXTENDS;
        var typeParameters = getTypeParameters(clazz.getTypeParameters());
        return PUBLIC + CLASS + className + typeParameters + SPACE + deriveKeyWord + clazz.getSimpleName() + typeParameters + SPACE + CURLY_OPEN + DOUBLE_NEWLINE;
    }


    private String getFileName(Class<?> token) {
        return token.getSimpleName().concat(CLASS_NAME_SUFFIX);
    }

    private Path getFilePath(Path path, Class<?> clazz, String extension) {
        return path.resolve(clazz.getPackage().getName().replace('.', File.separatorChar)).resolve(String.format("%s.%s", getFileName(clazz), extension.trim()));
    }

    private String getClassName(Class<?> clazz) {
        return clazz.getSimpleName() + CLASS_NAME_SUFFIX;
    }

    private void setClassName(Class<?> clazz) {
        this.className = getClassName(clazz);
    }

    private String getJoinedStrings(AnnotatedElement[] annotatedElements) {
        var joiner = new StringJoiner(COMMA + SPACE);
        Arrays.stream(annotatedElements).forEach(ae -> {
            var element = ae.toString();
            if (element.matches("class .+")) {
                element = element.split(" ")[1];
            }
            joiner.add(element.replaceAll("\\$", "\\."));
        });
        return joiner.toString();
    }

    private void createDirectories(Path path) throws ImplerException {
        if (path.getParent() != null) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new ImplerException(String.format("Unable to create directories for output file with path: %s", path.toString()), e);
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void deleteDirectory(Path path) throws IOException {
        Files.walk(path).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }

    private String getConstructorImplementation(Executable executable) {
        var args = new StringJoiner(COMMA + SPACE);
        Arrays.stream(executable.getParameters()).forEach(param -> args.add(param.getName()));
        return TAB + SUPER + OPEN + args.toString() + CLOSE + COLON;
    }

    private String getMethodImplementation(Class<?> returnType) {
        String defaultValueStr;
        if (returnType.equals(Void.TYPE)) {
            return EMPTY;
        }
        try {
            defaultValueStr = getDefaultValue(returnType).toString();
            if (defaultValueStr.charAt(0) == Character.MIN_VALUE) {
                defaultValueStr = "'\u0000'";
            } else if (defaultValueStr.equals("0.0")) {
                defaultValueStr += "f";
            }
        } catch (NullPointerException | IllegalArgumentException ignored) {
            defaultValueStr = "null";
        }
        return TAB + RETURN + defaultValueStr + COLON;
    }

    private void compile(Class<?> clazz, Path path) throws ImplerException {
        var compiler = ToolProvider.getSystemJavaCompiler();
        var joiner = new StringJoiner(File.pathSeparator);
        var pathsStream = Arrays.stream(System.getProperty("java.class.path").split(File.pathSeparator)).map(Paths::get);
        pathsStream.forEach(p -> Arrays.stream(Objects.requireNonNull(p.getParent().toFile().listFiles())).forEach(f -> joiner.add(Paths.get(f.toURI()).toAbsolutePath().toString())));
        var classPath = joiner.toString();
        String[] args = new String[]{ "-cp", path.toString() + File.pathSeparator + classPath, getFilePath(path, clazz, JAVA).toString() };
        if (compiler == null || compiler.run(null, null, null, args) != 0) {
            throw new ImplerException(String.format("Unable to compile %s.java", className));
        }
    }

    private void createJar(Class<?> clazz, Path path, Path sourcePath) throws ImplerException {
        var manifest = new Manifest();
        var attributes = manifest.getMainAttributes();
        attributes.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        try (var writer = new JarOutputStream(Files.newOutputStream(path), manifest)) {
            writer.putNextEntry(new ZipEntry(clazz.getName().replace('.', '/') + CLASS_NAME_SUFFIX + DOT + CLASS.trim()));
            Files.copy(getFilePath(sourcePath, clazz, CLASS), writer);
        } catch (IOException e) {
            throw new ImplerException("Unable to write to JAR file", e);
        }
    }

    @Override
    public void implementJar(Class<?> clazz, Path path) throws ImplerException {
        validateClass(clazz);
        Path sourcePath;
        try {
            sourcePath = Files.createTempDirectory(path.toAbsolutePath().getParent(), String.format("temp%d", System.currentTimeMillis()));
        } catch (IOException e) {
            throw new ImplerException("Can't create a temporary directory.", e);
        }
        try {
            implement(clazz, sourcePath);
            compile(clazz, sourcePath);
            createJar(clazz, path, sourcePath);
        } finally {
            try {
                deleteDirectory(sourcePath);
            } catch (IOException e) {
                System.err.println("Can't delete the temporary directory.");
            }
        }
    }

    private void validateClass(Class<?> clazz) throws ImplerException {
        if (clazz.isPrimitive() || clazz.isArray() || clazz.isEnum() || Modifier.isFinal(clazz.getModifiers()) || clazz == Enum.class) {
            throw new ImplerException(String.format("Invalid class with name: %s", clazz.getSimpleName()));
        }
    }

    private void init(Class<?> clazz, Path path) throws ImplerException {
        createDirectories(path);
        setClassName(clazz);
    }

    @Override
    public void implement(Class<?> clazz, Path path) throws ImplerException {
        validateClass(clazz);
        path = getFilePath(path, clazz, JAVA);
        init(clazz, path);
        try (var writer = Files.newBufferedWriter(path)) {
            generateHeader(clazz, writer);
            generateBody(clazz, writer);
            generateFooter(clazz, writer);
        } catch (IOException ignored) {
            throw new ImplerException(String.format("Can't write implementation of a following class: %s", clazz.getCanonicalName()));
        }
    }

    private void generateHeader(Class<?> clazz, BufferedWriter writer) throws ImplerException {
        try {
            writer.write(getPackageDeclaration(clazz));
            writer.write(getClassDeclaration(clazz));
        } catch (IOException ignored) {
            throw new ImplerException(String.format("Can't write implementation of a following class: %s", clazz.getCanonicalName()));
        }
    }

    private void generateBody(Class<?> clazz, BufferedWriter writer) throws ImplerException {
        if (!clazz.isInterface()) {
            generateConstructors(clazz, writer);
        }
        generateAbstractMethods(clazz, writer);
    }

    private void generateFooter(Class<?> clazz, BufferedWriter writer) throws ImplerException {
        try {
            writer.write(CURLY_CLOSE + DOUBLE_NEWLINE);
        } catch (IOException e) {
            throw new ImplerException(String.format("Can't write implementation of a following class: %s", clazz.getCanonicalName()));
        }
    }

    private void generateExecutable(Executable executable, BufferedWriter writer) throws ImplerException {
        var isMethod = executable instanceof Method;
        var accessModifierMask = executable.getModifiers() & (Modifier.PROTECTED | Modifier.PUBLIC);
        var accessModifier = EMPTY;
        if (accessModifierMask > 0) {
            accessModifier = Modifier.isPublic(accessModifierMask) ? PUBLIC : PROTECTED;
        }
        var returnType = EMPTY;
        var name = className;
        var implementation = getConstructorImplementation(executable);
        var isDeprecated = executable.isAnnotationPresent(Deprecated.class);
        if (isMethod) {
            name = executable.getName();
            implementation = getMethodImplementation(((Method) executable).getReturnType());
            returnType = getTypeParameters(executable.getTypeParameters()) + ((Method) executable).getGenericReturnType().getTypeName() + SPACE;
        }
        var args = getJoinedStrings(executable.getParameters());
        var exceptions = getJoinedStrings(executable.getExceptionTypes());
        if (exceptions.length() > 0) exceptions = THROWS + exceptions;
        try {
            if (isDeprecated) writer.write(DEPRECATED);
            writer.write(TAB + accessModifier + returnType + name + OPEN + args + CLOSE + SPACE + exceptions + SPACE + CURLY_OPEN + NEWLINE + TAB + implementation + NEWLINE + TAB);
        } catch (IOException ignored) {
            throw new ImplerException(String.format("Can't write implementation of a following method: %s", executable.getName()));
        }
        generateFooter(executable.getDeclaringClass(), writer);
    }

    private void generateConstructor(Constructor<?> constructor, BufferedWriter writer) throws ImplerException {
        generateExecutable(constructor, writer);
    }

    private void generateConstructors(Class<?> clazz, BufferedWriter writer) throws ImplerException {
        var constructors = Arrays.stream(clazz.getDeclaredConstructors()).filter(c -> !Modifier.isPrivate(c.getModifiers())).collect(Collectors.toList());
        if (constructors.isEmpty()) {
            throw new ImplerException(String.format("Class %s has no callable constructors", clazz.getSimpleName()));
        }
        for (var constructor : constructors) {
            generateConstructor(constructor, writer);
        }
    }

    private void generateAbstractMethod(Method method, BufferedWriter writer) throws ImplerException {
        generateExecutable(method, writer);
    }

    private void generateAbstractMethods(Class<?> clazz, BufferedWriter writer) {
        var methods = new HashSet<CustomMethod>();
        fill(methods, clazz);
        methods.stream().filter(m -> !m.isOverriden).forEach(m -> {
            try {
                generateAbstractMethod(m.instance, writer);
            } catch (ImplerException e) {
                e.printStackTrace();
            }
        });
    }

    private class CustomMethod {
        private Method instance;
        private boolean isOverriden;

        CustomMethod(Method m, boolean isOverriden) {
            instance = m;
            this.isOverriden = isOverriden;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj instanceof CustomMethod) {
                return obj.hashCode() == hashCode();
            }
            return false;
        }

        //bad hashcode, consider thinking of a better one, i am just too lazy
        @Override
        public int hashCode() {
            return Arrays.hashCode(instance.getParameterTypes()) + instance.getReturnType().hashCode() + instance.getName().hashCode();
        }
    }

    private void fill(Set<CustomMethod> methods, Class<?> clazz) {
        if (clazz == null) return;
        methods.addAll(Arrays.stream(clazz.getDeclaredMethods()).map(m -> new CustomMethod(m, Modifier.isFinal(m.getModifiers()) || !Modifier.isAbstract(m.getModifiers()))).collect(Collectors.toSet()));
        Arrays.stream(clazz.getInterfaces()).forEach(i -> fill(methods, i));
        fill(methods, clazz.getSuperclass());
    }

    public static void main(String[] args) {
        if (args == null || !ARGS_LENGTHS.contains(args.length) || args[0] == null || args[1] == null || args.length > 2 && args[2] == null) {
            System.err.println("Usage: Implementor [-jar] <Class name> <Target path>");
            return;
        }
        var isJar = args.length > 2;
        var offset = isJar ? 1 : 0;
        var classFullName = args[offset];
        var pathName = args[offset + 1];
        var implementor = new Implementor();
        try {
            var clazz = Class.forName(classFullName);
            var path = Paths.get(pathName);
            if (isJar) {
                implementor.implementJar(clazz, path);
            } else {
                implementor.implement(clazz, path);
            }
        } catch (ClassNotFoundException ignored) {
            System.err.println(String.format("Class with name %s not found", classFullName));
        } catch (ImplerException ignored) {
            System.err.println(String.format("Can't generate implementation of a class with full name: %s", classFullName));
        }
    }

}