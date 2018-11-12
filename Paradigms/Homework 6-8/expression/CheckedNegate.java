package expression;

public class CheckedNegate<T> extends UnaryOperation<T> {

    public CheckedNegate(TripleExpression<T> number, Operation<T> operation) {
        super(number, operation);
    }

    public T performOperation(T a) {
        return operation.negate(a);
    }

}

