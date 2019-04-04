package expression;

import static expression.Constants.*;

abstract class BinaryOperation implements Expression {

    private Expression lhs, rhs;
    String token;

    BinaryOperation (Expression Lhs, Expression Rhs) {
        lhs = Lhs;
        rhs = Rhs;
    }

    public String toString() {
        return OPEN + token + COMMA + lhs.toString() + COMMA + rhs.toString() + CLOSE;
    }

}
