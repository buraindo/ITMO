package exceptionsLib;

public class OverflowException extends EvaluatingException {
    public OverflowException() {
        super("overflow");
    }
}