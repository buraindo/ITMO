using System;
using System.Collections.Generic;
using System.IO;

namespace LabProblem {
    public static class MassPrimeFactorization {
        public static void Main() {
            const int m = 1000001;
            var dividers = new int[m];
            var nonPrime = new bool[m];
            for (var i = 0; i < m; i++) {
                nonPrime[i] = false;
                dividers[i] = i;
            }

            for (var i = 2L; i < m; ++i) {
                if (nonPrime[i]) continue;
                for (var k = i * i; k < m; k += i) {
                    nonPrime[k] = true;
                    dividers[k] = (int) i;
                }
            }

            using (var writer = new StreamWriter(Console.OpenStandardOutput()))
            using (var reader = new StreamReader(Console.OpenStandardInput())) {
                var n = int.Parse(reader.ReadLine());
                for (var i = 0; i < n; i++) {
                    var number = int.Parse(reader.ReadLine());
                    var current = number;
                    var divisors = new List<int>();
                    while (current != 1) {
                        divisors.Add(dividers[current]);
                        current /= dividers[current];
                    }

                    divisors.Sort();
                    foreach (var divisor in divisors) {
                        writer.Write(divisor + " ");
                    }

                    writer.Write("\n");
                }
            }
        }
    }
}