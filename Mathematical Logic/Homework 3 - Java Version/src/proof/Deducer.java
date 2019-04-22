package proof;

import parser.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Deducer {

    private final byte AXIOM_COUNT = 10;
    private final ExpressionParser parser = new ExpressionParser();
    private final Axiom[] axioms = new Axiom[AXIOM_COUNT];
    private final Expression toDeduce;
    private final List<String> answer;
    private final List<String> proof;

    private List<Hypothesis> hypotheses = new ArrayList<>();
    private Map<Expression, Set<Expression>> byRightPart = new HashMap<>();
    private Map<Expression, Integer> byIndex = new HashMap<>();
    private int globalIndex = 0;

    private void initAxioms() {
        axioms[0] = new Axiom(new Impl(new Variable("A"), new Impl(new Variable("B"), new Variable("A"))));
        axioms[1] = new Axiom(new Impl(new Impl(new Variable("A"), new Variable("B")), new Impl(new Impl(new Variable("A"), new Impl(new Variable("B"), new Variable("C"))), new Impl(new Variable("A"), new Variable("C")))));
        axioms[2] = new Axiom(new Impl(new Variable("A"), new Impl(new Variable("B"), new Conj(new Variable("A"), new Variable("B")))));
        axioms[3] = new Axiom(new Impl(new Conj(new Variable("A"), new Variable("B")), new Variable("A")));
        axioms[4] = new Axiom(new Impl(new Conj(new Variable("A"), new Variable("B")), new Variable("B")));
        axioms[5] = new Axiom(new Impl(new Variable("A"), new Disj(new Variable("A"), new Variable("B"))));
        axioms[6] = new Axiom(new Impl(new Variable("B"), new Disj(new Variable("A"), new Variable("B"))));
        axioms[7] = new Axiom(new Impl(new Impl(new Variable("A"), new Variable("B")), new Impl(new Impl(new Variable("C"), new Variable("B")), new Impl(new Disj(new Variable("A"), new Variable("C")), new Variable("B")))));
        axioms[8] = new Axiom(new Impl(new Impl(new Variable("A"), new Variable("B")), new Impl(new Impl(new Variable("A"), new Neg(new Variable("B"))), new Neg(new Variable("A")))));
        axioms[9] = new Axiom(new Impl(new Neg(new Neg(new Variable("A"))), new Variable("A")));
    }

    Deducer(List<String> proof, Expression toDeduce, List<Expression> hypotheses, List<String> external) {
        initAxioms();
        this.toDeduce = toDeduce;
        this.proof = proof;
        for (Expression e : hypotheses) {
            this.hypotheses.add(new Hypothesis(e));
        }
        answer = external;
    }

    public ExpressionParser getParser() {
        return parser;
    }

    private void deduceSelf() throws IOException {
        List<String> proof = Files.readAllLines(Paths.get("resources/self")).stream().map(s -> s.replaceAll("A", toDeduce.toString())).collect(Collectors.toList());
        answer.addAll(proof);
    }

    private void deduceHypothesisOrAxiom(Expression expression) throws IOException {
        List<String> proof = Files.readAllLines(Paths.get("resources/axhy")).stream().map(
                s -> s
                        .replaceAll("\\(A\\)", expression.toString())
                        .replaceAll("\\(B\\)", toDeduce.toString())).collect(Collectors.toList());
        answer.addAll(proof);
    }

    private void deduceModusPonens(Expression lhs, Expression rhs) throws IOException {
        List<String> proof = Files.readAllLines(Paths.get("resources/mp")).stream().map(
                s -> s
                        .replaceAll("\\(B\\)", lhs.toString())
                        .replaceAll("\\(C\\)", rhs.toString())
                        .replaceAll("\\(A\\)", toDeduce.toString())).collect(Collectors.toList());
        answer.addAll(proof);
    }

    void deduce() throws IOException {
        for (String s : proof) {
            try {
                add(s);
            } catch (NullPointerException ignored) {
                System.err.println(toDeduce);
                System.err.println(s);
            }
        }
    }

    private boolean checkSelf(Expression expression) throws IOException {
        if (expression.toString().equals(toDeduce.toString())) {
            deduceSelf();
            return addExpression(expression);
        }
        return false;
    }

    private boolean checkHypotheses(Expression expression) throws IOException {
        for (Hypothesis hypothesis : hypotheses) {
            if (hypothesis.getExpression().equals(expression)) {
                deduceHypothesisOrAxiom(expression);
                return addExpression(expression);
            }
        }
        return false;
    }

    private boolean checkAxioms(Expression expression) throws IOException {
        for (Axiom axiom : axioms) {
            if (axiom.matches(expression)) {
                deduceHypothesisOrAxiom(expression);
                return addExpression(expression);
            }
        }
        return false;
    }

    private void checkModusPonens(Expression expression) throws IOException {
        Set<Expression> expressionsByOurRightPart = byRightPart.get(expression);
        for (Expression e : expressionsByOurRightPart) {
            if (e instanceof Impl && byIndex.containsKey(((Impl) e).getLhs())) {
                deduceModusPonens(((Impl) e).getLhs(), expression);
            }
        }
        addExpression(expression);
    }

    private boolean addExpression(Expression expression) {
        if (expression instanceof Impl) {
            byRightPart.putIfAbsent(((Impl) expression).getRhs(), new HashSet<>());
            byRightPart.get(((Impl) expression).getRhs()).add(expression);
        }
        byIndex.putIfAbsent(expression, globalIndex++);
        return true;
    }

    private void add(String input) throws IOException {
        Expression expression = parser.parse(input);
        boolean result = checkSelf(expression);
        if (result) return;
        result = checkHypotheses(expression);
        if (result) return;
        result = checkAxioms(expression);
        if (result) return;
        checkModusPonens(expression);
    }

}
