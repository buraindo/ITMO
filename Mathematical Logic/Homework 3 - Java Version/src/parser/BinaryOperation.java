package parser;

import static parser.Constants.*;
import static parser.Constants.SPACE;

public abstract class BinaryOperation implements Expression {

    Expression lhs, rhs;
    String token;
    boolean state;

    BinaryOperation (Expression Lhs, Expression Rhs) {
        lhs = Lhs;
        rhs = Rhs;
    }

    public String toString() {
        return OPEN + lhs.toString() + SPACE + token + SPACE + rhs.toString() + CLOSE;
    }

    @Override
    public boolean state() {
        return state;
    }

    @Override
    public void setState(boolean state) {
        this.state = state;
    }

    public Expression getLhs() {
        return lhs;
    }

    public Expression getRhs() {
        return rhs;
    }

    public String getToken() {
        return token;
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
