package parser;

import static parser.Constants.*;

public class Variable extends Expression {

    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public String toString() {
        return variable;
    }

    public String toHumanString() {
        return variable;
    }

    @Override
    public String toClassString() {
        return NEW + SPACE + getRealClass().getSimpleName() + OPEN + QUOTE + variable + QUOTE + CLOSE;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Variable) {
            return variable.equals(other.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }

    @Override
    public Class<?> getRealClass() {
        return Variable.class;
    }

}
