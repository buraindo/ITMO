package parser;

public class Impl extends BinaryOperation {

    public Impl(Expression Lhs, Expression Rhs) {
        super(Lhs, Rhs);
        token = Constants.IMPL;
    }

    @Override
    public boolean evaluate() {
        return !lhs.evaluate() | rhs.evaluate();
    }

    @Override
    public Class<?> getRealClass() {
        return Impl.class;
    }
}
