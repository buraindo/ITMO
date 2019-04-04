package expression;

public class Variable<T> implements TripleExpression<T>{

    String variable;

    public Variable (String variable) {
        this.variable = variable;
    }

    public T evaluate(T x, T y, T z) {
        if (this.variable.equals("x")) return x;
        if (this.variable.equals("y")) return y;
        return z;
    }

}
