package des.common;

import des.util.Util;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Des {

  private static List<int[]> subKeys;

  private static Integer[] shifts;
  private static Integer[] e;
  private static Integer[] ip;
  private static Integer[] ipInverse;
  private static Integer[] pc;
  private static Integer[] subkey;
  private static Integer[] p;
  private static Integer[][][] s;

  static {
    try {
      shifts =
          Arrays.stream(
                  Files.readAllLines(Paths.get("res/des/resources/shifts.txt")).get(0).split(" "))
              .map(Integer::parseInt)
              .toArray(Integer[]::new);
      e =
          Arrays.stream(Files.readAllLines(Paths.get("res/des/resources/e.txt")).get(0).split(" "))
              .map(s -> Integer.parseInt(s) - 1)
              .toArray(Integer[]::new);
      ip =
          Arrays.stream(Files.readAllLines(Paths.get("res/des/resources/ip.txt")).get(0).split(" "))
              .map(s -> Integer.parseInt(s) - 1)
              .toArray(Integer[]::new);
      ipInverse =
          Arrays.stream(
                  Files.readAllLines(Paths.get("res/des/resources/ip_inverse.txt"))
                      .get(0)
                      .split(" "))
              .map(s -> Integer.parseInt(s) - 1)
              .toArray(Integer[]::new);
      pc =
          Arrays.stream(Files.readAllLines(Paths.get("res/des/resources/pc.txt")).get(0).split(" "))
              .map(s -> Integer.parseInt(s) - 1)
              .toArray(Integer[]::new);
      subkey =
          Arrays.stream(
                  Files.readAllLines(Paths.get("res/des/resources/subkey.txt")).get(0).split(" "))
              .map(s -> Integer.parseInt(s) - 1)
              .toArray(Integer[]::new);
      p =
          Arrays.stream(Files.readAllLines(Paths.get("res/des/resources/p.txt")).get(0).split(" "))
              .map(s -> Integer.parseInt(s) - 1)
              .toArray(Integer[]::new);
      s = new Integer[8][4][14];
      for (var i = 0; i < 8; i++) {
        final var current =
            Files.readAllLines(Paths.get(String.format("res/des/resources/s-box/s%s.txt", i + 1)));
        for (var j = 0; j < 4; j++) {
          s[i][j] =
              Arrays.stream(current.get(j).split(" "))
                  .map(Integer::parseInt)
                  .toArray(Integer[]::new);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void generateSubKeys(final byte[] keyBytes) {
    subKeys = new ArrayList<>();
    final var key = Util.transform(Util.toBinaryArray64(keyBytes), pc);
    subKeys.add(key);
    for (var i = 1; i <= 16; i++) {
      final var before = subKeys.get(i - 1);
      final var shift = shifts[i - 1];
      final var c = Util.cyclicLeftShift(Arrays.copyOfRange(before, 0, 28), shift);
      final var d = Util.cyclicLeftShift(Arrays.copyOfRange(before, 28, 56), shift);
      final var newKey = new int[56];
      System.arraycopy(c, 0, newKey, 0, 28);
      System.arraycopy(d, 0, newKey, 28, 28);
      subKeys.add(newKey);
    }
    subKeys.remove(0);
    for (var i = 0; i < 16; i++) {
      subKeys.set(i, Util.transform(subKeys.get(i), subkey));
    }
  }

  private int[] f(final int[] r, final int[] key) {
    final var input = Util.xor(Util.transform(r, e), key);
    final var result = new int[32];
    for (var i = 0; i < 48; i += 6) {
      final var six = Arrays.copyOfRange(input, i, i + 6);
      final var x = i / 6;
      final var y = Integer.valueOf(six[0] + "" + six[5], 2);
      final var z = Integer.valueOf(Util.printArray(Arrays.copyOfRange(six, 1, 5)), 2);
      final var sBoxValue = s[x][y][z];
      final var bits =
          Arrays.stream(Util.toBinaryString(sBoxValue, 4).split(""))
              .mapToInt(Integer::parseInt)
              .toArray();
      System.arraycopy(bits, 0, result, x * 4, 4);
    }
    return Util.transform(result, p);
  }

  public byte[] code(final byte[] messageBlock, final boolean encode) {
    final var block = Util.transform(Util.toBinaryArray64(messageBlock), ip);
    final var results = new ArrayList<int[]>();
    results.add(block);
    for (var i = 1; i <= 16; i++) {
      final var before = results.get(i - 1);
      final var l = Arrays.copyOfRange(before, 0, 32);
      final var r = Arrays.copyOfRange(before, 32, 64);
      final var rNew = Util.xor(l, f(r, encode ? subKeys.get(i - 1) : subKeys.get(16 - i)));
      final var result = new int[64];
      System.arraycopy(r, 0, result, 0, 32);
      System.arraycopy(rNew, 0, result, 32, 32);
      results.add(result);
    }
    final var result = results.get(results.size() - 1);
    final var l = Arrays.copyOfRange(result, 0, 32);
    final var r = Arrays.copyOfRange(result, 32, 64);
    System.arraycopy(r, 0, result, 0, 32);
    System.arraycopy(l, 0, result, 32, 32);
    return Util.toByteArray64(Util.transform(result, ipInverse));
  }
}
