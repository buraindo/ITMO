package proof;

import parser.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

import static parser.Constants.*;
import static util.Util.addToJoiner1;
import static util.Util.addToJoiner2;
import static util.Util.addToJoiner3;
import static util.Util.addToJoiner4;
import static util.Util.addToJoiner5;
import static util.Util.addToJoiner6;
import static util.Util.addToJoiner7;
import static util.Util.addToJoiner8;
import static util.Util.addToJoiner9;
import static util.Util.addToJoiner10;

@SuppressWarnings("Duplicates")
public class Proof {

    private final byte AXIOM_COUNT = 10;
    private final ExpressionParser parser = new ExpressionParser();
    private final Axiom[] axioms = new Axiom[AXIOM_COUNT];

    private List<Hypothesis> hypotheses = new ArrayList<>();
    private BufferedWriter writer;
    private Map<Expression, Set<Expression>> byRightPart = new HashMap<>();
    private Map<Expression, Integer> byIndex = new HashMap<>();
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

    private static Neg doubleNegation(Expression expression) {
        return new Neg(new Neg(expression));
    }

    public Proof(BufferedWriter writer) {
        this.writer = writer;
        initAxioms();
    }

    private void deduceTenthAxiom(Expression expression) throws IOException {
        StringJoiner joiner = new StringJoiner(NEWLINE);
        Expression a = ((Impl)expression).getRhs();
        Expression t = new Neg(new Impl(doubleNegation(a), a));
        Expression x = new Impl(new Impl(a, new Impl(doubleNegation(a), a)), new Impl(new Impl(a, new Neg(new Impl(doubleNegation(a), a))), new Neg(a)));
        Expression y = new Impl(a, new Impl(doubleNegation(a), a));
        joiner.add(new Impl(new Impl(t, new Neg(a)), new Impl(new Impl(t, doubleNegation(a)), doubleNegation(expression))).toHumanString());
        joiner.add(x.toHumanString());
        joiner.add(new Impl(x, new Impl(t, x)).toHumanString());
        joiner.add(new Impl(t,x).toHumanString());
        joiner.add(y.toHumanString());
        joiner.add(new Impl(y, new Impl(t, y)).toHumanString());
        joiner.add(new Impl(t ,y).toHumanString());
        joiner.add(new Impl(new Impl(t, y), new Impl(new Impl(t, x), new Impl(t, new Impl(new Impl(a, new Neg(new Impl(doubleNegation(a), a))), new Neg(a))))).toHumanString());
        joiner.add(new Impl(new Impl(t, x), new Impl(t, new Impl(new Impl(a, new Neg(new Impl(doubleNegation(a), a))), new Neg(a)))).toHumanString());
        joiner.add(new Impl(t, new Impl(new Impl(a, new Neg(new Impl(doubleNegation(a), a))), new Neg(a))).toHumanString());
        joiner.add(new Impl(t, new Impl(t, t)).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(t, t)), new Impl(new Impl(t, new Impl(new Impl(t, t), t)),new Impl(t, t))).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(new Impl(t, t), t)),new Impl(t, t)).toHumanString());
        joiner.add(new Impl(t, new Impl(new Impl(t, t), t)).toHumanString());
        joiner.add(new Impl(t, t).toHumanString());
        joiner.add(new Impl(t, new Impl(a,t)).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(a,t)), new Impl(t, new Impl(t, new Impl(a,t)))).toHumanString());
        joiner.add(new Impl(t, new Impl(t, new Impl(a,t))).toHumanString());
        joiner.add(new Impl(new Impl(t, t), new Impl(new Impl(t, new Impl(t, new Impl(a,t))), new Impl(t, new Impl(a,t)))).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(t, new Impl(a,t))), new Impl(t, new Impl(a,t))).toHumanString());
        joiner.add(new Impl(t, new Impl(a,t)).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(a,t)), new Impl(new Impl(t, new Impl(new Impl(a, t), new Neg(a))) ,new Impl(t, new Neg(a)))).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(new Impl(a, t), new Neg(a))) ,new Impl(t, new Neg(a))).toHumanString());
        joiner.add(new Impl(t, new Neg(a)).toHumanString());
        t = new Neg(new Impl(doubleNegation(a), a));
        x = new Impl(new Impl(new Neg(a), new Impl(doubleNegation(a), a)), new Impl(new Impl(new Neg(a), new Neg(new Impl(doubleNegation(a), a))), doubleNegation(a)));
        y = new Impl(new Neg(a), new Impl(doubleNegation(a), a));
        joiner.add(x.toHumanString());
        joiner.add(new Impl(x, new Impl(t, x)).toHumanString());
        joiner.add(new Impl(t,x).toHumanString());
        joiner.add(y.toHumanString());
        joiner.add(new Impl(y, new Impl(t, y)).toHumanString());
        joiner.add(new Impl(t ,y).toHumanString());
        joiner.add(new Impl(new Impl(t, y), new Impl(new Impl(t, x), new Impl(t, new Impl(new Impl(new Neg(a), new Neg(new Impl(doubleNegation(a), a))), doubleNegation(a))))).toHumanString());
        joiner.add(new Impl(new Impl(t, x), new Impl(t, new Impl(new Impl(new Neg(a), new Neg(new Impl(doubleNegation(a), a))), doubleNegation(a)))).toHumanString());
        joiner.add(new Impl(t, new Impl(new Impl(new Neg(a), new Neg(new Impl(doubleNegation(a), a))), doubleNegation(a))).toHumanString());
        joiner.add(new Impl(t, new Impl(t, t)).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(t, t)), new Impl(new Impl(t, new Impl(new Impl(t, t), t)),new Impl(t, t))).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(new Impl(t, t), t)),new Impl(t, t)).toHumanString());
        joiner.add(new Impl(t, new Impl(new Impl(t, t), t)).toHumanString());
        joiner.add(new Impl(t, t).toHumanString());
        joiner.add(new Impl(t, new Impl(new Neg(a),t)).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(new Neg(a),t)), new Impl(t, new Impl(t, new Impl(new Neg(a),t)))).toHumanString());
        joiner.add(new Impl(t, new Impl(t, new Impl(new Neg(a),t))).toHumanString());
        joiner.add(new Impl(new Impl(t, t), new Impl(new Impl(t, new Impl(t, new Impl(new Neg(a),t))), new Impl(t, new Impl(new Neg(a),t)))).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(t, new Impl(new Neg(a),t))), new Impl(t, new Impl(new Neg(a),t))).toHumanString());
        joiner.add(new Impl(t, new Impl(new Neg(a),t)).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(new Neg(a),t)), new Impl(new Impl(t, new Impl(new Impl(new Neg(a), t), doubleNegation(a))) ,new Impl(t, doubleNegation(a)))).toHumanString());
        joiner.add(new Impl(new Impl(t, new Impl(new Impl(new Neg(a), t), doubleNegation(a))) ,new Impl(t, doubleNegation(a))).toHumanString());
        joiner.add(new Impl(t, doubleNegation(a)).toHumanString());
        joiner.add(new Impl(new Impl(t, doubleNegation(a)), doubleNegation(expression)).toHumanString());
        joiner.add(doubleNegation(expression).toHumanString());
        writer.write(joiner.toString());
        writer.newLine();
    }

    private void deduceAxiomOrHypothesis(Expression expression) throws IOException {
        if (expression.isAxiom() && expression.getNumber() == AXIOM_COUNT) {
            deduceTenthAxiom(expression);
            return;
        }
        StringJoiner joiner = new StringJoiner(NEWLINE);
        joiner.add(expression.toHumanString());
        joiner.add(new Impl(expression, new Impl(new Neg(expression), expression)).toHumanString());
        joiner.add(new Impl(new Neg(expression), expression).toHumanString());
        joiner.add(new Impl(new Neg(expression), new Impl(new Neg(expression), new Neg(expression))).toHumanString());
        joiner.add(new Impl(new Impl(new Neg(expression), new Impl(new Neg(expression), new Neg(expression))), new Impl(new Impl(new Neg(expression), new Impl(new Impl(new Neg(expression), new Neg(expression)), new Neg(expression))), new Impl(new Neg(expression), new Neg(expression)))).toHumanString());
        joiner.add(new Impl(new Impl(new Neg(expression), new Impl(new Impl(new Neg(expression), new Neg(expression)), new Neg(expression))), new Impl(new Neg(expression), new Neg(expression))).toHumanString());
        joiner.add(new Impl(new Neg(expression), new Impl(new Impl(new Neg(expression), new Neg(expression)), new Neg(expression))).toHumanString());
        joiner.add(new Impl(new Neg(expression), new Neg(expression)).toHumanString());
        joiner.add(new Impl(new Impl(new Neg(expression), expression), new Impl(new Impl(new Neg(expression), new Neg(expression)), doubleNegation(expression))).toHumanString());
        joiner.add(new Impl(new Impl(new Neg(expression), new Neg(expression)), doubleNegation(expression)).toHumanString());
        joiner.add(doubleNegation(expression).toHumanString());
        writer.write(joiner.toString());
        writer.newLine();
    }

    private void deduceModusPonens(Expression expression, Expression lhs) throws IOException {
        StringJoiner joiner = new StringJoiner(NEWLINE);
        addToJoiner1(expression, lhs, joiner);
        addToJoiner2(expression, lhs, joiner);
        addToJoiner3(expression, lhs, joiner);
        addToJoiner4(expression, lhs, joiner);
        addToJoiner5(expression, lhs, joiner);
        addToJoiner6(expression, lhs, joiner);
        addToJoiner7(expression, lhs, joiner);
        addToJoiner8(expression, lhs, joiner);
        addToJoiner9(expression, lhs, joiner);
        addToJoiner10(expression, lhs, joiner);
        writer.write(joiner.toString());
        writer.newLine();
    }

    private boolean checkHypotheses(Expression expression) throws IOException {
        for (Hypothesis hypothesis : hypotheses) {
            if (hypothesis.getExpression().equals(expression)) {
                deduceAxiomOrHypothesis(expression);
                addExpression(expression);
                return true;
            }
        }
        return false;
    }

    private boolean checkAxioms(Expression expression) throws IOException {
        for (Axiom axiom : axioms) {
            if (axiom.matches(expression)) {
                expression.setAxiom();
                expression.setNumber(axiom.getNumber());
                deduceAxiomOrHypothesis(expression);
                addExpression(expression);
                return true;
            }
        }
        return false;
    }

    private void checkModusPonens(Expression expression) throws IOException {
        Set<Expression> expressionsByOurRightPart = byRightPart.get(expression);
        for (Expression e : expressionsByOurRightPart) {
            if (e instanceof Impl && byIndex.containsKey(((Impl) e).getLhs())) {
                deduceModusPonens(expression, ((Impl) e).getLhs());
                addExpression(expression);
            }
        }
    }

    private void addExpression(Expression expression) {
        if (expression instanceof Impl) {
            byRightPart.putIfAbsent(((Impl) expression).getRhs(), new HashSet<>());
            byRightPart.get(((Impl) expression).getRhs()).add(expression);
        }
        byIndex.putIfAbsent(expression, globalIndex++);
    }

    private void setHypothesisAndStatement(String input) throws IOException {
        int index = input.indexOf(TURNSTILE);
        String hypothesesStr = EMPTY;
        StringJoiner joiner = new StringJoiner(COMMA + SPACE);
        Expression statement;
        if (index == 0) {
            statement = doubleNegation(parser.parse(input.substring(2)));
        } else {
            String[] hypotheses = input.substring(0, index).split(COMMA);
            statement = doubleNegation(parser.parse(input.substring(index + 2)));
            int i = 1;
            for (String hypothesis : hypotheses) {
                Hypothesis h = new Hypothesis(parser.parse(hypothesis), (byte) i++);
                this.hypotheses.add(h);
                joiner.add(h.toHumanString());
            }
            hypothesesStr = joiner.toString() + SPACE;
        }
        writer.write( hypothesesStr + TURNSTILE + SPACE);
        writer.write(statement.toHumanString());
        writer.newLine();
    }

    public void add(String input, int count) throws IOException {
        if (count == 0) {
            setHypothesisAndStatement(input);
            return;
        }
        Expression expression = parser.parse(input);
        boolean result = checkHypotheses(expression);
        if (result) return;
        result = checkAxioms(expression);
        if (result) return;
        checkModusPonens(expression);
    }

}
