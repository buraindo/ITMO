import proof.Proof;

import java.io.*;

@SuppressWarnings("Duplicates")
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(System.out));
        Proof proof = new Proof(writer);
        String line = reader.readLine();
        int count = 0;
        while (line != null) {
            proof.add(line, count++);
            line = reader.readLine();
        }
        reader.close();
        writer.close();
    }
}
