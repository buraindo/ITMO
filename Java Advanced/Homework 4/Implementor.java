package ru.ifmo.rain.ibragimov.implementor;

import info.kgeorgiy.java.advanced.implementor.Impler;
import info.kgeorgiy.java.advanced.implementor.ImplerException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Implementor implements Impler {
    private static final int ARGS_LENGTH = 2;

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

    private static final String PACKAGE = "package ";
    private static final String CLASS = "class ";
    private static final String IMPLEMENTS = "implements ";
    private static final String EXTENDS = "extends ";

    private static final String PUBLIC = "public ";
    private static final String PROTECTED = "protected ";

    private static final String RETURN = "return ";
    private static final String SUPER = "super";

    private String className;

    private String getFileName(Class<?> token) {
        return token.getSimpleName().concat(CLASS_NAME_SUFFIX);
    }

    private Path getFilePath(Path path, Class<?> clazz, String extension) {
        return path.resolve(clazz.getPackage().getName().replace('.', File.separatorChar)).resolve(String.format("%s.%s", getFileName(clazz), extension));
    }

    private void validateClass(Class<?> clazz) throws ImplerException {
        if (clazz.isPrimitive() || clazz.isArray() || clazz.isEnum() || Modifier.isFinal(clazz.getModifiers()) || clazz == Enum.class /*обожаю костыли, спасибо, Гоша, а давай еще 100500 классов, от которых нельзя наследоваться, я каждый заифаю*/) {
            throw new ImplerException(String.format("Invalid class with name: %s", clazz.getSimpleName()));
        }
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

    @Override
    public void implement(Class<?> clazz, Path path) throws ImplerException {
        validateClass(clazz);
        path = getFilePath(path, clazz, JAVA);
        createDirectories(path);
        setClassName(clazz);
        try (var writer = Files.newBufferedWriter(path)) {
            generateHeader(clazz, writer);
            generateBody(clazz, writer);
            generateFooter(clazz, writer);
        } catch (IOException ignored) {
            throw new ImplerException(String.format("Can't write implementation of a following class: %s", clazz.getCanonicalName()));
        }
    }

    private String getClassName(Class<?> clazz) {
        return clazz.getSimpleName() + CLASS_NAME_SUFFIX;
    }

    private void setClassName(Class<?> clazz) {
        this.className = getClassName(clazz);
    }

    private void generateHeader(Class<?> clazz, BufferedWriter writer) throws ImplerException {
        try {
            writer.write(getPackage(clazz));
            writer.write(getClass(clazz));
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

    private void generateExecutable(Executable executable, BufferedWriter writer, boolean isConstructor) throws ImplerException {
        try {
            var accessModifierMask = executable.getModifiers() & (Modifier.PROTECTED | Modifier.PUBLIC);
            String accessModifier = EMPTY;
            if (accessModifierMask > 0) {
                accessModifier = Modifier.isPublic(accessModifierMask) ? PUBLIC : PROTECTED;
            }
            String returnType = EMPTY;
            String name = className;
            String implementation = getConstructorImplementation(executable);
            if (!isConstructor) {
                returnType = ((Method) executable).getReturnType().getCanonicalName() + SPACE;
                name = executable.getName();
                implementation = getImplementation(((Method) executable).getReturnType());
            }
            var args = new StringJoiner(COMMA + SPACE);
            Arrays.stream(executable.getParameters()).forEach(param -> args.add(param.toString()));
            writer.write(TAB + accessModifier + returnType + name + OPEN + args.toString() + CLOSE + SPACE + CURLY_OPEN + NEWLINE + TAB + implementation + NEWLINE + TAB);
            generateFooter(executable.getDeclaringClass(), writer);
        } catch (IOException ignored) {
            throw new ImplerException(String.format("Can't write implementation of a following method: %s", executable.getName()));
        }
    }

    private String getConstructorImplementation(Executable executable) {
        var args = new StringJoiner(COMMA + SPACE);
        Arrays.stream(executable.getParameters()).forEach(param -> args.add(param.getName()));
        return TAB + SUPER + OPEN + args.toString() + CLOSE + COLON;
    }

    @SuppressWarnings("unchecked")
    private <T> T getDefaultValue(Class<T> clazz) {
        return (T) Array.get(Array.newInstance(clazz, 1), 0);
    }

    private String getImplementation(Class<?> returnType) {
        String defaultValueStr;
        if (returnType.equals(Void.TYPE)) {
            defaultValueStr = EMPTY;
        } else
        try {
            defaultValueStr = getDefaultValue(returnType).toString();
            if (defaultValueStr.charAt(0) == Character.MIN_VALUE) {
                defaultValueStr = "'\u0000'";
            }
        } catch (NullPointerException | IllegalArgumentException ignored) {
            defaultValueStr = "null";
        }
        if (defaultValueStr.equals("0.0")) defaultValueStr += "f";
        return TAB + RETURN + defaultValueStr + COLON;
    }

    private void generateConstructor (Constructor<?> constructor, BufferedWriter writer) throws ImplerException {
        generateExecutable(constructor, writer, true);
    }

    private void generateConstructors (Class<?> clazz, BufferedWriter writer) throws ImplerException {
        var constructors = Arrays.stream(clazz.getDeclaredConstructors()).filter(c -> !Modifier.isPrivate(c.getModifiers())).collect(Collectors.toList());
        if (constructors.isEmpty()) {
            throw new ImplerException(String.format("Class %s has no callable constructors", clazz.getSimpleName()));
        }
        for (var constructor : constructors) {
            generateConstructor(constructor, writer);
        }
    }

    private void generateAbstractMethod(Method method, BufferedWriter writer) throws ImplerException {
        generateExecutable(method, writer, false);
    }

    private class CustomMethod {
        private Method instance;
        CustomMethod(Method m) {
            instance = m;
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
        methods.addAll(Arrays.stream(clazz.getDeclaredMethods()).filter(c -> Modifier.isAbstract(c.getModifiers())).map(CustomMethod::new).collect(Collectors.toSet()));
        Arrays.stream(clazz.getInterfaces()).forEach(i -> fill(methods, i));
        fill(methods, clazz.getSuperclass());
    }

    private void generateAbstractMethods(Class<?> clazz, BufferedWriter writer) throws ImplerException {
        var methods = new HashSet<CustomMethod>();
        fill(methods, clazz);
        for (var method : methods) {
            generateAbstractMethod(method.instance, writer);
        }
    }

    private String getPackage(Class<?> clazz) {
        if (!clazz.getPackage().getName().equals(EMPTY)) {
            return PACKAGE + clazz.getPackageName() + COLON + DOUBLE_NEWLINE;
        }
        return EMPTY;
    }

    private String getClass(Class<?> clazz) {
        var deriveKeyWord = clazz.isInterface() ? IMPLEMENTS : EXTENDS;
        return PUBLIC + CLASS  + className + SPACE + deriveKeyWord + clazz.getSimpleName() + SPACE + CURLY_OPEN + DOUBLE_NEWLINE;
    }

    public static void main(String[] args) {
        if (args == null || args.length != ARGS_LENGTH || args[0] == null || args[1] == null) {
            System.err.println("Usage: Implementor <Class name> <Target path>");
            return;
        }
        var classFullName = args[0];
        var pathName = args[1];
        Impler implementor = new Implementor();
        try {
            var clazz = Class.forName(classFullName);
            var path = Paths.get(pathName);
            implementor.implement(clazz, path);
        } catch (ClassNotFoundException ignored) {
            System.err.println(String.format("Class with name %s not found", classFullName));
        } catch (ImplerException ignored) {
            System.err.println(String.format("Can't generate implementation of a class with full name: %s", classFullName));
        }
    }
}
