package expression;

class Conj extends BinaryOperation {

    Conj(Expression Lhs, Expression Rhs) {
        super(Lhs, Rhs);
        token = Constants.CONJ;
    }
}