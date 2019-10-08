package vigenere.decoder;

import vigenere.util.Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Decoder {

    public static void main(final String[] args) throws IOException {
        final var passphrase = args[0];
        final var alphabet = Util.getAlphabet();
        try (final var writer = new BufferedWriter(new FileWriter("res/vigenere/decoder/output.txt"))) {
            final var original = String.join(" ", Files.readAllLines(Paths.get("res/vigenere/decoder/input.txt")));
            final var output = original.toCharArray();
            final var shifts = passphrase.toLowerCase().chars().filter(Character::isLetter).map(c -> c - 'a').toArray();
            var index = 0;
            for (var i = 0; i < output.length; i++) {
                var ch = Character.toLowerCase(output[i]);
                if (Character.isLetter(ch)) {
                    final var currentIndex = ch - 'a' - (shifts[index % shifts.length] % 26) > 0 ? (ch - 'a' - (shifts[index % shifts.length] % 26)) % 26 : (26 + (ch - 'a' - (shifts[index % shifts.length] % 26))) % 26;
                    index++;
                    ch = alphabet[currentIndex];
                }
                if (Character.isUpperCase(output[i])) {
                    ch = Character.toUpperCase(ch);
                }
                output[i] = ch;
            }
            writer.write(output);
            writer.newLine();
        }
    }

}
