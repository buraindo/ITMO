package expression;

class Disj extends BinaryOperation {

    Disj(Expression Lhs, Expression Rhs) {
        super(Lhs, Rhs);
        token = Constants.DISJ;
    }
}