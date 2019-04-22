package proof;

import parser.Expression;

public class Hypothesis {

    private final Expression expression;

    public Expression getExpression() {
        return expression;
    }

    Hypothesis(Expression expression) {
        this.expression = expression;
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
