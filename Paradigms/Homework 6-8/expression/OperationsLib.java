package expression;

import java.math.BigInteger;

class DoubleOperation implements Operation<Double> {

    @Override
    public Double parse(String stringRepresentation) {
        return Double.parseDouble(stringRepresentation);
    }

    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }
}

class BigIntegerOperation implements Operation<BigInteger> {

    @Override
    public BigInteger parse(String stringRepresentation) {
        return BigInteger.valueOf(Integer.parseInt(stringRepresentation));
    }

    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        return x.divide(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }
}

class ShortOperation implements Operation<Short> {

    @Override
    public Short parse(String stringRepresentation) {
        try {
            return Short.parseShort(stringRepresentation);
        } catch (Exception e) {
            return (short) Integer.parseInt(stringRepresentation);
        }
    }

    @Override
    public Short add(Short x, Short y) {
        return (short)(x + y);
    }

    @Override
    public Short subtract(Short x, Short y) {
        return (short)(x - y);
    }

    @Override
    public Short multiply(Short x, Short y) {
        return (short)(x * y);
    }

    @Override
    public Short divide(Short x, Short y) {
        return (short)(x / y);
    }

    @Override
    public Short negate(Short x) {
        return (short) -x;
    }
}

class IntegerOperation implements Operation<Integer> {

    @Override
    public Integer parse(String stringRepresentation) {
        return Integer.parseInt(stringRepresentation);
    }

    @Override
    public Integer add(Integer x, Integer y) {
        if (x > 0 && Integer.MAX_VALUE - x < y) {
            throw new ArithmeticException();
        }
        if (x < 0 && Integer.MIN_VALUE - x > y) {
            throw new ArithmeticException();
        }
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
            throw new ArithmeticException();
        }
        if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
            throw new ArithmeticException();
        }
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new ArithmeticException();
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new ArithmeticException();
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new ArithmeticException();
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new ArithmeticException();
        }
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) throw new ArithmeticException("Can't divide by zero");
        return x / y;
    }

    @Override
    public Integer negate(Integer x) {
        return -x;
    }
}

class UIntegerOperation implements Operation<Integer> {

    @Override
    public Integer parse(String stringRepresentation) {
        return Integer.parseInt(stringRepresentation);
    }

    @Override
    public Integer add(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        return x / y;
    }

    @Override
    public Integer negate(Integer x) {
        return -x;
    }
}

class LongOperation implements Operation<Long> {

    @Override
    public Long parse(String stringRepresentation) {
        return Long.parseLong(stringRepresentation);
    }

    @Override
    public Long add(Long x, Long y) {
        return x + y;
    }

    @Override
    public Long subtract(Long x, Long y) {
        return x - y;
    }

    @Override
    public Long multiply(Long x, Long y) {
        return x * y;
    }

    @Override
    public Long divide(Long x, Long y) {
        return x / y;
    }

    @Override
    public Long negate(Long x) {
        return -x;
    }
}
