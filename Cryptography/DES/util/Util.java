package des.util;

import java.util.Arrays;

public class Util {

  public static int[] transform(final int[] input, final Integer[] permutation) {
    var transformed = new int[permutation.length];
    for (var i = 0; i < permutation.length; i++) {
      transformed[i] = input[permutation[i]];
    }
    return transformed;
  }

  public static int[] cyclicLeftShift(final int[] array, final int shift) {
    final var result = new int[array.length];
    System.arraycopy(array, shift, result, 0, result.length - shift);
    System.arraycopy(array, 0, result, result.length - shift, shift);
    return result;
  }

  public static int[] xor(final int[] lhs, final int[] rhs) {
    final int[] result = new int[lhs.length];
    for (var i = 0; i < lhs.length; i++) {
      result[i] = lhs[i] ^ rhs[i];
    }
    return result;
  }

  private static byte toByte(final String binaryString) {
    return (byte) (int) Integer.valueOf(binaryString, 2);
  }

  public static String toBinaryString(int b, final int padding) {
    if (b < 0) {
      b += 256;
    }
    return String.format("%" + padding + "s", Integer.toBinaryString(b)).replaceAll(" ", "0");
  }

  public static int[] toBinaryArray64(final byte[] block) {
    final var result = new int[64];
    var counter = 0;
    for (final byte b : block) {
      final var binary = Util.toBinaryString(b, 8);
      for (var i = 0; i < binary.length(); i++) {
        result[i + counter] = Integer.parseInt(Character.toString(binary.charAt(i)));
      }
      counter += 8;
    }
    return result;
  }

  public static String printArray(final int[] array) {
    return Arrays.toString(array).replaceAll("[\\[\\] ,]", "");
  }

  public static byte[] toByteArray64(final int[] array) {
    final var result = new byte[8];
    for (var i = 0; i < 8; i++) {
      final var binary = new StringBuilder();
      for (var j = 0; j < 8; j++) {
        binary.append(array[i * 8 + j]);
      }
      result[i] = toByte(binary.toString());
    }
    return result;
  }
}
