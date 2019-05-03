package ru.ifmo.rain.ibragimov.udp;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

class Util {

    static final String ANSWER_PREFIX = "Hello, ";

    private static void showClientUsage() {
        System.out.println("Usage: HelloUPDClient <address> <port> <prefix> <threads> <per thread>.");
    }

    private static void showServerUsage() {
        System.out.println("Usage: HelloUPDServer <port> <threads>.");
    }

    static void validateInput(String[] args, boolean server, int expectedArgsCount) {
        Objects.requireNonNull(args);
        if (args.length != 5) {
            if (server) showServerUsage(); else showClientUsage();
            throw new IllegalArgumentException(format("Illegal number of arguments, expected: %d but got: %d", expectedArgsCount, args.length));
        }
        for (var arg : args) {
            Objects.requireNonNull(arg);
        }
    }

    static String getRequestMessage(final String prefix, final int threadNumber, final int requestNumber) {
        return format("%s%d_%d", prefix, threadNumber, requestNumber);
    }

    static void close(ExecutorService executorService) {
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {}
    }

}
