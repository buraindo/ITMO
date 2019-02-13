package ru.ifmo.rain.ibragimov.walk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Walk {

    private static final int ARGUMENTS_LENGTH = 2;
    private static final int BLOCK_SIZE = 1024;
    private static final int INIT_HASH = 0x811c9dc5;
    private static final int ERROR_ANSWER = 0x00000000;

    private static int hash(final byte[] bytes, final int count, int hash) {
        for (int i = 0; i < count; i++) {
            byte b = bytes[i];
            hash = (hash * 0x01000193) ^ (b & 0xff);
        }
        return hash;
    }

    private static int getHashSum(final String fileName) {
        Path path;
        try {
            path = Paths.get(fileName);
        } catch (InvalidPathException ignored) {
            System.err.println("The file name contains forbidden characters or the file does not exist, file name: " + fileName);
            return ERROR_ANSWER;
        }
        byte[] bytes = new byte[BLOCK_SIZE];
        int result = INIT_HASH;
        int bytesRead;
        try (InputStream stream = Files.newInputStream(path)) {
            while ((bytesRead = stream.read(bytes)) >= 0) {
                result = hash(bytes, bytesRead, result);
            }
        } catch (IOException ignored) {
            System.err.println("An exception has occurred when reading the contents of the following file: " + fileName);
            result = ERROR_ANSWER;
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length != ARGUMENTS_LENGTH) {
            System.err.println("Usage: Walk <Input file name> <Output file name>");
            return;
        }
        Path[] paths = new Path[2];
        for (int i = 0; i < paths.length; i++) {
            String fileName = args[i];
            try {
                paths[i] = Paths.get(fileName);
            } catch (InvalidPathException ignored) {
                System.err.println(String.format("The file name \"%s\" contains forbidden characters or the file does not exist", fileName));
                return;
            }
        }
        String fileName;
        Path inputFilePath = paths[0], outputFilePath = paths[1];
        try {
            if (outputFilePath.getParent() != null) {
                Files.createDirectories(outputFilePath.getParent());
            }
        } catch (IOException e) {
            System.err.println("Can't create output file with following name: " + args[1]);
        }
        try (BufferedReader reader = Files.newBufferedReader(inputFilePath, StandardCharsets.UTF_8)) {
            try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8)) {
                while ((fileName = reader.readLine()) != null) {
                    writer.write(String.format("%08x %s", getHashSum(fileName), fileName) + System.lineSeparator());
                }
            } catch (IOException ignored) {
                System.err.println("An exception has occurred when reading file.");
            }
        } catch (IOException ignored) {
            System.err.println("An exception has occurred when writing file.");
        }
    }
}
