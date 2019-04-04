package expression;

public class CheckedMultiply<T> extends BinaryOperation<T> {

    public CheckedMultiply(TripleExpression<T> lhs, TripleExpression<T> rhs, Operation<T> operation) {
        super(lhs, rhs, operation);
    }

    @Override
    public T performOperation(T a, T b) {
        return operation.multiply(a,b);
    }

}
