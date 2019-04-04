package parser;

import static parser.Constants.*;

public abstract class BinaryOperation extends Expression {

    private Expression lhs, rhs;
    String token;

    public Expression getLhs() {
        return lhs;
    }

    public Expression getRhs() {
        return rhs;
    }

    BinaryOperation (Expression Lhs, Expression Rhs) {
        lhs = Lhs;
        rhs = Rhs;
    }

    public String toString() {
        return OPEN + token + COMMA + lhs.toString() + COMMA + rhs.toString() + CLOSE;
    }

    public String toHumanString() {
        return OPEN + lhs.toHumanString() + SPACE + token + SPACE + rhs.toHumanString() + CLOSE;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BinaryOperation && ((BinaryOperation) other).getRealClass() == getRealClass()) {
            BinaryOperation op = (BinaryOperation) other;
            return lhs.equals(op.lhs) && rhs.equals(op.rhs);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (lhs.hashCode() * 31 + rhs.hashCode()) * 31 + token.hashCode();
    }

}
