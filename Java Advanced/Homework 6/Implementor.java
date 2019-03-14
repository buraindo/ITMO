package ru.ifmo.rain.ibragimov.docjarimpler;

import info.kgeorgiy.java.advanced.implementor.ImplerException;
import info.kgeorgiy.java.advanced.implementor.JarImpler;

import javax.tools.ToolProvider;
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

/**
 * @author Lev Dovzhik (lol ahahahahah типа списал ыыы рофл лмао)
 */
@SuppressWarnings("Duplicates")
public class Implementor implements JarImpler {

    /**
     * Probable count of arguments.
     */
    private static final List<Integer> ARGS_LENGTHS = Arrays.asList(2, 3);

    /**
     * File name extension for source file.
     */
    private static final String JAVA = "java";
    /**
     * Suffix of generated class name.
     */
    private static final String CLASS_NAME_SUFFIX = "Impl";

    /**
     * Empty string token.
     */
    private static final String EMPTY = "";
    /**
     * Space token.
     */
    private static final String SPACE = " ";
    /**
     * Tabulation token.
     */
    private static final String TAB = "\t";
    /**
     * New line token.
     */
    private static final String NEWLINE = System.lineSeparator();
    /**
     * Double new line token.
     */
    private static final String DOUBLE_NEWLINE = NEWLINE + NEWLINE;
    /**
     * Colon token.
     */
    private static final String COLON = ";";
    /**
     * Open curly bracket token.
     */
    private static final String CURLY_OPEN = "{";
    /**
     * Close curly bracket token.
     */
    private static final String CURLY_CLOSE = "}";
    /**
     * Open bracket token.
     */
    private static final String OPEN = "(";
    /**
     * Close bracket token.
     */
    private static final String CLOSE = ")";
    /**
     * Comma token.
     */
    private static final String COMMA = ",";
    /**
     * Dot token.
     */
    private static final String DOT = ".";

    /**
     * String representation of keyword <code>package</code>
     */
    private static final String PACKAGE = "package ";
    /**
     * String representation of keyword <code>class</code>
     */
    private static final String CLASS = "class ";
    /**
     * String representation of keyword <code>implements</code>
     */
    private static final String IMPLEMENTS = "implements ";
    /**
     * String representation of keyword <code>extends</code>
     */
    private static final String EXTENDS = "extends ";
    /**
     * String representation of keyword <code>throws</code>
     */
    private static final String THROWS = "throws ";

    /**
     * String representation of keyword <code>public</code>
     */
    private static final String PUBLIC = "public ";
    /**
     * String representation of keyword <code>protected</code>
     */
    private static final String PROTECTED = "protected ";

    /**
     * String representation of keyword <code>return</code>
     */
    private static final String RETURN = "return ";
    /**
     * String representation of keyword <code>super</code>
     */
    private static final String SUPER = "super";

    /**
     * String representation of annotation {@link Deprecated}
     */
    private static final String DEPRECATED = "@Deprecated" + NEWLINE;

    /**
     * String representation of generated class's {@link Class#getSimpleName()}
     */
    private String className;
    /**
     * Stores implementation of generated class
     */
    private StringBuilder classImplementation;

    /**
     * Returns default value of any type. For primitives except <code>void</code> and
     * <code>boolean</code> it is <code>0</code>, for classes it is <code>null</code>.
     *
     * @param clazz target type token
     * @param <T> type of default value
     * @return default value of target type <code>clazz</code>
     */
    @SuppressWarnings("unchecked")
    private <T> T getDefaultValue(Class<T> clazz) {
        return (T) Array.get(Array.newInstance(clazz, 1), 0);
    }

    /**
     * Return package declaration of the generated class in the following format:
     * <code>package a.b.c;</code> with two line breaks at the end.
     *
     * @param clazz target type token
     * @return package declaration with two line breaks at the end
     */
    private String getPackageDeclaration(Class<?> clazz) {
        if (!clazz.getPackage().getName().equals(EMPTY)) {
            return PACKAGE + clazz.getPackageName() + COLON + DOUBLE_NEWLINE;
        }
        return EMPTY;
    }

    /**
     * Returns all type parameters that were passed as arguments in formatted way.
     * Format: all type parameters are comma-separated and placed in <code>less</code> and
     * <code>greater</code> tokens.
     *
     * @param typeParameters array of type parameters
     * @return string of comma-separated type parameters placed in <code>&lt;</code> and <code>&gt;</code>
     */
    private String getTypeParameters(TypeVariable<?>[] typeParameters) {
        if (typeParameters.length == 0) return EMPTY;
        return String.format("<%s>", getJoinedStrings(typeParameters));
    }

