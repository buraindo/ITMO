package expression;

class Variable implements Expression {

    private String variable;

    Variable(String variable) {
        this.variable = variable;
    }

    public String toString() {
        return variable;
    }

}
