package parser;

import static parser.Constants.*;

public class Neg extends Expression {

    public Expression getExpression() {
        return expression;
    }

    private Expression expression;

    public Neg(Expression Expression) {
        expression = Expression;
    }

    public String toString() {
        return OPEN + NEG + expression.toString() + CLOSE;
    }

    public String toHumanString() {
        return NEG + expression.toHumanString();
    }

    @Override
    public String toClassString() {
        return NEW + SPACE + getRealClass().getSimpleName() + OPEN + expression.toClassString() + CLOSE;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Neg) {
            Neg neg = (Neg) other;
            return expression.equals(neg.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }

    @Override
    public Class<?> getRealClass() {
        return Neg.class;
    }

}
