package expression.generic;

import exceptionsLib.*;
import expression.Operation;
import expression.TripleExpression;
import expression.Util;
import expression.parser.ExpressionParser;

public class GenericTabulator implements Tabulator {

    private <T> Object[][][] protoTabulate(Operation<T> operation, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws MissingOperationException, IllegalConstantException, MissingOperandException, UnknownSymbolException, UnknownIdentifierException, OddOpeningParenthesisException, IncorrectConstException, OddClosingParenthesisException {
        var xDiff = x2 - x1 + 1;
        var yDiff = y2 - y1 + 1;
        var zDiff = z2 - z1 + 1;
        var result = new Object[xDiff][yDiff][zDiff];
        TripleExpression<T> expr = new ExpressionParser<>(operation).parse(expression);
        for (int j = 0; j != xDiff; j++) {
            for (int k = 0; k != yDiff; k++) {
                for (int m = 0; m != zDiff; m++) {
                    try {
                        T xVal = operation.parse(Integer.toString(j + x1));
                        T yVal = operation.parse(Integer.toString(k + y1));
                        T zVal = operation.parse(Integer.toString(m + z1));
                        T value = expr.evaluate(xVal, yVal, zVal);
                        result[j][k][m] = value;
                    } catch (ArithmeticException e) {
                        result[j][k][m] = null;
                    }
                }
            }
        }
        return result;
    }

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Util.initMap();
        return protoTabulate(Util.operations.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }
}
