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
            System.err.println("Usage: Walk Input_File_Name Output_File_Name");
            return;
        }
        String inputFileName = args[0];
        String outputFileName = args[1];
        Path inputFilePath, outputFilePath;
        String fileName;
        try {
            inputFilePath = Paths.get(inputFileName);
            outputFilePath = Paths.get(outputFileName);
        } catch (InvalidPathException ignored) {
            System.err.println("The file name contains forbidden characters or the file does not exist");
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(inputFilePath, StandardCharsets.UTF_8); BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8)) {
            while ((fileName = reader.readLine()) != null) {
                writer.write(String.join(" ", String.format("%08x", getHashSum(fileName)), fileName) + System.lineSeparator());
            }
        } catch (IOException ignored) {
            System.err.println("An exception has occurred when reading/writing file.");
        }
    }
}
