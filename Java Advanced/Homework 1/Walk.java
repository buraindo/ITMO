package ru.ifmo.rain.ibragimov.walk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

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

    private static int getExceptionResult(String message) {
        System.err.println(message);
        return ERROR_ANSWER;
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
        } catch (NoSuchFileException ignored) {
            result = getExceptionResult("File not found: " + fileName);
        } catch (SecurityException ignored) {
            result = getExceptionResult("Can't access file " + fileName);
        } catch (IOException ignored) {
            result = getExceptionResult("An exception has occurred when reading the contents of the following file: " + fileName);
        }
        return result;
    }

    public static void main(String[] args) {
        if (args == null || args.length != ARGUMENTS_LENGTH || args[0] == null || args[1] == null) {
            System.err.println("Usage: Walk <Input file name> <Output file name>");
            return;
        }
        try {
            if (Paths.get(args[1]).getParent() != null) {
                Files.createDirectories(Paths.get(args[1]).getParent());
            }
        } catch (SecurityException ignored) {
            System.err.println("Can't access the directory" + Paths.get(args[1]).getParent().getFileName());
        } catch (InvalidPathException ignored) {
            System.err.println("The file name contains forbidden characters or the file does not exist, file name: " + args[1]);
        } catch (IOException ignored) {
            System.err.println("Can't create output file with following name: " + args[1]);
        }
        String fileName;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(args[0]), StandardCharsets.UTF_8)) {
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(args[1]), StandardCharsets.UTF_8)) {
                while ((fileName = reader.readLine()) != null) {
                    writer.write(String.format("%08x %s", getHashSum(fileName), fileName) + System.lineSeparator());
                }
            } catch (SecurityException se) {
                System.err.println("Can't access file. " + se.getMessage());
            } catch (IOException ioe) {
                System.err.println("An exception has occurred when reading file. " + ioe.getMessage());
            } catch (InvalidPathException ipe) {
                System.err.println("The input file name contains forbidden characters or the file does not exist. " + ipe.getMessage());
            }
        } catch (SecurityException se) {
            System.err.println("Can't access file. " + se.getMessage());
        } catch (IOException ioe) {
            System.err.println("An exception has occurred when writing file. " + ioe.getMessage());
        } catch (InvalidPathException ipe) {
            System.err.println("The output file name contains forbidden characters or the file does not exist. " + ipe.getMessage());
        }
    }
}
