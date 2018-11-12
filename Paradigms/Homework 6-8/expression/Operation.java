package expression;

public interface Operation<T> {

    T parse(final String stringRepresentation);

    T add(final T lhs, final T rhs);

    T subtract(final T lhs, final T rhs);

    T multiply(final T lhs, final T rhs);

    T divide(final T lhs, final T rhs);

    T negate(final T number);

}
