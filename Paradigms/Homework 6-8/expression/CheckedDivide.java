package expression;

public class CheckedDivide<T> extends BinaryOperation<T> {

    public CheckedDivide(TripleExpression<T> lhs, TripleExpression<T> rhs, Operation<T> operation) {
        super(lhs, rhs, operation);
    }

    @Override
    public T performOperation(T a, T b) {
        return operation.divide(a,b);
    }

}
