//import java.io.*;
//
//public class Exam {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("exam.in")));
//        FileWriter writer = new FileWriter("exam.out");
//        int k, n;
//        String[] str = reader.readLine().split(" ");
//        k = Integer.parseInt(str[0]);
//        n = Integer.parseInt(str[1]);
//        double sum = 0.0;
//        for (int i = 0; i != k; i++) {
//            String[] string = reader.readLine().split(" ");
//            int count = Integer.parseInt(string[0]);
//            int prob = Integer.parseInt(string[1]);
//            sum += count * prob;
//        }
//        writer.write(Double.toString(sum / (100 * n)));
//        writer.close();
//    }
//}
