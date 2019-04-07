package proof;

import parser.Expression;

public class Hypothesis {

    private final Expression expression;
    private final byte number;

    byte getNumber() {
        return number;
    }

    public Expression getExpression() {
        return expression;
    }

    Hypothesis(Expression expression, byte number) {
        this.expression = expression;
        this.number = number;
    }

    String toHumanString() {
        return expression.toHumanString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Hypothesis) {
            Hypothesis hypothesis = (Hypothesis) other;
            return expression.equals(hypothesis.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
}
