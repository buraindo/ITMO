package expression;

public abstract class BinaryOperation<T> implements TripleExpression<T> {

    private TripleExpression<T> lhs, rhs;
    Operation<T> operation;

    public T evaluate(final T x, final T y, final T z) {
        return performOperation(lhs.evaluate(x, y, z), rhs.evaluate(x, y, z));
    }

    BinaryOperation (TripleExpression<T> Lhs, TripleExpression<T> Rhs, Operation<T> Operation) {
        lhs = Lhs;
        rhs = Rhs;
        operation = Operation;
    }

    public abstract T performOperation(final T a, final T b);

}
