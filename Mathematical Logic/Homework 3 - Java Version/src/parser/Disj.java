package parser;

public class Disj extends BinaryOperation {

    public Disj(Expression Lhs, Expression Rhs) {
        super(Lhs, Rhs);
        token = Constants.DISJ;
    }

    @Override
    public boolean evaluate() {
        return lhs.evaluate() | rhs.evaluate();
    }

    @Override
    public Class<?> getRealClass() {
        return Disj.class;
    }
}