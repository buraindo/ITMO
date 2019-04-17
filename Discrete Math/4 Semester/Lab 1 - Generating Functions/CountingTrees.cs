using System;
using System.Linq;
using System.Text;

namespace LabProblem {
    public static class CountingTrees {
        private const long Modulo = 1_000_000_007;

        public static void Main() {
            var input = Console.ReadLine()?.Split().Select(int.Parse).ToArray();
            if (input == null) return;
            var m = input[1];
            var tree = new long[m + 1];
            input = Console.ReadLine()?.Split().Select(int.Parse).ToArray();
            if (input == null) return;
            foreach (var item in input) {
                tree[item] = 1;
            }
            var result = new long[m + 1];
            result[0] = 1;
            var prefixSums = (long[]) result.Clone();
            var sb = new StringBuilder();
            for (var i = 1; i <= m; i++) {
                var sum = 0L;
                for (var j = 1; j <= i; j++) {
                    if (tree[j] == 0) continue;
                    sum += prefixSums[i - j] % Modulo;
                }
                result[i] = sum % Modulo;
                for (var j = 0; j <= i; j++) {
                    prefixSums[i] = (prefixSums[i] + result[j] * result[i - j]) % Modulo;
                }
                sb.Append(result[i]).Append(" ");
            }
            Console.WriteLine(sb.ToString());
        }
    }
}