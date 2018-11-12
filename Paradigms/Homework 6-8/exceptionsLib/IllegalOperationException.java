package exceptionsLib;

public class IllegalOperationException extends EvaluatingException {
    public IllegalOperationException(final String message) {
        super("Can't recognize operation: " + message);
    }
}