package parser;

public class Variable implements Expression {

    private String variable;
    private boolean state;

    public Variable(String variable, boolean f) {
        this.variable = variable;
        values.putIfAbsent(variable, 0);
        if (values.size() > indexToVariable.size()) {
            indexToVariable.putIfAbsent(indexToVariable.size(), variable);
        }
    }

    public Variable(String variable) {
        this.variable = variable;
    }

    public String toString() {
        return variable;
    }

    @Override
    public boolean evaluate() {
        return values.get(variable) == 1;
    }

    @Override
    public boolean state() {
        return state;
    }

    @Override
    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public Class<?> getRealClass() {
        return Variable.class;
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

}
