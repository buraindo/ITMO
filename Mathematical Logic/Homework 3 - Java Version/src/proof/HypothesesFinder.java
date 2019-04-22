package proof;

import parser.Constants;
import parser.Expression;
import util.Util;

import java.util.*;

import static parser.Constants.MAX_CONSTANT_BIT;
import static parser.Constants.MAX_VARIABLE_NUMBER;

public class HypothesesFinder {

    private Expression expression;

    public HypothesesFinder(Expression expression) {
        this.expression = expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    private Set<Integer> getTruthTableFailedVariables(int constantBit, boolean positive) {
        int size = Expression.values.size();
        int count = 1 << size;
        Set<Integer> failedVariables = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int input = positive ? i | constantBit : i & constantBit;
            for (int j = 0; j < size; j++) {
                int jThBit = (input >> (size - j - 1)) & 1;
                Expression.values.replace(Expression.indexToVariable.get(j), jThBit);
            }
            boolean result = expression.evaluate();
            if (!result) {
                int goodVariablesFailed = 0;
                for (int j = 0; j < size; j++) {
                    int jThBit = (input >> (size - j - 1)) & 1;
                    int bad = positive ? 0 : 1;
                    if (jThBit == bad) {
                        failedVariables.add(j);
                    } else {
                        goodVariablesFailed++;
                    }
                }
                if (goodVariablesFailed == size) {
                    for (int j = 0; j < MAX_VARIABLE_NUMBER + 1; j++) {
                        //filling it with trash to make sure we can have 4 numbers in set only in case
                        //when it's impossible to make a proper hypotheses set
                        failedVariables.add(j);
                    }
                }
            }
        }
        return failedVariables;
    }

    private boolean getResult(Expression expression, int input) {
        int size = Expression.values.size();
        for (int i = 0; i < size; i++) {
            int ithBit = (input >> (size - i - 1)) & 1;
            Expression.values.replace(Expression.indexToVariable.get(i), ithBit);
        }
        return expression.evaluate();
    }

    public Queue<Integer> getPath(int hypotheses, boolean positive) {
        Queue<Integer> path = new ArrayDeque<>();
        int valuesSize = Expression.values.size();
        if (hypotheses == Constants.EMPTY) {
            for (int i = 0; i < (1 << valuesSize); i++) {
                path.add(i);
            }
            return path;
        }
        for (int i = 0; i < (1 << valuesSize); i++) {
            boolean result = getResult(expression, i);
            if (result) {
                if (!positive) {
                    if ((i | hypotheses) == hypotheses) {
                        path.add(i);
                    }
                } else
                if ((i & hypotheses) == hypotheses) {
                    path.add(i);
                }
            }
        }
        return path;
    }

    public int getHypotheses(boolean positive) {
        int valuesSize = Expression.values.size();
        int maxBit = (1 << valuesSize) - 1;
        List<Integer> failed = new ArrayList<>(getTruthTableFailedVariables(positive ? 0 : maxBit, positive));
        if (failed.size() == MAX_VARIABLE_NUMBER + 1
        || getTruthTableFailedVariables(MAX_CONSTANT_BIT, positive).size() == MAX_VARIABLE_NUMBER + 1 && positive
        || getTruthTableFailedVariables(0, positive).size() == MAX_VARIABLE_NUMBER + 1 && !positive) {
            return Constants.FAILED;
        }
        int size = failed.size();
        List<Integer> constantBits = new ArrayList<>();
        if (size == 1) {
            int a = 1 << (valuesSize - failed.get(0) - 1);
            if (positive) {
                constantBits.add(a);
            } else constantBits.add(maxBit & ~a);
        } else if (size == 2) {
            int a = 1 << (valuesSize - failed.get(0) - 1);
            int b = 1 << (valuesSize - failed.get(1) - 1);
            if (positive) {
                constantBits.addAll(Arrays.asList(a, b, a | b));
            } else constantBits.addAll(Arrays.asList(maxBit & ~a, maxBit & ~b, maxBit & ~(a | b)));
        } else if (size == 3) {
            int a = 1 << (valuesSize - failed.get(0) - 1);
            int b = 1 << (valuesSize - failed.get(1) - 1);
            int c = 1 << (valuesSize - failed.get(2) - 1);
            if (positive) {
                constantBits.addAll(Arrays.asList(a, b, c, a | b, a | c, b | c, a | b | c));
            } else constantBits.addAll(Arrays.asList(maxBit & ~a, maxBit & ~b, maxBit & ~c, maxBit & ~(a | b), maxBit & ~(a | c), maxBit & ~(b | c), maxBit & ~(a | b | c)));
        }
        for (int b : constantBits) {
            failed = new ArrayList<>(getTruthTableFailedVariables(b, positive));
            if (failed.size() == 0) {
                return b;
            }
        }
        return Constants.EMPTY;
    }

}
