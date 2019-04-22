package proof;

import parser.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Prover {

    private void proveDeepNegation(Expression expression, List<String> answer) throws IOException {
        if (!(expression instanceof Neg)) return;
        int depth = 0;
        while (expression instanceof Neg) {
            expression = ((Neg) expression).getExpression();
            depth++;
        }
        if (depth < 2) return;
        if (depth % 2 == 1) {
            expression = new Neg(expression);
            depth--;
        }
        StringBuilder expressionStr = new StringBuilder(expression.toString());
        String path = "resources/1";
        List<String> proof = Files.readAllLines(Paths.get(path));
        for (int i = 0; i < depth; i += 2) {
            final String str = expressionStr.toString();
            answer.addAll(proof.stream().map(s -> s.replaceAll("\\(A\\)", str)).collect(Collectors.toList()));
            expressionStr.insert(0, "!!");
        }
    }

    private void prove(Expression expression, List<String> answer) throws IOException {
        if (expression instanceof Neg) {
            prove(((Neg) expression).getExpression(), answer);
            expression.setState(!((Neg) expression).getExpression().state());
        } else if (expression instanceof Variable) {
            boolean state = Expression.values.get(expression.toString()) == 1;
            expression.setState(state);
            String fileName = state ? "1" : "0";
            List<String> proof = Files.readAllLines(Paths.get("resources/" + fileName)).stream().map(s -> s.replaceAll("\\(A\\)", expression.toString())).collect(Collectors.toList());
            answer.addAll(proof);
        } else if (expression instanceof BinaryOperation) {
            prove(((BinaryOperation) expression).getLhs(), answer);
            prove(((BinaryOperation) expression).getRhs(), answer);
            Expression left = ((BinaryOperation) expression).getLhs();
            Expression right = ((BinaryOperation) expression).getRhs();
            boolean leftState = left.state();
            boolean rightState = right.state();
            int leftStateInt = leftState ? 1 : 0;
            int rightStateInt = rightState ? 1 : 0;
            if (leftState) proveDeepNegation(left, answer); else proveDeepNegation(new Neg(left), answer);
            if (rightState) proveDeepNegation(right, answer); else proveDeepNegation(new Neg(right), answer);
            String fileName = Integer.toString(leftStateInt) + rightStateInt + ((BinaryOperation) expression).getToken();
            List<String> proof = Files.readAllLines(Paths.get("resources/" + fileName)).stream()
                    .map(s -> s.replaceAll("\\(A\\)", left.toString())
                            .replaceAll("\\(B\\)", right.toString()))
                    .collect(Collectors.toList());
            answer.addAll(proof);
            if (expression instanceof Impl) {
                expression.setState(!leftState | rightState);
            } else if (expression instanceof Disj) {
                expression.setState(leftState | rightState);
            } else if (expression instanceof Conj) {
                expression.setState(leftState & rightState);
            }
        }
    }

    public void proveTertium(Expression expression, Expression toProve, List<String> external) throws IOException {
        List<String> proof = Files.readAllLines(Paths.get("resources/tertium")).stream().map(s -> s.replaceAll("\\(B\\)", expression.toString())).collect(Collectors.toList());
        external.addAll(proof);
        Expression neg = new Neg(expression);
        Expression axiom8 = new Impl(new Impl(expression, toProve), new Impl(new Impl(neg, toProve), new Impl(new Disj(expression, neg), toProve)));
        external.add(axiom8.toString());
        Expression mp1 = new Impl(new Impl(neg, toProve), new Impl(new Disj(expression, neg), toProve));
        external.add(mp1.toString());
        Expression mp2 = new Impl(new Disj(expression, neg), toProve);
        external.add(mp2.toString());
        external.add(toProve.toString());
    }

    public List<String> proveExpression(Expression expression) throws IOException {
        List<String> proof = new ArrayList<>();
        prove(expression, proof);
        proveDeepNegation(expression, proof);
        return proof;
    }

    public void deduceProof(List<String> proof, Expression toDeduce, List<Expression> hypotheses, List<String> external) throws IOException {
        Deducer deducer = new Deducer(proof, toDeduce, hypotheses, external);
        deducer.deduce();
    }

}
