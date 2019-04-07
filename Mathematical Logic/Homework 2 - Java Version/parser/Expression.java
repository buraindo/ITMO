package parser;

public abstract class Expression {

    private byte status;
    private byte number;

    public boolean isAxiom() {
        return status == Constants.AXIOM;
    }

    public void setAxiom() {
        status = Constants.AXIOM;
    }

    public byte getNumber() {
        return number;
    }

    public void setNumber(byte number) {
        this.number = number;
    }

    public abstract Class<?> getRealClass();

    public abstract String toString();

    public abstract String toHumanString();

    public abstract String toClassString();

    public abstract boolean equals(Object other);

    public abstract int hashCode();
}
