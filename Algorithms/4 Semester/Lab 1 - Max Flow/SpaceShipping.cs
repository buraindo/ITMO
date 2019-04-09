using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace LabProblem {
    public class SpaceShipping {
        public static void Main() {
            using (var reader = new StreamReader("bring.in"))
            using (var writer = new StreamWriter("bring.out")) {
                var input = reader.ReadLine().Split().Select(int.Parse).ToArray();
                var n = input[0];
                var m = input[1];
                var k = input[2];
                var s = input[3] - 1;
                var t = input[4] - 1;
                var day = 1;
                var count = 0L;

                var from = new int[m];
                var to = new int[m];
                var sizes = new int[n];

                for (var i = 0; i < m; i++) {
                    var inp = reader.ReadLine().Split().Select(int.Parse).ToArray();
                    from[i] = inp[0] - 1;
                    to[i] = inp[1] - 1;
                    sizes[from[i]] += 2;
                    sizes[to[i]] += 2;
                }

                var graph = new Graph(n, s, t, sizes);

                while (count != k) {
                    for (var i = 0; i < n; i++) {
                        graph.AddEdge((day - 1) * n + i, day * n + i, int.MaxValue);
                    }

                    for (var i = 0; i < m; i++) {
                        graph.AddEdge((day - 1) * n + from[i], day * n + to[i], 1);
                        graph.AddEdge((day - 1) * n + to[i], day * n + from[i], 1);
                    }

                    count += graph.FindMaxFlow(day * n + t, k - count, n, day);
                    day++;
                }

                writer.WriteLine(--day);
                var place = new int[k];
                for (var i = 0; i < place.Length; i++) {
                    place[i] = s + 1;
                }

                for (var d = 0; d < day; d++) {
                    var ansFrom = new List<int>();
                    var ansTo = new List<int>();
                    var isMove = new bool[k];
                    for (var i = 0; i < n; i++) {
                        foreach (var e in graph.Edges[d * n + i]) {
                            if (e.Capacity != int.MaxValue && e.Flow == 1) {
                                ansFrom.Add(e.From - d * n + 1);
                                ansTo.Add(e.To - (d + 1) * n + 1);
                            }
                        }
                    }

                    writer.Write(ansFrom.Count + " ");
                    for (var i = 0; i < ansFrom.Count; i++) {
                        for (var j = 0; j < k; j++) {
                            if (!isMove[j] && place[j] == ansFrom[i]) {
                                isMove[j] = true;
                                writer.Write(j + 1 + " " + ansTo[i] + " ");
                                place[j] = ansTo[i];
                                break;
                            }
                        }
                    }

                    writer.WriteLine();
                }
            }
        }

        private class Graph {
            public readonly List<Edge>[] Edges;
            private readonly int _vertexCount;
            private readonly int _s;
            private int _t;
            private readonly int[] _distances;
            private readonly int[] _visit;

            public Graph(int vertexCount, int s, int t, IReadOnlyList<int> sizes) {
                _vertexCount = vertexCount * 10000;
                Edges = new List<Edge>[_vertexCount];
                _s = s;
                _t = t;
                _distances = new int[_vertexCount];
                _visit = new int[_vertexCount];
                for (var i = 0; i < 10000; i++) {
                    for (var j = 0; j < vertexCount; j++) {
                        Edges[vertexCount * i + j] = new List<Edge>(sizes[j]);
                    }
                }
            }

            public void AddEdge(int from, int to, int capacity) {
                var e1 = new Edge(from, to, capacity, 0);
                var e2 = new Edge(to, from, 0, 0);
                e1.Reverse = e2;
                e2.Reverse = e1;
                Edges[from].Add(e1);
                Edges[to].Add(e2);
            }

            private long Dfs(int v, long cMin) {
                if (v == _t || cMin == 0) {
                    return cMin;
                }

                var maxFlow = 0L;
                for (var i = _visit[v]; i < Edges[v].Count; i++) {
                    var e = Edges[v][i];
                    if (e.Flow < e.Capacity && _distances[e.To] == _distances[v] + 1) {
                        var delta = Dfs(e.To, Math.Min(cMin, e.Capacity - e.Flow));
                        if (delta > 0) {
                            cMin -= delta;
                            maxFlow += delta;
                            e.Flow += delta;
                            e.Reverse.Flow -= delta;
                            if (cMin == 0) break;
                        }
                    }

                    _visit[v] = i + 1;
                }

                return maxFlow;
            }

            private bool Bfs(int max) {
                var queue = new Queue<int>(_vertexCount);
                queue.Enqueue(_s);
                for (var i = 0; i < max; i++) {
                    _distances[i] = int.MaxValue;
                }

                _distances[_s] = 0;
                while (queue.Count > 0) {
                    var v = queue.Dequeue();
                    foreach (var e in Edges[v]) {
                        if (e.Flow < e.Capacity && _distances[e.To] == int.MaxValue) {
                            _distances[e.To] = _distances[v] + 1;
                            queue.Enqueue(e.To);
                            if (e.To == _t) {
                                return true;
                            }
                        }
                    }
                }

                return _distances[_t] != int.MaxValue;
            }

            public long FindMaxFlow(int t, long flows, int n, int day) {
                var maxFlow = 0L;
                _t = t;
                while (Bfs((day + 1) * n)) {
                    for (var i = 0; i < (day + 1) * n; i++) {
                        _visit[i] = 0;
                    }

                    maxFlow += Dfs(_s, flows - maxFlow);
                    if (maxFlow == flows) break;
                }

                return maxFlow;
            }
        }

        private class Edge {
            public readonly int To;
            public readonly int From;
            public readonly int Capacity;
            public long Flow;
            public Edge Reverse;

            public Edge(int from, int to, int capacity, long flow) {
                From = from;
                To = to;
                Capacity = capacity;
                Flow = flow;
            }
        }
    }
}