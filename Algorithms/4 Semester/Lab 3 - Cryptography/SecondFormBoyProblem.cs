using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Numerics;

namespace LabProblem {
    public static class SecondFormBoyProblem {
        private static void FourierTransform(List<Complex> a, bool invert) {
            var n = a.Count;
            if (n == 1) return;
            var a0 = new List<Complex>();
            var a1 = new List<Complex>();
            for (var i = 0; i < n; i += 2) {
                a0.Add(a[i]);
                a1.Add(a[i + 1]);
            }
            
            FourierTransform(a0, invert);
            FourierTransform(a1, invert);
            var ang = 2 * Math.PI / n;
            if (invert) {
                ang = -ang;
            }
            var w = new Complex(1, 0);
            var wn = new Complex(Math.Cos(ang), Math.Sin(ang));
            for (var i = 0; i < n / 2; ++i) {
                a[i] = a0[i] + w * a1[i];
                a[i + n / 2] = a0[i] - w * a1[i];
                if (invert) {
                    a[i] /= 2;
                    a[i + n / 2] /= 2;
                }
                w *= wn;
            }
        }
        private static void Multiply(List<int> a, List<int> b, List<int> res) {
            var left = new List<Complex>(a.Select(real => new Complex(real, 0)));
            var right = new List<Complex>(b.Select(real => new Complex(real, 0)));
            var n = 1;
            var maxim = Math.Max(a.Count, b.Count);
            for (; n < maxim; n <<= 1) {}
            n <<= 1;
            while (left.Count < n) left.Add(0);
            while (right.Count < n) right.Add(0);
            FourierTransform(left, false);
            FourierTransform(right, false);
            for (var i = 0; i < n; ++i) {
                left[i] *= right[i];
            }
            FourierTransform(left, true);
            for (var i = 0; i < n; ++i) {
                res.Add((int) (left[i].Real + 0.5));
            }
            var md = 0;
            for (var i = 0; i < n; ++i) {
                res[i] += md;
                md = res[i] / 10;
                res[i] %= 10;
            }
        }

        public static void Main() {
            using (var writer = new StreamWriter(Console.OpenStandardOutput()))
            using (var reader = new StreamReader(Console.OpenStandardInput())) {
                var first = reader.ReadLine();
                var second = reader.ReadLine();
                if (first[0] == '0' || second[0] == '0') {
                    writer.WriteLine(0);
                    return;
                }
                var isFirstNegative = first[0] == '-';
                var isSecondNegative = second[0] == '-';
                var isResultNegative = isFirstNegative ^ isSecondNegative;
                var a = new List<int>(Enumerable.Repeat(0, isFirstNegative ? first.Length - 1 : first.Length));
                var b = new List<int>(Enumerable.Repeat(0, isSecondNegative ? second.Length - 1 : second.Length));
                var res = new List<int>();
                if (isResultNegative) writer.Write("-");
                for (int i = isFirstNegative ? 1 : 0, sub = isFirstNegative ? 0 : 1; i < first.Length; ++i) {
                    a[a.Count - sub - i] = first[i] - '0';
                }
                for (int i = isSecondNegative ? 1 : 0, sub = isSecondNegative ? 0 : 1; i < second.Length; ++i) {
                    b[b.Count - sub - i] = second[i] - '0';
                }
                Multiply(a, b, res);
                var print = false;
                for (var i = res.Count - 1; i >= 0; --i) {
                    if (print) {
                        writer.Write(res[i]);
                    } else if (res[i] != 0) {
                        writer.Write(res[i]);
                        print = true;
                    }
                }
            }
        }
    }
}