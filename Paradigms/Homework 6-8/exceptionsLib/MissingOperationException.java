package exceptionsLib;

public class MissingOperationException extends ParsingException {
    public MissingOperationException(final String s, final int ind) {
        super("Missing operation at position: " + ind + "\n" + s + "\n" + getPlace(ind, 1));
    }
}