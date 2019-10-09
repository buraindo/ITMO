package des.decoder;

import des.common.Des;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Decoder {
  public static void main(final String[] args) throws IOException {
    final var key = args[0].getBytes();
    final var des = new Des();
    des.generateSubKeys(key);
    try (final var output = Files.newBufferedWriter(Paths.get("res/des/decoder/output.txt"))) {
      final var input = Files.newInputStream(Paths.get("res/des/encoder/output.txt"));
      final var block = new byte[8];
      int count;
      while ((count = input.read(block)) > 0) {
        if (count != 8) {
          for (var i = count; i < 8; i++) {
            block[i] = 0;
          }
        }
        output.write(new String(des.code(block, false)));
      }
    }
  }
}
