using System;

namespace LabProblem {
    public class Cockroaches {

        private const long INFINITY = long.MaxValue;

        private static long ShortestPath(int n, int to, long[,] lengths) {
            var dist = new long[n];
            var used = new bool[n];
            for (var i = 0; i < n; i++)
                dist[i] = INFINITY;
            dist[0] = 0;
            for (var i = 0; i < n; i++) {
                var v = -1;
                for (var j = 0; j < n; j++) {
                    if (!used[j] && (v == -1 || dist[j] < dist[v]))
                        v = j;
                }
                if (dist[v] == INFINITY) break;
                used[v] = true;
                for (var u = 0; u < n; u++) {
                    if (v == u) continue;
                    var l = lengths[v, u];
                    if (dist[v] + l < dist[u]) {
                        dist[u] = dist[v] + l;
                    }
                }
            }
            return dist[to];
        }
        
        public static void Main() {
            var input = Console.ReadLine().Split();
            var n = Convert.ToInt32(input[0]);
            var width = Convert.ToInt32(input[1]);
            var lengths = new long[n + 2, n + 2];
            lengths[0, n + 1] = width;
            lengths[n + 1, 0] = width;
            var x1 = new int[n];
            var y1 = new int[n];
            var x2 = new int[n];
            var y2 = new int[n];
            for (var i = 0; i < n; i++) {
                input = Console.ReadLine().Split();
                x1[i] = Convert.ToInt32(input[0]);
                y1[i] = Convert.ToInt32(input[1]);
                x2[i] = Convert.ToInt32(input[2]);
                y2[i] = Convert.ToInt32(input[3]);
            }
            for (var i = 0; i < n; i++) {
                var minY = Math.Min(y1[i], y2[i]);
                var maxY = Math.Max(y1[i], y2[i]);
                lengths[n + 1, i + 1] = minY;
                lengths[i + 1, n + 1] = minY;
                lengths[0, i + 1] = width - maxY;
                lengths[i + 1, 0] = width - maxY;
                for (var j = 0; j < n; j++) {
                    if (i == j) continue;
                    var horizontal = x1[i] <= x1[j] ? x1[j] - x2[i] : x1[i] - x2[j];
                    var vertical = y1[i] <= y1[j] ? y1[j] - y2[i] : y1[i] - y2[j];
                    lengths[i + 1, j + 1] = Math.Max(0, Math.Max(vertical, horizontal));
                    lengths[j + 1, i + 1] = Math.Max(0, Math.Max(vertical, horizontal));
                }
            }
            var result = ShortestPath(n + 2, n + 1, lengths);
            if (result == INFINITY) Console.WriteLine(0);
            else Console.WriteLine(result);
        }
    }
}