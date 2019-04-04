package expression;

public abstract class UnaryOperation<T> implements TripleExpression<T> {

    TripleExpression<T> Expression;
    Operation<T> operation;

    UnaryOperation (TripleExpression<T> Expression, Operation<T> operation) {
        this.Expression = Expression;
        this.operation = operation;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return performOperation(Expression.evaluate(x, y, z));
    }

    public abstract T performOperation(final T num);

}
