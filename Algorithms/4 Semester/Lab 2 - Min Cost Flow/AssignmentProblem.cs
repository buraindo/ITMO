using System.IO;
using System.Linq;

namespace LabProblem {
    public class AssignmentProblem {
        public static void Main() {
            using (var reader = new StreamReader("assignment.in"))
            using (var writer = new StreamWriter("assignment.out")) {
                var n = int.Parse(reader.ReadLine());
                var c = new int[n + 1, n + 1];
                for (var i = 1; i <= n; i++) {
                    var input = reader.ReadLine().Split().Select(int.Parse).ToArray();
                    for (var j = 1; j <= n; j++) c[i, j] = input[j - 1];
                }
                var solver = new Solver(n, n, c);
                solver.Solve();
                var result = solver.GetResult();
                writer.Write(solver.GetCost() + "\n");
                for (var i = 1; i < result.Length; i++) writer.Write(i + " " + result[i] + "\n");
            }
        }

        private class Solver {
            private readonly int[,] _a;
            private readonly int _m;
            private readonly int _n;
            private readonly int[] _u;
            private readonly int[] _v;
            private readonly int[] _match;
            private readonly int[] _path;

            public Solver(int n, int m, int[,] a) {
                _a = a;
                _n = n;
                _m = m;
                _u = new int[n + 1];
                _v = new int[n + 1];
                _match = new int[m + 1];
                _path = new int[m + 1];
            }

            public void Solve() {
                for (var i = 1; i <= _n; i++) {
                    _match[0] = i;
                    var start = 0;
                    var minimum = new int[_m + 1];
                    for (var k = 0; k < minimum.Length; k++) minimum[k] = int.MaxValue;
                    var used = new bool[_m + 1];
                    do {
                        used[start] = true;
                        var currentMatch = _match[start];
                        var delta = int.MaxValue;
                        var newStart = 0;
                        for (var j = 1; j <= _m; j++) {
                            if (used[j]) continue;
                            var cur = _a[currentMatch, j] - _u[currentMatch] - _v[j];
                            if (cur < minimum[j]) {
                                minimum[j] = cur;
                                _path[j] = start;
                            }
                            if (minimum[j] >= delta) continue;
                            delta = minimum[j];
                            newStart = j;
                        }
                        for (var j = 0; j <= _m; j++)
                            if (used[j]) {
                                _u[_match[j]] += delta;
                                _v[j] -= delta;
                            }
                            else {
                                minimum[j] -= delta;
                            }
                        start = newStart;
                    } while (_match[start] != 0);
                    do {
                        var current = _path[start];
                        _match[start] = _match[current];
                        start = current;
                    } while (start > 0);
                }
            }

            public int[] GetResult() {
                var result = new int[_n + 1];
                for (var j = 1; j <= _m; j++) result[_match[j]] = j;
                return result;
            }

            public int GetCost() {
                return -_v[0];
            }
        }
    }
}