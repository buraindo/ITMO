package expression;

import exceptionsLib.*;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Main {

    private static void print (Object o) {
        if (o.getClass().getName().equals("java.lang.Integer"))
            System.out.println(o);
        System.out.println(o.getClass());
    }

    public static void main(String[] args) {
        Double x = 5D;
        Pattern p = Pattern.compile("zhopa");
        System.out.println((double) (x.shortValue()));
    }
}
