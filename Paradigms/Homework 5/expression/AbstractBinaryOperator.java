package hw5.expression;

public abstract class AbstractBinaryOperator implements AnyExpression {
    private final AnyExpression fo;
    private final AnyExpression so;

    public AbstractBinaryOperator(AnyExpression x, AnyExpression y) {
        fo = x;
        so = y;
    }

    protected abstract double apply(double x, double y);

    protected abstract int apply(int x, int y);

    public double evaluate(double x) {
        return apply(fo.evaluate(x), so.evaluate(x));
    }

    public int evaluate(int x) {
        return apply(fo.evaluate(x), so.evaluate(x));
    }
}
