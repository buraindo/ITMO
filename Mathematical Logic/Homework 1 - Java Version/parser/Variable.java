package parser;

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
