package parser;

import static parser.Constants.*;

public class Neg implements Expression {

    private Expression expression;
    private boolean state;

    public Neg(Expression Expression) {
        expression = Expression;
    }

    public String toString() {
        return NEG + expression.toString();
    }

    @Override
    public boolean evaluate() {
        return !expression.evaluate();
    }

    @Override
    public boolean state() {
        return state;
    }

    @Override
    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public Class<?> getRealClass() {
        return Neg.class;
    }

    public Expression getExpression() {
        return expression;
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

}
