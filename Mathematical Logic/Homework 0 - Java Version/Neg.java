package expression;

import static expression.Constants.*;

class Neg implements Expression {

    private Expression expression;

    Neg(Expression Expression) {
        expression = Expression;
    }

    public String toString() {
        return OPEN + NEG + expression.toString() + CLOSE;
    }


}
