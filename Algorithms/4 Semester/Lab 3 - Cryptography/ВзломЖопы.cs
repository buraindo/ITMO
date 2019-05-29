using System;
using System.IO;

namespace LabProblem {
    public static class ВзломЖопы {
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
        private static long Gcd(long a, long b, out long x, out long y) {
            if (a == 0) {
                x = 0;
                y = 1;
                return b;
            }
            var d = Gcd(b % a, a, out var x1, out var y1);
            x = y1 - b / a * x1;
            y = x1;
            return d;
        }

        public static void Main() {
            using (var writer = new StreamWriter(Console.OpenStandardOutput()))
            using (var reader = new StreamReader(Console.OpenStandardInput())) {
                var n = int.Parse(reader.ReadLine());
                var e = int.Parse(reader.ReadLine());
                var c = int.Parse(reader.ReadLine());
                var root = (int) Math.Sqrt(n);
                var nonPrime = new bool[root + 1];
                for (var i = 0; i <= root; ++i) {
                    nonPrime[i] = false;
                }
                for (var i = 2; i <= root; ++i) {
                    if (nonPrime[i]) continue;
                    for (var k = i * i; k <= root; k += i) {
                        nonPrime[k] = true;
                    }
                }

                var p = 0L;
                var q = 0L;
                for (var p1 = 2; p1 <= root; ++p1) {
                    if (nonPrime[p1]) continue;
                    if (n % p1 != 0) continue;
                    q = n / p1;
                    p = p1;
                }
                var mm = (p - 1) * (q - 1);
                Gcd(e, mm, out var x1, out _);
                var d = (x1 % mm + mm) % mm;
                var m = ModuloPower(c, d, n);
                writer.WriteLine(m);
            }
        }
    }
}