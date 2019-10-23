package mars.encoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import mars.common.Mars;

public class Encoder {

  public static void main(final String[] args) throws IOException {
    final var key = args[0].getBytes();
    final var mars = new Mars();
    mars.generateSubKeys(key);
    try (final var output = Files.newOutputStream(Paths.get("res/mars/encoder/output.txt"))) {
      final var input = Files.newInputStream(Paths.get("res/mars/encoder/input.txt"));
      final var block = new byte[16];
      int count;
      while ((count = input.read(block)) > 0) {
        if (count != 16) {
          for (var i = count; i < 16; i++) {
            block[i] = 0;
          }
        }
        output.write(mars.encrypt(block));
      }
    }
  }
}
