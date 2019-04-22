import parser.*;
import proof.HypothesesFinder;
import proof.Prover;
import util.Generator;
import util.Util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Test {

    private static void sadSmiley() {
        System.out.println(":(");
    }

    private static void compare() throws IOException {
        try (BufferedWriter writer1 = Files.newBufferedWriter(Paths.get("result1.txt"));
             BufferedWriter writer2 = Files.newBufferedWriter(Paths.get("result2.txt"));
             BufferedReader reader1 = Files.newBufferedReader(Paths.get("output.txt"));
             BufferedReader reader2 = Files.newBufferedReader(Paths.get("/home/buraindo/Documents/university trash/mathlog_hw3/D/output.txt"))) {
            for (int i = 0; i < 1000000; i++) {
                String[] line1 = reader1.readLine().split("\\|-")[0].split(",");
                String[] line2 = reader2.readLine().split("\\|-")[0].split(",");
                if (line1.length != line2.length) {
                    for (String s : line1)
                        writer1.write(s);
                    writer1.newLine();
                    for (String s : line2)
                        writer2.write(s);
                    writer2.newLine();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        compare();
//        if (true) return;
//        Generator.generate();
//        List<String> lines = Files.readAllLines(Paths.get("generated.txt"));
//        BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"), StandardOpenOption.APPEND);
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("output1.txt"));
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"))) {
//            for (String s : lines) {
                String line = reader.readLine();
//                String line = s;
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
//                        writer.write(":(\n");
//                        continue;
                    }
                }
                Queue<Integer> path = hypothesesFinder.getPath(hypotheses, positive);
                Queue<Integer> deductionPath = new ArrayDeque<>();
                Queue<List<String>> proofs = new ArrayDeque<>();
                Prover prover = new Prover();
                int size = Expression.values.size();
                writer.write(Util.printSummary(hypotheses, positive));
                writer.write(expression.toString());
                writer.newLine();

                //System.out.println(Util.printSummary(hypotheses, positive) + expression.toString());

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
//            }
            writer.close();
        }
    }
}
