using System;

namespace LabProblem {
    public static class ConstructedCombinatorialClasses {
        private static string _input;
        private static int _pos;

        private static long Combination(long n, long k) {
            var res = 1L;
            for (var i = n - k + 1; i <= n; ++i) {
                res *= i;
            }

            for (var i = 2; i <= k; ++i) {
                res /= i;
            }

            return res;
        }

        private static long[] Parse() {
            long[] first;
            var result = new long[7];
            if (_input[_pos] == 'B') {
                _pos++;
                result = new long[] {0, 1, 0, 0, 0, 0, 0};
            }
            else if (_input[_pos] == 'L') {
                _pos += 2;
                first = Parse();
                _pos++;
                result[0] = 1;
                for (var i = 1; i < 7; i++) {
                    var sum = 0L;
                    for (var j = 1; j <= i; j++) {
                        sum += first[j] * result[i - j];
                    }

                    result[i] = sum;
                }
            }
            else if (_input[_pos] == 'S') {
                _pos += 2;
                first = Parse();
                _pos++;
                var m = new long[7, 7];

                m[0, 0] = 1;
                for (var i = 1; i < 7; i++) {
                    m[0, i] = 1;
                    m[i, 0] = 0;
                }

                result[0] = 1;
                for (var i = 1; i < 7; i++) {
                    for (var j = 1; j < 7; j++) {
                        var sum = 0L;
                        for (var k = 0; k <= i / j; k++) {
                            sum += Combination(Math.Max(first[j] + k - 1, 0L), k) * m[i - k * j, j - 1];
                        }

                        m[i, j] = sum;
                    }

                    result[i] = m[i, i];
                }
            }
            else if (_input[_pos] == 'P') {
                _pos += 2;
                first = Parse();
                _pos++;
                var second = Parse();
                _pos++;
                for (var i = 0; i < 7; i++) {
                    var sum = 0L;
                    for (var j = 0; j <= i; j++) {
                        sum += first[j] * second[i - j];
                    }

                    result[i] = sum;
                }
            }

            return result;
        }

        public static void Main() {
            _input = Console.ReadLine();
            var result = Parse();
            foreach (var item in result) {
                Console.Write(item + " ");
            }
        }
    }
}