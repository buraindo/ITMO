package proof;

import parser.BinaryOperation;
import parser.Expression;
import parser.Neg;
import parser.Variable;

import java.util.HashMap;
import java.util.Map;

public class Axiom {

    private final Expression expression;
    private final byte number;
    private final Map<String, Expression> variableValue = new HashMap<>();

    byte getNumber() {
        return number;
    }

    public Expression getExpression() {
        return expression;
    }

    Axiom(Expression expression, byte number) {
        this.expression = expression;
        this.number = number;
    }

    private boolean matches(Expression lhs, Expression rhs) {
        if (rhs instanceof BinaryOperation) {
            if (lhs.getRealClass() == rhs.getRealClass()) {
                return matches(((BinaryOperation) lhs).getLhs(), ((BinaryOperation) rhs).getLhs()) &&
                        matches(((BinaryOperation) lhs).getRhs(), ((BinaryOperation) rhs).getRhs());
            }
            return false;
        }
        if (rhs instanceof Neg) {
            if (lhs.getRealClass() == rhs.getRealClass()) {
                return matches(((Neg) lhs).getExpression(), ((Neg) rhs).getExpression());
            }
            return false;
        }
        if (rhs instanceof Variable) {
            String letter = rhs.toHumanString();
            if (!variableValue.containsKey(letter)) {
                variableValue.put(letter, lhs);
            } else {
                return variableValue.get(letter).equals(lhs);
            }
        }
        return true;
    }

    boolean matches(Expression assumption) {
        variableValue.clear();
        return matches(assumption, expression);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Axiom) {
            Axiom axiom = (Axiom) other;
            return expression.equals(axiom.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
}
