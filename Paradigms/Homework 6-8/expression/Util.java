package expression;

import exceptionsLib.MissingOperandException;
import exceptionsLib.UnknownIdentifierException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Util {

    private static List<Character> chars = new ArrayList<>();

    public static Map<String, Operation<?>> operations = new HashMap<>();
    public static Set<Character> signs = new HashSet<>();
    public static Set<Character> nums = new HashSet<>();
    public static Set<Character> appropriate = new HashSet<>();
    public static Set<String> divideByZeroTypes = new HashSet<>();
    public static Set<String> overflowTypes = new HashSet<>();

    public static final String d = "d";
    public static final String i = "i";
    public static final String bi = "bi";
    public static final String u = "u";
    public static final String l = "l";
    public static final String s = "s";

    public static void initialize() {
        for (char i = '0'; i != '9'; i++) {
            chars.add(i);
            nums.add(i);
        }
        divideByZeroTypes.add(u);
        divideByZeroTypes.add(l);
        divideByZeroTypes.add(s);
        divideByZeroTypes.add(i);
        overflowTypes.add(i);
        chars.add('|');
        chars.add('/');
        chars.add('*');
        chars.add('-');
        chars.add('+');
        chars.add('&');
        chars.add('^');
        chars.add('x');
        chars.add('y');
        chars.add('z');
        chars.add('(');
        chars.add(')');
        signs.add('+');
        signs.add('-');
        signs.add('*');
        signs.add('/');
        signs.add('&');
        signs.add('^');
        signs.add('|');
        appropriate = new HashSet<>(chars);
    }

    public static void initMap () {
        operations.putIfAbsent(d, new DoubleOperation());
        operations.putIfAbsent(u, new UIntegerOperation());
        operations.putIfAbsent(bi, new BigIntegerOperation());
        operations.putIfAbsent(l, new LongOperation());
        operations.putIfAbsent(i, new IntegerOperation());
        operations.putIfAbsent(s, new ShortOperation());
    }

    public static int getPosition(String s, String expected) {
        int pos = 0;
        for (int i = 0; i != s.length(); i++) {
            if (s.charAt(i) != expected.charAt(i)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static boolean tryParse(String s) {
        try {
            int res = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /*public static void handle(String s, int i, String expected) throws UnknownIdentifierException, MissingOperandException {
        var tempSb = new StringBuilder();
        for (var j = i; j != i + 5; j++) {
            tempSb.append(s.charAt(j));
        }
        if (!tempSb.toString().equals(expected)) {
            var position = Util.getPosition(tempSb.toString(), expected);
            throw new UnknownIdentifierException(tempSb.toString(), s, position);
        }
        if (i + 6 > s.length()) {
            throw new MissingOperandException(s, 5);
        }
    }*/

    public static Number getValue (Number num, String mode) {
        switch (mode) {
            case i:
                return num.intValue();
            case l:
                return num.longValue();
            case s:
                return num.shortValue();
            case u:
                return num.intValue();
            case bi:
                return new BigInteger(num.toString());
            case d:
                return num.doubleValue();
        }
        return 0L;
    }

    public static long getMin (String mode) {
        switch (mode) {
            case i:
                return Integer.MIN_VALUE;
            case s:
                return Short.MIN_VALUE;
            case l:
                return Long.MIN_VALUE;
        }
        return 0L;
    }

    public static long getMax (String mode) {
        switch (mode) {
            case i:
                return Integer.MAX_VALUE;
            case s:
                return Short.MAX_VALUE;
            case l:
                return Long.MAX_VALUE;
        }
        return 0L;
    }

}
