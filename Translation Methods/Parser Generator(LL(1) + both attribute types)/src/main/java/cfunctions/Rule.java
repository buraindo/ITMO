
package cfunctions;

public enum Rule {
    name(false),
    OPEN(true),
    type(false),
    EPSILON(true),
    multiarg(false),
    WHITESPACE(true),
    arguments(false),
    SEMICOLON(true),
    IDENTIFIER(true),
    COMMA(true),
    args(false),
    arg(false),
    CONST(true),
    ASTERISK(true),
    CLOSE(true),
    function(false),
    variable(false),
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