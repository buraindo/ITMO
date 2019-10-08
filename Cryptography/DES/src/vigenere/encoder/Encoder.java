package vigenere.encoder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import vigenere.util.Util;

public class Encoder {

  public static void main(final String[] args) throws IOException {
    final var passphrase = args[0];
    final var alphabet = Util.getAlphabet();
    try (final var writer = new BufferedWriter(new FileWriter("res/vigenere/encoder/output.txt"))) {
      for (final var original : Files.readAllLines(Paths.get("res/vigenere/encoder/input.txt"))) {
        final var output = original.toCharArray();
        final var shifts =
            passphrase
                .toLowerCase()
                .chars()
                .filter(Character::isLetter)
                .map(c -> c - 'a')
                .toArray();
        var index = 0;
        for (var i = 0; i < output.length; i++) {
          var ch = Character.toLowerCase(output[i]);
          if (Character.isLetter(ch)) {
            ch = alphabet[(ch - 'a' + shifts[index++ % shifts.length]) % 26];
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
    Files.copy(
        Paths.get("res/vigenere/encoder/output.txt"),
        Paths.get("res/vigenere/kasiski/input.txt"),
        StandardCopyOption.REPLACE_EXISTING);
  }
}