    /**
     * Return class declaration of the generated class in the following format:
     * <code>class className;</code> with two line breaks at the end.
     *
     * @param clazz target type token
     * @return class declaration with two line breaks at the end
     */
    private String getClassDeclaration(Class<?> clazz) {
        var deriveKeyWord = clazz.isInterface() ? IMPLEMENTS : EXTENDS;
        var typeParameters = getTypeParameters(clazz.getTypeParameters());
        return PUBLIC + CLASS + className + typeParameters + SPACE + deriveKeyWord + clazz.getSimpleName() + typeParameters + SPACE + CURLY_OPEN + DOUBLE_NEWLINE;
    }

    /**
     * Returns full path to the file with target class implementation.
     *
     * @param path initial path
     * @param clazz target type token
     * @param extension extension of target source file, e.g. {@value #JAVA}
     * @return full path to the file with target class implementation
     */
    private Path getFilePath(Path path, Class<?> clazz, String extension) {
        return path.resolve(clazz.getPackage().getName().replace('.', File.separatorChar)).resolve(String.format("%s.%s", className, extension.trim()));
    }

    /**
     * Returns target class name. Obtained class name contains target type's simple name
     * and {@value #CLASS_NAME_SUFFIX}.
     * This method is only used to set field {@link #className}.
     *
     * @param clazz target type token
     * @return generated class's simple name
     */
    private String getClassName(Class<?> clazz) {
        return clazz.getSimpleName() + CLASS_NAME_SUFFIX;
    }

    /**
     * Sets field {@link #className} to actual class name of target generated class.
     * This method is only used to set field {@link #className}.
     *
     * @param clazz target type token
     */
    private void setClassName(Class<?> clazz) {
        this.className = getClassName(clazz);
    }

    /**
     * Returns comma-separated elements of {@link AnnotatedElement}.
     * This method is most commonly used to get arguments list as a string but can be easily
     * used for other purposes.
     *
     * @param annotatedElements list of {@link AnnotatedElement}
     * @return string representation of comma-separated arguments
     */
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

