package vigenere.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import vigenere.encoder.Encoder;

public class Util {

  private static final char[] alphabet = new char[26];

  static {
    for (char c = 'a'; c <= 'z'; c++) {
      alphabet[c - 'a'] = c;
    }
  }

  public static char[] getAlphabet() {
    return alphabet;
  }

  public static void encodeWithRandomWord() throws IOException {
    final var url = new URL("https://random-word-api.herokuapp.com/word?key=0QHEXO5A&number=1");
    final var connection = (URLConnection) url.openConnection();
    final var word = new String(connection.getInputStream().readAllBytes());
    Encoder.main(new String[] {word});
  }
}
