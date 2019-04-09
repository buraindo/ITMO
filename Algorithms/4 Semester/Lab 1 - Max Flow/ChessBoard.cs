/*using System;
using System.Collections.Generic;
using System.Linq;

namespace LabProblem {
    public class ChessBoard {
        public static void Main() {
            var input = Console.ReadLine()?.Split().Select(int.Parse).ToArray();
            if (input == null) return;
            var n = input[0];
            var m = input[1];
            var first = new Graph(2 * (n + m), 0, 2 * (n + m) - 1);
            var second = new Graph(2 * (n + m), 0, 2 * (n + m) - 1);

            var color = new int[n, m];
            for (var i = 0; i < n; i++) {
                var s = Console.ReadLine();
                for (var j = 0; j < m; j++) {
                    color[i, j] = (i + j) % 2;
                    color[i, j] -= s != null && s[j] == 'B' ? 1 : 0;
                    color[i, j] = Math.Abs(color[i, j]);
                }
            }

            first.Fill(color, n, m);

            for (var i = 0; i < n; i++) {
                for (var j = 0; j < m; j++) {
                    color[i, j] = 1 - color[i, j];
                }
            }

            second.Fill(color, n, m);

            var w = 0;
            if (first.FindMaxFlow() > second.FindMaxFlow()) {
                first = second;
                w = 1;
            }

            first.Write(n, m, w);
        }

        private class Graph {
            private readonly List<Edge>[] _edges;
            private readonly int _s;
            private readonly int _t;
            private readonly bool[] _used;
            private long _maxFlow;

            public Graph(int vertexCount, int s, int t) {
                _edges = new List<Edge>[vertexCount];
                _s = s;
                _t = t;
                _used = new bool[vertexCount];
                for (var i = 0; i < vertexCount; i++) {
                    _edges[i] = new List<Edge>();
                }
            }

            private void AddEdge(int from, int to, int flow, int capacity, int x, int y) {
                var e1 = new Edge(to, flow, capacity, x, y);
                var e2 = new Edge(from, -flow, 0, x, y);
                e1.Rev = e2;
                e2.Rev = e1;
                _edges[from].Add(e1);
                _edges[to].Add(e2);
            }

            private int Dfs(int v, int cMin) {
                if (v == _t) {
                    return cMin;
                }

                _used[v] = true;
                foreach (var e in _edges[v]) {
                    if (e.Flow >= e.Capacity || _used[e.To]) continue;
                    var delta = Dfs(e.To, Math.Min(cMin, e.Capacity - e.Flow));
                    if (delta <= 0) continue;
                    e.Flow += delta;
                    e.Rev.Flow -= delta;
                    return delta;
                }

                return 0;
            }

            public int FindMaxFlow() {
                var maxFlow = 0;
                for (var i = 0; i < _used.Length; i++) {
                    _used[i] = false;
                }

                int flow;
                while ((flow = Dfs(_s, int.MaxValue)) > 0) {
                    for (var i = 0; i < _used.Length; i++) {
                        _used[i] = false;
                    }

                    maxFlow += flow;
                }

                _maxFlow = maxFlow;
                return maxFlow;
            }

            public void Fill(int[,] color, int n, int m) {
                for (var i = 0; i < n; i++) {
                    for (var j = 0; j < m; j++) {
                        if (color[i, j] != 1) continue;
                        var from = i - j;
                        if (from <= 0) from = n - from;
                        var to = n + m + i + j;
                        AddEdge(from, to, 0, 1, i, j);
                    }
                }

                for (var i = 1; i < n + m; i++) {
                    AddEdge(_s, i, 0, 1, i, i);
                }

                for (var i = n + m; i < 2 * (n + m); i++) {
                    AddEdge(i, _t, 0, 1, i, i);
                }
            }

            public void Write(int n, int m, int w) {
                Console.WriteLine(_maxFlow);
                for (var i = 0; i < n + m; i++) {
                    if (_used[i]) continue;
                    foreach (var e in _edges[i]) {
                        if (e.To == _s) continue;
                        Console.Write("2 " + (e.X + 1) + " " + (e.Y + 1) + " ");
                        Console.WriteLine((e.X + e.Y) % 2 == w ? "W" : "B");
                        break;
                    }
                }

                for (var i = n + m; i < 2 * (n + m); i++) {
                    if (!_used[i]) continue;
                    foreach (var e in _edges[i]) {
                        if (e.To == _t) continue;
                        Console.Write("1 " + (e.X + 1) + " " + (e.Y + 1) + " ");
                        Console.WriteLine((e.X + e.Y) % 2 == w ? "W" : "B");
                        break;
                    }
                }
            }
        }

        private class Edge {
            public readonly int To;
            public int Flow;
            public readonly int Capacity;
            public readonly int X;
            public readonly int Y;
            public Edge Rev;

            public Edge(int to, int flow, int capacity, int x, int y) {
                To = to;
                Flow = flow;
                Capacity = capacity;
                X = x;
                Y = y;
            }
        }
    }
}*/