package expression;

public class CheckedAdd<T> extends BinaryOperation<T> {

    public CheckedAdd(TripleExpression<T> lhs, TripleExpression<T> rhs, Operation<T> operation) {
        super(lhs, rhs, operation);
    }

    @Override
    public T performOperation(T a, T b) {
        return operation.add(a,b);
    }
}
