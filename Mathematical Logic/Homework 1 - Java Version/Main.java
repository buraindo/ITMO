import proof.Proof;

import java.io.*;

public class Main {

    private static void proofIsIncorrect() {
        System.out.println("Proof is incorrect");
    }

    public static void main(String[] args) throws IOException {
        Proof proof = new Proof();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(System.out));
        String line = reader.readLine();
        int count = 0;
        while (line != null) {
            if (!proof.tryAdd(line, count++)) {
                proofIsIncorrect();
                return;
            }
            line = reader.readLine();
        }
        proof.findUsed();
        proof.getProof(writer);
    }
}
