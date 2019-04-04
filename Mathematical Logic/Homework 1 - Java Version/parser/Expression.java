package parser;

public abstract class Expression {

    private boolean isUsedInModusPonens;
    private byte status;
    private byte number;
    private int modusPonensLhsIndex;
    private int modusPonensRhsIndex;

    public boolean isModusPonens() {
        return status == Constants.MODUS_PONENS;
    }

    public void setModusPonens() {
        status = Constants.MODUS_PONENS;
    }

    public boolean isAxiom() {
        return status == Constants.AXIOM;
    }

    public void setAxiom() {
        status = Constants.AXIOM;
    }

    public boolean isHypothesis() {
        return status == Constants.HYPOTHESIS;
    }

    public void setHypothesis() {
        status = Constants.HYPOTHESIS;
    }

    public byte getNumber() {
        return number;
    }

    public void setNumber(byte number) {
        this.number = number;
    }

    public int getModusPonensLhsIndex() {
        return modusPonensLhsIndex;
    }

    public void setModusPonensLhsIndex(int modusPonensLhsIndex) {
        this.modusPonensLhsIndex = modusPonensLhsIndex;
    }

    public int getModusPonensRhsIndex() {
        return modusPonensRhsIndex;
    }

    public void setModusPonensRhsIndex(int modusPonensRhsIndex) {
        this.modusPonensRhsIndex = modusPonensRhsIndex;
    }

    public abstract Class<?> getRealClass();

    public abstract String toString();

    public abstract String toHumanString();

    public abstract boolean equals(Object other);

    public abstract int hashCode();

    public boolean isUsedInModusPonens() {
        return isUsedInModusPonens;
    }

    public void setUsedInModusPonens() {
        isUsedInModusPonens = true;
    }
}