    /**
     * Creates a directory for output file if it doesn't already exist.
     *
     * @param path target path
     * @throws ImplerException if an {@link IOException} has occurred
     */
    private void createDirectories(Path path) throws ImplerException {
        if (path.getParent() != null) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new ImplerException(String.format("Unable to create directories for output file with path: %s", path.toString()), e);
            }
        }
    }

    /**
     * Deletes a directory denoted by the path provided.
     * This method is used to delete the temporary folder that contains only one file.
     * If we are to delete a directory with a lot of files, this is not a good way to do it.
     *
     * @param path target path
     * @throws IOException if an internal {@link IOException} has occurred
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void deleteDirectory(Path path) throws IOException {
        Files.walk(path).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }

    /**
     * Returns implementation of a constructor.
     * Implementation contains only call of the super constructor.
     *
     * @param executable target constructor
     * @return string representation of constructor's implementation
     */
    private String getConstructorImplementation(Executable executable) {
        var args = new StringJoiner(COMMA + SPACE);
        Arrays.stream(executable.getParameters()).forEach(param -> args.add(param.getName()));
        return TAB + SUPER + OPEN + args.toString() + CLOSE + COLON;
    }

    /**
     * Returns implementation of a method by a return type.
     * Implementation contains <code>false</code> for <code>boolean</code> type, {@link #EMPTY} for
     * <code>void</code> type, <code>0</code> for other primitives and <code>null</code> for
     * classes.
     *
     * @param returnType target return type
     * @return string representation of method's implementation
     */
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

    /**
     * Compiles a provided source file using system java compiler.
     * This method uses class path used when launching the program so make sure you specified
     * all the paths (including modules) in the <code>-classpath</code> flag.
     *
     * @param clazz target type token
     * @param path target source file
     * @throws ImplerException if compilation error has occurred when compiling target source file.
     */
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

    /**
     * Creates a <code>.jar</code> file.
     * Note, that the obtained file is not executable and contains only one <code>.class</code> file.
     *
     * @param clazz target type token
     * @param path target path for the output <code>jar</code> file
     * @param sourcePath source file of <code>.class</code> file
     * @throws ImplerException if an internal {@link IOException} has occurred
     */
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

    /**
     * Generates implementation of a class denoted by the provided type token and creates a <code>.jar</code>
     * file which contains that implementation in the provided path.
     *
     * @param clazz target type token
     * @param path target path
     * @throws ImplerException if:
     * <ul>
     *     <li>One or more arguments are <code>null</code></li>
     *     <li>Target class can't be extended</li>
     *     <li>An internal {@link IOException} has occurred when handling I/O processes</li>
     *     <li>{@link javax.tools.JavaCompiler} failed to compile target source file</li>
     *     <li>There are no callable constructors in the target class</li>
     * </ul>
     */
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

    /**
     * Checks if a class can be extended.
     * Note: a class can't be extended if:
     * <ul>
     *     <li>It is a primitive</li>
     *     <li>It is final</li>
     *     <li>It is array</li>
     *     <li>It is enum</li>
     *     <li>It is {@link Enum}</li>
     * </ul>
     *
     * @param clazz target type token
     * @throws ImplerException if the class can't be extended
     */
    private void validateClass(Class<?> clazz) throws ImplerException {
        if (clazz.isPrimitive() || clazz.isArray() || clazz.isEnum() || Modifier.isFinal(clazz.getModifiers()) || clazz == Enum.class) {
            throw new ImplerException(String.format("Invalid class with name: %s", clazz.getSimpleName()));
        }
    }

    /**
     * Does base initialization. Initialization contains:
     * <ul>
     *     <li>Creating a directory for the output file if it doesn't exist</li>
     *     <li>Setting the field {@link #className}</li>
     *     <li>Resetting the field {@link #classImplementation}</li>
     * </ul>
     *
     * @param clazz target type token
     * @param path target path
     * @throws ImplerException if an internal {@link IOException} has occurred
     */
    private void init(Class<?> clazz, Path path) throws ImplerException {
        createDirectories(path);
        setClassName(clazz);
        classImplementation = new StringBuilder();
    }

    /**
     * Generates implementation of a class denoted by the provided type token and creates a <code>.jar</code>
     * file which contains that implementation in the provided path.
     *
     * @param clazz target type token
     * @param path target path
     * @throws ImplerException if:
     * <ul>
     *     <li>One or more arguments are <code>null</code></li>
     *     <li>Target class can't be extended</li>
     *     <li>An internal {@link IOException} has occurred when handling I/O processes</li>
     *     <li>There are no callable constructors in the target class</li>
     * </ul>
     */
    @Override
    public void implement(Class<?> clazz, Path path) throws ImplerException {
        validateClass(clazz);
        path = getFilePath(path, clazz, JAVA);
        init(clazz, path);
        try (var writer = Files.newBufferedWriter(path)) {
            generateHeader(clazz);
            generateBody(clazz);
            generateFooter();
            writer.write(toUnicode(classImplementation.toString()));
        } catch (IOException ignored) {
            throw new ImplerException(String.format("Can't write implementation of a following class: %s", clazz.getCanonicalName()));
        }
    }

    /**
     * Return Unicode representation of the input string.
     *
     * @param input input string
     * @return Unicode representation of the input string
     */
    private String toUnicode(String input) {
        StringBuilder b = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c >= 128) {
                b.append(String.format("\\u%04X", (int) c));
            } else {
                b.append(c);
            }
        }
        return b.toString();
    }

    /**
     * Generates header of the generated class.
     * Header contains <code>package</code> declaration formed using {@link #getPackageDeclaration(Class)}
     * and <code>class</code> declaration formed using {@link #getClassDeclaration(Class)}
     *
     * @param clazz target type token
     */
    private void generateHeader(Class<?> clazz) {
        classImplementation.append(getPackageDeclaration(clazz));
        classImplementation.append(getClassDeclaration(clazz));
    }

    /**
     * Generates body of the generated class.
     * Body contains constructors' (only if the following class is not an interface) and abstract methods'
     * implementations.
     *
     * @param clazz target type token
     * @throws ImplerException if an internal {@link IOException} has occurred
     */
    private void generateBody(Class<?> clazz) throws ImplerException {
        if (!clazz.isInterface()) {
            generateConstructors(clazz);
        }
        generateAbstractMethods(clazz);
    }

    /**
     * Generates footer of the generated class.
     * Footer contains {@link #CURLY_CLOSE} and two line breaks.
     */
    private void generateFooter() {
        classImplementation.append(CURLY_CLOSE).append(DOUBLE_NEWLINE);
    }

    /**
     * Generates implementation of an {@link Executable}.
     * The executable must be either {@link Method} or {@link Constructor} to work correctly.
     * Implementation contains:
     * <ul>
     *     <li>[optional] {@link Deprecated} annotation if needed</li>
     *     <li>Access modifier</li>
     *     <li>[optional] Type parameters (if it is a method)</li>
     *     <li>[optional] Return type (if it is a method)</li>
     *     <li>Name ({@link #className} if it is a constructor</li>
     *     <li>List of all checked exceptions thrown</li>
     *     <li>Implementation formed by either {@link #getMethodImplementation(Class)} or
     *     {@link #getConstructorImplementation(Executable)}</li>
     * </ul>
     *
     * @param executable target executable
     *
     * @see "https://www.geeksforgeeks.org/checked-vs-unchecked-exceptions-in-java/"
     */
    private void generateExecutable(Executable executable) {
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
        if (isDeprecated) classImplementation.append(DEPRECATED);
        classImplementation.append(TAB).append(accessModifier).append(returnType).append(name).append(OPEN).append(args).append(CLOSE).append(SPACE).append(exceptions).append(SPACE).append(CURLY_OPEN).append(NEWLINE).append(TAB).append(implementation).append(NEWLINE).append(TAB);
        generateFooter();
    }

    /**
     * Generates implementation of a constructor.
     *
     * @param constructor target constructor
     * @see #generateExecutable(Executable)
     */
    private void generateConstructor(Constructor<?> constructor) {
        generateExecutable(constructor);
    }

    /**
     * Generates all callable constructors of target type.
     *
     * @param clazz target type token
     * @throws ImplerException if there is no callable constructor in the target class
     */
    private void generateConstructors(Class<?> clazz) throws ImplerException {
        var constructors = Arrays.stream(clazz.getDeclaredConstructors()).filter(c -> !Modifier.isPrivate(c.getModifiers())).collect(Collectors.toList());
        if (constructors.isEmpty()) {
            throw new ImplerException(String.format("Class %s has no callable constructors", clazz.getSimpleName()));
        }
        for (var constructor : constructors) {
            generateConstructor(constructor);
        }
    }

    /**
     * Generates implementation of an abstract method.
     *
     * @param method target method
     * @see #generateExecutable(Executable)
     */
    private void generateAbstractMethod(Method method) {
        generateExecutable(method);
    }

    /**
     * Generates all abstract methods of target type.
     *
     * @param clazz target type token
     */
    private void generateAbstractMethods(Class<?> clazz) {
        var methods = new HashSet<CustomMethod>();
        fill(methods, clazz);
        methods.stream().filter(m -> !m.isOverridden).forEach(m -> generateAbstractMethod(m.instance));
    }

    /**
     * A wrapper of {@link Method} for proper method comparing.
     */
    private class CustomMethod {
        /**
         * Instance of the method.
         */
        private Method instance;
        /**
         * A flag that shows if we need to override the method.
         * It is set to <code>true</code> if we met a <code>final</code> or just not <code>abstract</code> method in
         * the <code>super</code> class.
         * Otherwise, it is set to <code>false</code>.
         */
        private boolean isOverridden;

        /**
         * Constructor for the wrapper that receives instance and the flag.
         *
         * @param m target instance of {@link Method}
         * @param isOverridden target flag
         */
        CustomMethod(Method m, boolean isOverridden) {
            instance = m;
            this.isOverridden = isOverridden;
        }

        /**
         * Custom {@link Object#equals(Object)} method to compare two instances of {@link Method}.
         * Methods are equal if:
         * <ul>
         *     <li>The other method is not <code>null</code></li>
         *     <li>The other method's {@link #hashCode()} is the same</li>
         * </ul>
         *
         * @param obj target instance
         * @return <code>true</code> if instances are equal, <code>false</code> otherwise
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj instanceof CustomMethod) {
                return obj.hashCode() == hashCode();
            }
            return false;
        }

        /**
         * Calculates hash code of a {@link Method} instance
         * Formula: {@link Arrays#hashCode(Object[])} of type parameters plus {@link Class#hashCode()} of
         * return type plus {@link String#hashCode()} of name.
         *
         * @return hash code of the {@link #instance}
         */
        @Override
        public int hashCode() {
            return Arrays.hashCode(instance.getParameterTypes()) + instance.getReturnType().hashCode() + instance.getName().hashCode();
        }
    }

    /**
     * Adds all abstract methods of the target type to {@link Set} of {@link CustomMethod}
     *
     * @param methods target set
     * @param clazz target type token
     */
    private void fill(Set<CustomMethod> methods, Class<?> clazz) {
        if (clazz == null) return;
        methods.addAll(Arrays.stream(clazz.getDeclaredMethods()).map(m -> new CustomMethod(m, Modifier.isFinal(m.getModifiers()) || !Modifier.isAbstract(m.getModifiers()))).collect(Collectors.toSet()));
        Arrays.stream(clazz.getInterfaces()).forEach(i -> fill(methods, i));
        fill(methods, clazz.getSuperclass());
    }

    /**
     * Entry point of the program.
     * Usage: Implementor [-jar] &lt;Class name&gt; &lt;Target path&gt;
     *
     * @param args arguments
     */
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