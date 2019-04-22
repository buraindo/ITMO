package util;

import parser.Expression;
import parser.Neg;
import parser.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static parser.Constants.EMPTY;

public class Util {

    public static List<Expression> expressionsByMask(int b, int except) {
        List<Expression> hypotheses = new ArrayList<>();
        int sz = Expression.values.size();
        for (int j = 0; j < sz; j++) {
            if (j == except) continue;
            int jThBit = (b >> (sz - j - 1)) & 1;
            Expression hypothesis = new Variable(Expression.indexToVariable.get(j));
            if (jThBit == 0) {
                hypothesis = new Neg(hypothesis);
            }
            hypotheses.add(hypothesis);
        }
        return hypotheses;
    }

    private static boolean[] getTruthTable(Expression expression, int constantBit) {
        int size = Expression.values.size();
        int count = 1 << size;
        boolean[] table = new boolean[count];
        for (int i = 0; i < count; i++) {
            int input = i | constantBit;
            for (int j = 0; j < size; j++) {
                int jThBit = (input >> (size - j - 1)) & 1;
                Expression.values.replace(Expression.indexToVariable.get(j), jThBit);
            }
            table[i] = expression.evaluate();
        }
        return table;
    }

    public void printTruthTable(Expression expression, int constantBit) {
        boolean[] table = Util.getTruthTable(expression, constantBit);
        for (boolean b : table) System.out.println(b);
        for (Integer key : Expression.indexToVariable.keySet()) {
            System.out.print(Expression.indexToVariable.get(key));
        }
        System.out.println();
    }

    public static String printSummary(int input, boolean positive) {
        if (input == EMPTY) {
            return "|- ";
        }
        int size = Expression.values.size();
        for (int i = 0; i < size; i++) {
            int ithBit = (input >> (size - i - 1)) & 1;
            Expression.values.replace(Expression.indexToVariable.get(i), ithBit);
        }
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < size; i++) {
            String s = Expression.indexToVariable.get(i);
            Expression var = new Variable(s);
            if (Expression.values.get(s) == 0 && !positive) {
                var = new Neg(var);
                joiner.add(var.toString());
            } else if (Expression.values.get(s) == 1 && positive) {
                joiner.add(var.toString());
            }
        }
        return (joiner.toString() + " |- ");
    }

    public static void setVariables(int input) {
        int size = Expression.values.size();
        for (int i = 0; i < size; i++) {
            int ithBit = (input >> (size - i - 1)) & 1;
            Expression.values.replace(Expression.indexToVariable.get(i), ithBit);
        }
    }

}
