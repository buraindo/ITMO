//import java.io.*;
//
//public class MarkChain {
//
//    public class Matrix {
//        private final int M;
//        private final int N;
//        private final double[][] data;
//
//        public Matrix(int M, int N) {
//            this.M = M;
//            this.N = N;
//            data = new double[M][N];
//        }
//
//        public Matrix(double[][] data) {
//            M = data.length;
//            N = data[0].length;
//            this.data = new double[M][N];
//            for (int i = 0; i < M; i++)
//                for (int j = 0; j < N; j++)
//                    this.data[i][j] = data[i][j];
//        }
//
//        public Matrix times(Matrix B) {
//            Matrix A = this;
//            Matrix C = new Matrix(A.M, B.N);
//            for (int i = 0; i < C.M; i++)
//                for (int j = 0; j < C.N; j++)
//                    for (int k = 0; k < A.N; k++)
//                        C.data[i][j] += (A.data[i][k] * B.data[k][j]);
//            return C;
//        }
//
//    }
//
//    public static boolean isRepeated (Matrix m) {
//        double[] first = m.data[0];
//        for (int i = 1; i != m.N; i++) {
//            double[] row = m.data[i];
//            for (int j = 0; j != m.N; j++) {
//                if (row[j] - first[j] >= 0.0001) return false;
//            }
//        }
//        return true;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("markchain.in")));
//        FileWriter writer = new FileWriter("markchain.out");
//        int n = Integer.parseInt(reader.readLine());
//        double[][] data = new double[n][n];
//        for (int i = 0; i != n; i++) {
//            String[] str = reader.readLine().split(" ");
//            for (int j = 0; j != n; j++) {
//                data[i][j] = Double.parseDouble(str[j]);
//            }
//        }
//        MarkChain obj = new MarkChain();
//        Matrix matrix = obj.new Matrix(data);
//        while (true) {
//            if (isRepeated(matrix)) break;
//            matrix = matrix.times(matrix);
//        }
//        data = matrix.data;
//        for (int i = 0; i != n; i++) {
//            double d = data[0][i];
//            writer.write(Double.toString(d) + "\n");
//        }
//        writer.close();
//    }
//
//}