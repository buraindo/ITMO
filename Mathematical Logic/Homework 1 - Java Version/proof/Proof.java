package proof;

import parser.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

import static parser.Constants.*;

public class Proof {

    private final byte AXIOM_COUNT = 10;
    private final ExpressionParser parser = new ExpressionParser();
    private final Axiom[] axioms = new Axiom[AXIOM_COUNT];

    private List<Hypothesis> hypotheses = new ArrayList<>();
    private Map<Expression, Set<Expression>> byRightPart = new HashMap<>();
    private Map<Expression, Integer> byIndex = new HashMap<>();
    private Map<Integer, Integer> byOldIndex = new HashMap<>();
    private List<Expression> proof = new ArrayList<>();
    private Expression statement;
    private int globalIndex = 0;

    private void initAxioms() {
        axioms[0] = new Axiom(new Impl(new Variable("A"), new Impl(new Variable("B"), new Variable("A"))), (byte) 1);
        axioms[1] = new Axiom(new Impl(new Impl(new Variable("A"), new Variable("B")), new Impl(new Impl(new Variable("A"), new Impl(new Variable("B"), new Variable("C"))), new Impl(new Variable("A"), new Variable("C")))), (byte) 2);
        axioms[2] = new Axiom(new Impl(new Variable("A"), new Impl(new Variable("B"), new Conj(new Variable("A"), new Variable("B")))), (byte) 3);
        axioms[3] = new Axiom(new Impl(new Conj(new Variable("A"), new Variable("B")), new Variable("A")), (byte) 4);
        axioms[4] = new Axiom(new Impl(new Conj(new Variable("A"), new Variable("B")), new Variable("B")), (byte) 5);
        axioms[5] = new Axiom(new Impl(new Variable("A"), new Disj(new Variable("A"), new Variable("B"))), (byte) 6);
        axioms[6] = new Axiom(new Impl(new Variable("B"), new Disj(new Variable("A"), new Variable("B"))), (byte) 7);
        axioms[7] = new Axiom(new Impl(new Impl(new Variable("A"), new Variable("B")), new Impl(new Impl(new Variable("C"), new Variable("B")), new Impl(new Disj(new Variable("A"), new Variable("C")), new Variable("B")))), (byte) 8);
        axioms[8] = new Axiom(new Impl(new Impl(new Variable("A"), new Variable("B")), new Impl(new Impl(new Variable("A"), new Neg(new Variable("B"))), new Neg(new Variable("A")))), (byte) 9);
        axioms[9] = new Axiom(new Impl(new Neg(new Neg(new Variable("A"))), new Variable("A")), (byte) 10);
    }

    public Proof() {
        initAxioms();
    }

    public ExpressionParser getParser() {
        return parser;
    }

    private void useInModusPonens(Expression expression) {
        expression.setUsedInModusPonens();
        if (expression.isModusPonens()) {
            useInModusPonens(proof.get(expression.getModusPonensLhsIndex()));
            useInModusPonens(proof.get(expression.getModusPonensRhsIndex()));
        }
    }

    public void findUsed() {
        useInModusPonens(proof.get(proof.size() - 1));
    }

    public void getProof(BufferedWriter writer) throws IOException {
        if (!proof.get(proof.size() - 1).equals(statement)) {
            writer.write("Proof is incorrect");
            writer.close();
            return;
        }
        StringBuilder sb = new StringBuilder();
        StringJoiner joiner = new StringJoiner(COMMA + SPACE);
        hypotheses.forEach(h -> joiner.add(h.toHumanString()));
        String hypotheses = joiner.toString();
        if (hypotheses.length() > 0)
            sb.append(joiner.toString()).append(SPACE);
        sb.append(TURNSTILE).append(SPACE).append(statement.toHumanString()).append(NEWLINE);
        writer.write(sb.toString());
        int count = 1;
        for (Expression current : proof) {
            if (current.isUsedInModusPonens()) {
                byOldIndex.put(byIndex.get(current), count);
                writer.write(String.format("[%d. ", count++));
                if (current.isHypothesis()) {
                    writer.write(String.format("Hypothesis %d", current.getNumber()) + "] " + current.toHumanString());
                } else if (current.isAxiom()) {
                    writer.write(String.format("Ax. sch. %d", current.getNumber()) + "] " + current.toHumanString());
                } else if (current.isModusPonens()) {
                    writer.write(String.format("M.P. %d, %d", byOldIndex.get(current.getModusPonensRhsIndex()), byOldIndex.get(current.getModusPonensLhsIndex())) + "] " + current.toHumanString());
                }
                writer.write(NEWLINE);
            }
        }
        writer.close();
    }

    private boolean checkHypotheses(Expression expression) {
        for (Hypothesis hypothesis : hypotheses) {
            if (hypothesis.getExpression().equals(expression)) {
                expression.setHypothesis();
                return addExpression(expression, hypothesis.getNumber());
            }
        }
        return false;
    }

    private boolean checkAxioms(Expression expression) {
        for (Axiom axiom : axioms) {
            if (axiom.matches(expression)) {
                expression.setAxiom();
                return addExpression(expression, axiom.getNumber());
            }
        }
        return false;
    }

    private boolean addExpression(Expression expression, byte number) {
        expression.setNumber(number);
        return addExpression(expression);
    }

    private boolean checkModusPonens(Expression expression) {
        if (!byRightPart.containsKey(expression)) {
            return false;
        }
        Set<Expression> expressionsByOurRightPart = byRightPart.get(expression);
        for (Expression e : expressionsByOurRightPart) {
            if (e instanceof Impl && byIndex.containsKey(((Impl) e).getLhs())) {
                Integer index = byIndex.get(((Impl) e).getLhs());
                expression.setModusPonens();
                expression.setModusPonensLhsIndex(index);
                expression.setModusPonensRhsIndex(byIndex.get(e));
                return addExpression(expression);
            }
        }
        return false;
    }

    private boolean addExpression(Expression expression) {
        proof.add(expression);
        if (expression instanceof Impl) {
            byRightPart.putIfAbsent(((Impl) expression).getRhs(), new HashSet<>());
            byRightPart.get(((Impl) expression).getRhs()).add(expression);
        }
        byIndex.putIfAbsent(expression, globalIndex++);
        return true;
    }

    private boolean setHypothesisAndStatement(String input) {
        if (input.contains(TURNSTILE)) {
            int index = input.indexOf(TURNSTILE);
            if (index == 0) {
                statement = parser.parse(input.substring(2));
            } else {
                String[] hypotheses = input.substring(0, index).split(COMMA);
                statement = parser.parse(input.substring(index + 2));
                int i = 1;
                for (String hypothesis : hypotheses) {
                    this.hypotheses.add(new Hypothesis(parser.parse(hypothesis), (byte) i++));
                }
            }
            return true;
        }
        return false;
    }

    public boolean tryAdd(String input, int count) {
        if (count == 0) {
            return setHypothesisAndStatement(input);
        }
        Expression expression = parser.parse(input);
        boolean result = checkHypotheses(expression);
        if (result) return true;
        result = checkAxioms(expression);
        if (result) return true;
        result = checkModusPonens(expression);
        return result;
    }

}
