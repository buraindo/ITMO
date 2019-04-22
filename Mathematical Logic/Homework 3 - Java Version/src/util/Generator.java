package util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Generator {

    private static String[] tokens = {"&", "|", "->"};
    private static String[] letters = {"A", "B", "C", "!A", "!B", "!C",
    "(A|B)","(!A->B)","(!A->!B)","(A->!B)","(!A|B)","(!A|!B)","(!A->B->C)"};

    public static void generate() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("generated.txt"))) {
            Random theRandom = new Random();
            for (int i = 0; i < 1000000; i++) {
                StringBuilder builder = new StringBuilder();
                boolean f = true;
                for (int j = 0; j < 21; j++) {
                    if (f) {
                        builder.append(letters[Math.abs(theRandom.nextInt()) % letters.length]);
                    } else builder.append(tokens[Math.abs(theRandom.nextInt()) % tokens.length]);
                    f = !f;
                }
                writer.write(builder.toString());
                writer.newLine();
            }
        }
    }

}
