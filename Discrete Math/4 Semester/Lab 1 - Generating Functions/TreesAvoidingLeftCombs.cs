using System;
using System.Collections.Generic;
using System.Linq;

namespace LabProblem {
    public static class TreesAvoidingLeftCombs {
        private const int Modulo = 998244353;

        private static long Get(IList<long> array, int index) {
            return index > array.Count - 1 ? 0 : array[index];
        }

        private static long Parity(long num) {
            return num % 2 == 0 ? 1 : -1;
        }

        public static void Main() {
            var input = Console.ReadLine()?.Split().Select(int.Parse).ToArray();
            if (input == null) return;
            var k = input[0];
            var n = input[1];
            var combinations = new long[k + 1, k + 1];
            for (var i = 0; i <= k; i++) {
                combinations[i, 0] = 1;
                combinations[i, i] = 1;
                for (var j = 1; j < i; j++) {
                    combinations[i, j] = (combinations[i - 1, j - 1] + combinations[i - 1, j]) % Modulo;
                    while (combinations[i, j] < 0) combinations[i, j] += Modulo;
                }
            }

            var firstLength = k / 2;
            var secondLength = (k + 1) / 2;
            var first = new long[firstLength];
            var second = new long[secondLength];
            for (var i = 0; i < firstLength; i++) {
                first[i] = combinations[k - i - 2, i] * Parity(i);
            }

            for (var i = 0; i < secondLength; i++) {
                second[i] = combinations[k - i - 1, i] * Parity(i);
            }

            var reversedSecond = new List<long> {1 / Get(second, 0)};
            for (var i = 1; i <= n; i++) {
                var ithCoefficient = 0L;
                for (var j = 1; j <= i; j++) {
                    ithCoefficient = (ithCoefficient + Get(second, j) * Get(reversedSecond, i - j)) % Modulo;
                    while (ithCoefficient < 0) ithCoefficient += Modulo;
                }

                reversedSecond.Add((-ithCoefficient / Get(second, 0) + Modulo) % Modulo);
                while (reversedSecond[reversedSecond.Count - 1] < 0) reversedSecond[reversedSecond.Count - 1] += Modulo;
            }

            for (var i = 0; i < n; i++) {
                var ithCoefficient = 0L;
                for (var j = 0; j <= i; j++) {
                    ithCoefficient = (ithCoefficient + Get(first, j) * Get(reversedSecond, i - j)) % Modulo;
                    while (ithCoefficient < 0) ithCoefficient += Modulo;
                }

                Console.WriteLine(ithCoefficient);
            }
        }
    }
}