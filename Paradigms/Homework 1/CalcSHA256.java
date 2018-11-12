import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public class CalcSHA256 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
            for (int i = 0; i != lines.size(); i++) {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                digest.reset();
                if (Files.size(Paths.get(lines.get(i))) != 0) {
                    digest.update(new Scanner(new File(lines.get(i)), "utf8").useDelimiter("\\Z").next().getBytes("utf8"));
                }
                System.out.printf("%064x\n", new BigInteger(1, digest.digest()));
            }
        } catch (IOException ex) {
            System.err.println("Something bad has occured with input data");
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Such algorithm doesn't exist");
        }
    }
}
