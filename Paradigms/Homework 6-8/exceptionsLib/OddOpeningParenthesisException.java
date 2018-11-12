package exceptionsLib;

public class OddOpeningParenthesisException extends ParsingException {
    public OddOpeningParenthesisException(final String s, final int ind) {
        super("You are missing a closing bracket at position: " + ind + "\n" + s + "\n" + getPlace(ind, 1));
    }
}