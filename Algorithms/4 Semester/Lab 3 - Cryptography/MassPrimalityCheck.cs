using System;
using System.IO;

namespace LabProblem {
    public static class MassPrimalityCheck {
        public static void Main() {
            const int m = 20000000;
            var prime = new bool[m + 1];
            for (var i = 0; i <= m; i++) {
                prime[i] = i % 2 != 0;
            }

            prime[1] = false;
            for (var i = 3L; i <= m; i += 2) {
                if (!prime[i] || i * i > m) continue;
                for (var j = i * i; j <= m; j += i) {
                    prime[j] = false;
                }
            }

            using (var writer = new StreamWriter(Console.OpenStandardOutput()))
            using (var reader = new StreamReader(Console.OpenStandardInput())) {
                var n = int.Parse(reader.ReadLine());
                for (var i = 0; i < n; ++i) {
                    var a = int.Parse(reader.ReadLine());
                    writer.WriteLine(prime[a] ? "YES" : "NO");
                }
            }
        }
    }
}