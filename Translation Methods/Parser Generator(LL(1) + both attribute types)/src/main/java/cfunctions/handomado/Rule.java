package cfunctions.handomado;

public enum Rule {
    function(false),
    type(false),
    name(false),
    arguments(false),
    arg(false),
    variable(false),
    args(false),
    multiarg(false),
    IDENTIFIER(true),
    CONST(true),
    COMMA(true),
    OPEN(true),
    CLOSE(true),
    SEMICOLON(true),
    ASTERISK(true),
    WHITESPACE(true),
    EPSILON(true),
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
