package parser;

public class Conj extends BinaryOperation {

    public Conj(Expression Lhs, Expression Rhs) {
        super(Lhs, Rhs);
        token = Constants.CONJ;
    }

    @Override
    public Class<?> getRealClass() {
        return Conj.class;
    }
}