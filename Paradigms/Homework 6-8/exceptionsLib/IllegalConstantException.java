package exceptionsLib;

public class IllegalConstantException extends ParsingException {
    public IllegalConstantException(final String reason, final String s, final int ind) {
        super("Constant '" + reason + "' is incorrect for integer parsing at pos: " + ind + "\n" + s + "\n" + getPlace(ind, reason.length()));
    }
}