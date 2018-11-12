package expression;

public class CheckedSubtract<T> extends BinaryOperation<T> {

    public CheckedSubtract(TripleExpression<T> lhs, TripleExpression<T> rhs, Operation<T> operation) {
        super(lhs, rhs, operation);
    }

    @Override
    public T performOperation(T a, T b) {
        return operation.subtract(a,b);
    }

}
