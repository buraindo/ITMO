package exceptionsLib;

public class OddClosingParenthesisException extends ParsingException {
    public OddClosingParenthesisException(final String s, int ind) {
        super("You have an extra opening bracket at position: " + ind + "\n" + s + "\n" + getPlace(ind, 1));
    }
}