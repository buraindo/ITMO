using System;
using System.IO;

namespace LabProblem {
    public static class LargePrimalityCheck {
        private static long Multiply(long a, long n, long m) {
            var r = 0L;
            while (n > 0) {
                if (n % 2 == 1)  r = (r + a) % m;
                a = (a + a) % m;
                n /= 2;
            }
            return r;
        }
        
        private static long ModuloPower(long a, long n, long m) {
            var res = 1L;
            while (n > 0) {
                if ((n & 1) > 0) res = Multiply(res, a, m);
                a = Multiply(a, a, m);
                n >>= 1;
            }
            return res;
        }

        private static readonly Random Random = new Random();
        
        private static bool MillerRabinTest(long n) {
            if (n == 2 || n == 3) return true;
            if (n < 2 || n % 2 == 0) return false;
            var t = n - 1;
            var s = 0L;
            while ((t & 1) == 0) {
                t /= 2;
                ++s;
            }
            for (var i = 0; i < 2; i++) {
                var a = (long) Random.NextDouble() % (n - 2) + 2;
                var x = ModuloPower(a, t, n);
                if (x == 1 || x == n - 1) continue;
                for (var r = 1; r < s; ++r) {
                    x = ModuloPower(x, 2, n);
                    if (x == 1) return false;
                    if (x == n - 1) break;
                }
                if (x != n - 1) return false;
            }
            return true;
        }
        
        public static void Main() {
            using (var writer = new StreamWriter(Console.OpenStandardOutput()))
            using (var reader = new StreamReader(Console.OpenStandardInput())) {
                var n = int.Parse(reader.ReadLine());
                for (var i = 0; i < n; i++) {
                    var number = long.Parse(reader.ReadLine());
                    writer.WriteLine(MillerRabinTest(number) ? "YES" : "NO");
                }
            }
        }
    }
}