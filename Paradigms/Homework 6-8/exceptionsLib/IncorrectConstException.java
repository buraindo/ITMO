package exceptionsLib;

public class IncorrectConstException extends ParsingException {
    public IncorrectConstException() {
        super("Incorrect constant expression");
    }
}