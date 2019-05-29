using System;
using System.IO;
using System.Linq;

namespace LabProblem {
    public static class ChineseTheorem {
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
            var a = new long[2];
            var m = new long[2];
            var x = 0L;
            using (var writer = new StreamWriter(Console.OpenStandardOutput()))
            using (var reader = new StreamReader(Console.OpenStandardInput())) {
                var input = reader.ReadLine().Split().Select(long.Parse).ToArray();
                a[0] = input[0];
                a[1] = input[1];
                m[0] = input[2];
                m[1] = input[3];
                var mm = m[0] * m[1];
                for (var i = 0; i < 2; ++i) {
                    var y = mm / m[i];
                    Gcd(y, m[i], out var x1, out _);
                    var s = (x1 % m[i] + m[i]) % m[i];
                    var c = a[i] * s % m[i];
                    x = (x + c * y) % mm;
                }
                writer.WriteLine(x);
            }
        }
    }
}