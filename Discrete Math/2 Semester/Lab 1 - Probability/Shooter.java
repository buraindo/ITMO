//import java.io.*;
//
//public class Shooter {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("shooter.in")));
//        FileWriter writer = new FileWriter("shooter.out");
//        int n, m, k;
//        String[] str = reader.readLine().split(" ");
//        n = Integer.parseInt(str[0]);
//        m = Integer.parseInt(str[1]);
//        k = Integer.parseInt(str[2]);
//        double sumProb = 0.0;
//        double prob = 0.0;
//        str = reader.readLine().split(" ");
//        for (int i = 0; i != str.length; i++) {
//            double localProb = 1 - Double.parseDouble(str[i]);
//            localProb = Math.pow(localProb, m);
//            if (i == k - 1) prob = localProb;
//            sumProb += localProb;
//        }
//        writer.write(Double.toString(prob / sumProb));
//        writer.close();
//    }
//}
