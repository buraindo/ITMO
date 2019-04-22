import parser.*;
import proof.HypothesesFinder;
import proof.Prover;
import util.Util;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static void sadSmiley() {
        System.out.println(":(");
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            ExpressionParser parser = new ExpressionParser();
            Expression expression = parser.parse(line);
            HypothesesFinder hypothesesFinder = new HypothesesFinder(expression);
            boolean positive = true;
            int hypotheses = hypothesesFinder.getHypotheses(true);
            if (hypotheses == Constants.FAILED) {
                expression = new Neg(expression);
                hypothesesFinder.setExpression(expression);
                positive = false;
                hypotheses = hypothesesFinder.getHypotheses(false);
                if (hypotheses == Constants.FAILED) {
                    sadSmiley();
                    return;
                }
            }
            Queue<Integer> path = hypothesesFinder.getPath(hypotheses, positive);
            Queue<Integer> deductionPath = new ArrayDeque<>();
            Queue<List<String>> proofs = new ArrayDeque<>();
            Prover prover = new Prover();
            int size = Expression.values.size();
            BufferedWriter writer = new BufferedWriter(new PrintWriter(System.out));
            writer.write(Util.printSummary(hypotheses, positive));
            writer.write(expression.toString());
            writer.newLine();
            if (path.size() == 1) {
                Util.setVariables(hypotheses);
                List<String> proof = prover.proveExpression(expression);
                proofs.offer(proof);
            }
            while (path.size() > 1) {
                int input1 = path.remove();
                int input2 = path.remove();
                int index = -1;
                Expression first = null, second = null;
                for (int i = 0; i < size; i++) {
                    int ithBit1 = (input1 >> (size - i - 1)) & 1;
                    int ithBit2 = (input2 >> (size - i - 1)) & 1;
                    if ((ithBit1 ^ ithBit2) == 1) {
                        second = new Variable(Expression.indexToVariable.get(i));
                        first = new Neg(second);
                        index = i;
                        break;
                    }
                }
                List<String> proof = new ArrayList<>();
                List<Expression> hypothesesList = Util.expressionsByMask(input1 & input2, index);
                Util.setVariables(input1);
                List<String> proof1 = prover.proveExpression(expression);
                prover.deduceProof(proof1, first, hypothesesList, proof);
                Util.setVariables(input2);
                List<String> proof2 = prover.proveExpression(expression);
                prover.deduceProof(proof2, second, hypothesesList, proof);
                prover.proveTertium(second, expression, proof);
                deductionPath.offer(input1 & input2);
                proofs.offer(proof);
            }
            Queue<Integer> lastDeductionPath = new ArrayDeque<>();
            while (deductionPath.size() > 1) {
                int input1 = deductionPath.remove();
                int input2 = deductionPath.remove();
                int index = -1;
                Expression first = null, second = null;
                for (int i = 0; i < size; i++) {
                    int ithBit1 = (input1 >> (size - i - 1)) & 1;
                    int ithBit2 = (input2 >> (size - i - 1)) & 1;
                    if ((ithBit1 ^ ithBit2) == 1) {
                        second = new Variable(Expression.indexToVariable.get(i));
                        first = new Neg(second);
                        index = i;
                        break;
                    }
                }
                List<Expression> hypothesesList = Util.expressionsByMask(input1 & input2, index);
                Util.setVariables(input1);
                List<String> proof = new ArrayList<>();
                prover.deduceProof(proofs.remove(), first, hypothesesList, proof);
                Util.setVariables(input2);
                prover.deduceProof(proofs.remove(), second, hypothesesList, proof);
                prover.proveTertium(second, expression, proof);
                lastDeductionPath.offer(input1 & input2);
                proofs.offer(proof);
            }
            while (lastDeductionPath.size() > 1) {
                int input1 = lastDeductionPath.remove();
                int input2 = lastDeductionPath.remove();
                int index = -1;
                Expression first = null, second = null;
                for (int i = 0; i < size; i++) {
                    int ithBit1 = (input1 >> (size - i - 1)) & 1;
                    int ithBit2 = (input2 >> (size - i - 1)) & 1;
                    if ((ithBit1 ^ ithBit2) == 1) {
                        second = new Variable(Expression.indexToVariable.get(i));
                        first = new Neg(second);
                        index = i;
                        break;
                    }
                }
                List<Expression> hypothesesList = Util.expressionsByMask(input1 & input2, index);
                Util.setVariables(input1);
                List<String> proof = new ArrayList<>();
                prover.deduceProof(proofs.remove(), first, hypothesesList, proof);
                Util.setVariables(input2);
                prover.deduceProof(proofs.remove(), second, hypothesesList, proof);
                prover.proveTertium(second, expression, proof);
                proofs.offer(proof);
            }
            for (List<String> p : proofs) {
                for (String l : p) {
                    writer.write(l);
                    writer.newLine();
                }
            }
            writer.close();
        }
    }
}
