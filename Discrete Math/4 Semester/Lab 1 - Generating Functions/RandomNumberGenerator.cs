using System;
using System.Linq;

namespace LabProblem {
    public static class RandomNumberGenerator {
        private const long Modulo = 104857601;

        /*
         * https://neerc.ifmo.ru/wiki/index.php?title=%D0%91%D1%8B%D1%81%D1%82%D1%80%D0%BE%D0%B5_%D0%B2%D1%8B%D1%87%D0%B8%D1%81%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5_%D1%87%D0%BB%D0%B5%D0%BD%D0%BE%D0%B2_%D0%BB%D0%B8%D0%BD%D0%B5%D0%B9%D0%BD%D0%BE%D0%B9_%D1%80%D0%B5%D0%BA%D1%83%D1%80%D1%80%D0%B5%D0%BD%D1%82%D0%BD%D0%BE%D0%B9_%D0%BF%D0%BE%D1%81%D0%BB%D0%B5%D0%B4%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D0%BE%D1%81%D1%82%D0%B8#.D0.A1.D0.B2.D1.8F.D0.B7.D1.8C_.D1.81_.D0.BC.D0.BD.D0.BE.D0.B3.D0.BE.D1.87.D0.BB.D0.B5.D0.BD.D0.B0.D0.BC.D0.B8_.28.D0.B7.D0.B0_.5Bmath.5DO.28k.5E2_.5Ccdot_.5Clog_n.29.5B.2Fmath.5D.29
         */

        private static void GetNth(ref long n, long k, long[] a, long[] q, long[] r, long[] negativeQ) {
            while (n >= k) {
                for (var i = k; i < 2 * k; i++) {
                    a[i] = 0;
                    for (var j = 1; j <= k; j++) {
                        a[i] = (a[i] - q[j] * a[i - j]) % Modulo;
                        while (a[i] < 0) {
                            a[i] += Modulo;
                        }
                    }
                }

                for (var i = 0; i <= k; i++) {
                    negativeQ[i] = i % 2 == 0 ? q[i] : (-q[i] + Modulo) % Modulo;
                }

                for (var i = 0; i <= 2 * k; i += 2) {
                    var ithCoefficient = 0L;
                    for (var j = 0; j <= i; j++) {
                        var coefficient = j > k ? 0 : q[j];
                        var negativeCoefficient = i - j > k ? 0 : negativeQ[i - j];
                        ithCoefficient = (ithCoefficient + coefficient * negativeCoefficient + Modulo) % Modulo;
                    }

                    r[i / 2] = ithCoefficient;
                }

                for (var i = 0; i <= k; i++) {
                    q[i] = r[i];
                }

                var filterCoefficient = 0;
                for (var i = 0; i < 2 * k; i++) {
                    if (n % 2 != i % 2) continue;
                    a[filterCoefficient] = a[i];
                    filterCoefficient++;
                }

                n /= 2;
            }
        }

        public static void Main() {
            var input = Console.ReadLine()?.Split().Select(long.Parse).ToArray();
            if (input == null) return;
            var k = input[0];
            var n = input[1] - 1;
            var a = new long[2 * k];
            var q = new long[k + 1];
            q[0] = 1;
            var r = new long[k + 1];
            var negativeQ = new long[k + 1];
            input = Console.ReadLine()?.Split().Select(long.Parse).ToArray();
            if (input == null) return;
            for (var i = 0; i < k; i++) {
                a[i] = input[i];
            }

            input = Console.ReadLine()?.Split().Select(long.Parse).ToArray();
            if (input == null) return;
            for (var i = 1; i <= k; i++) {
                q[i] = (-input[i - 1] + Modulo) % Modulo;
            }

            GetNth(ref n, k, a, q, r, negativeQ);
            Console.WriteLine(a[n]);
        }
    }
}