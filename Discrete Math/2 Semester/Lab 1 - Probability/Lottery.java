//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Lottery {
//
//    public class Ticket {
//        public int count;
//        public int price;
//
//        public Ticket(int count, int price) {
//            this.count = count;
//            this.price = price;
//        }
//    }
//
//    public static List<Ticket> items = new ArrayList<>();
//
//    public static double MeanValue(int step) {
//        if (step + 1 == items.size()) {
//            Ticket ticket = items.get(items.size() - 1);
//            double prob = 1.0 / ticket.count;
//            return (1 - prob) * items.get(items.size() - 2).price + prob * ticket.price;
//        }
//        Ticket ticket = items.get(step);
//        Ticket prev = items.get(step - 1);
//        double prob = 1.0 / ticket.count;
//        return prob * MeanValue(step + 1) + (1 - prob) * prev.price;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("lottery.in")));
//        FileWriter writer = new FileWriter("lottery.out");
//        String[] init = reader.readLine().split(" ");
//        int n = Integer.parseInt(init[0]), m = Integer.parseInt(init[1]);
//        Lottery r = new Lottery();
//        items.add(r.new Ticket(0, 0));
//        for (int i = 0; i != m; i++) {
//            String[] str = reader.readLine().split(" ");
//            int count = Integer.parseInt(str[0]);
//            int price = Integer.parseInt(str[1]);
//            items.add(r.new Ticket(count,price));
//        }
//        double mean = MeanValue(1);
//        writer.write(Double.toString(n - mean));
//        writer.close();
//    }
//}
