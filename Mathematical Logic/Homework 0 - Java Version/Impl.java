package expression;

class Impl extends BinaryOperation {

    Impl(Expression Lhs, Expression Rhs) {
        super(Lhs, Rhs);
        token = Constants.IMPL;
    }
}
