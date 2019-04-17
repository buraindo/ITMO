using System;
using System.Collections.Generic;
using System.Linq;

namespace LabProblem {
    public class OperationsWithPolynomials {
        private const long Modulo = 998_244_353;

        private readonly long[] _first;
        private readonly long[] _second;
        private readonly long[] _reversedSecond;

        private OperationsWithPolynomials(int n, int m) {
            _first = new long[n + 1];
            _second = new long[m + 1];
            _reversedSecond = new long[1001];
        }

        private static void Read(IList<long> array) {
            var input = Console.ReadLine()?.Split().Select(long.Parse).ToArray();
            if (input == null) return;
            for (var i = 0; i < array.Count; i++) {
                array[i] = input[i];
            }
        }

        private static long Get(IReadOnlyList<long> array, int index) {
            return index > array.Count - 1 ? 0 : array[index];
        }
        
        private void WriteAddition() {
            var degree = Math.Max(_first.Length - 1, _second.Length - 1);
            Console.WriteLine(degree);
            for (var i = 0; i <= degree; i++) {
                Console.Write((Get(_first, i) + Get(_second, i)) % Modulo + " ");
            }
            Console.WriteLine();
        }

        private void WriteMultiplication() {
            var degree = _first.Length + _second.Length - 2;
            Console.WriteLine(degree);
            for (var i = 0; i <= degree; i++) {
                var ithCoefficient = 0L;
                for (var j = 0; j <= i; j++) {
                    ithCoefficient = (ithCoefficient + Get(_first, j) * Get(_second, i - j)) % Modulo;
                }
                Console.Write(ithCoefficient + " ");
            }

            Console.WriteLine();
        }

        private void WriteDivision() {
            _reversedSecond[0] = 1 / Get(_second, 0);
            Console.Write(_reversedSecond[0] + " ");
            for (var i = 1; i <= 1000; i++) {
                var ithCoefficient = 0L;
                for (var j = 1; j <= i; j++) {
                    ithCoefficient = (ithCoefficient + Get(_second, j) * Get(_reversedSecond, i - j) + Modulo) % Modulo;
                }
                _reversedSecond[i] = (-ithCoefficient / Get(_second, 0) + Modulo) % Modulo;
                Console.Write(_reversedSecond[i] + " ");
            }

            Console.WriteLine();
            for (var i = 0; i < 1000; i++) {
                var ithCoefficient = 0L;
                for (var j = 0; j <= i; j++) {
                    ithCoefficient = (ithCoefficient + Get(_first, j) * Get(_reversedSecond, i - j)) % Modulo;
                }
                Console.Write(ithCoefficient + " ");
            }
        }
        
        private void Run() {
            Read(_first);
            Read(_second);
            WriteAddition();
            WriteMultiplication();
            WriteDivision();
        }
        
        public static void Main() {
            var input = Console.ReadLine()?.Split().Select(int.Parse).ToArray();
            if (input != null) new OperationsWithPolynomials(input[0], input[1]).Run();
        }
    }
}