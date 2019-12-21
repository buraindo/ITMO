package calculator;
public enum Rule {
    OPEN(true),
    e(false),
    f(false),
    DIVIDE(true),
    ePrime(false),
    t(false),
    EPSILON(true),
    tPrime(false),
    MULTIPLY(true),
    NUMBER(true),
    PLUS(true),
    WHITESPACES(true),
    MINUS(true),
    CLOSE(true),
    EOF(true),
    DUMMY(true);
                    
    private final boolean isTerminal;

    Rule(final boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    public boolean isTerminal() {
        return isTerminal;
    }
}