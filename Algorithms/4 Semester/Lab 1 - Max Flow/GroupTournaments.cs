/*using System;
using System.Collections.Generic;

namespace LabProblem {
    public class GroupTournaments {
        public static void Main() {
            var n = int.Parse(Console.ReadLine() ?? throw new ArgumentNullException());
            var graph = new Graph(n + 2, 0, n + 1);
            var scoreToGet = new int[n];
            var tourneyTable = new string[n];

            for (var i = 0; i < n; i++) {
                tourneyTable[i] = Console.ReadLine();
                for (var j = i + 1; j < n; j++) {
                    if (tourneyTable[i][j] == 'W') {
                        scoreToGet[i] += 3;
                    }
                    else if (tourneyTable[i][j] == 'w') {
                        scoreToGet[i] += 2;
                        scoreToGet[j] += 1;
                    }
                    else if (tourneyTable[i][j] == 'l') {
                        scoreToGet[i] += 1;
                        scoreToGet[j] += 2;
                    }
                    else if (tourneyTable[i][j] == 'L') {
                        scoreToGet[j] += 3;
                    }
                    else if (tourneyTable[i][j] == '.') {
                        graph.AddEdge(i + 1, j + 1, 3);
                    }
                }
            }

            var input = Console.ReadLine()?.Split();
            for (var i = 0; i < n; i++) {
                if (input != null) scoreToGet[i] = int.Parse(input[i]) - scoreToGet[i];
            }

            for (var i = 0; i < n; i++) {
                var count = 0;
                foreach (var e in graph.Edges[i + 1]) {
                    if (e.To < i + 1) count++;
                }

                count = 3 * (graph.Edges[i + 1].Count - count);
                if (scoreToGet[i] < count) {
                    graph.AddEdge(0, i + 1, count - scoreToGet[i]);
                }
                else {
                    graph.AddEdge(i + 1, n + 1, scoreToGet[i] - count);
                }
            }

            graph.FindMaxFlow();
            var capacities = graph.GetCapacities();

            for (var i = 0; i < n; i++) {
                for (var j = 0; j < n; j++) {
                    if (tourneyTable[i][j] == '.') {
                        Console.Write(capacities[i + 1, j + 1] == 3 ? 'W' :
                            capacities[i + 1, j + 1] == 2 ? 'w' :
                            capacities[i + 1, j + 1] == 1 ? 'l' : 'L');
                    }
                    else {
                        Console.Write(tourneyTable[i][j]);
                    }
                }

                Console.WriteLine();
            }
        }

        private class Graph {
            public readonly List<Edge>[] Edges;
            private readonly int _vertexCount;
            private readonly int _s;
            private readonly int _t;
            private readonly int[] _distances;
            private readonly int[] _visit;

            public Graph(int vertexCount, int s, int t) {
                Edges = new List<Edge>[vertexCount];
                _vertexCount = vertexCount;
                _s = s;
                _t = t;
                _distances = new int[vertexCount];
                _visit = new int[vertexCount];
                for (var i = 0; i < vertexCount; i++) {
                    Edges[i] = new List<Edge>();
                }
            }

            public void AddEdge(int from, int to, int capacity) {
                var e1 = new Edge(to, capacity);
                var e2 = new Edge(from, 0);
                e1.Reverse = e2;
                e2.Reverse = e1;
                Edges[from].Add(e1);
                Edges[to].Add(e2);
            }

            private int Dfs(int v, int cMin) {
                if (v == _t) {
                    return cMin;
                }

                var maxFlow = 0;
                for (var i = _visit[v]; i < Edges[v].Count; i++) {
                    var e = Edges[v][i];
                    if (e.Capacity > 0 && _distances[e.To] == _distances[v] + 1) {
                        var delta = Dfs(e.To, Math.Min(cMin, e.Capacity));
                        if (delta > 0) {
                            cMin -= delta;
                            maxFlow += delta;
                            e.Capacity -= delta;
                            e.Reverse.Capacity += delta;
                            if (cMin == 0) break;
                        }
                    }

                    _visit[v] = i + 1;
                }

                return maxFlow;
            }

            private bool Bfs() {
                var queue = new Queue<int>(_vertexCount);
                queue.Enqueue(_s);
                for (var i = 0; i < _distances.Length; i++) {
                    _distances[i] = int.MaxValue;
                }

                _distances[_s] = 0;
                while (queue.Count > 0) {
                    var v = queue.Dequeue();
                    foreach (var e in Edges[v]) {
                        if (e.Capacity > 0 && _distances[e.To] == int.MaxValue) {
                            _distances[e.To] = _distances[v] + 1;
                            queue.Enqueue(e.To);
                            if (e.To == _t) {
                                return true;
                            }
                        }
                    }
                }

                return false;
            }

            public void FindMaxFlow() {
                while (Bfs()) {
                    for (var i = 0; i < _visit.Length; i++) {
                        _visit[i] = 0;
                    }

                    while (Dfs(_s, 3) > 0) {
                    }
                }
            }

            public int[,] GetCapacities() {
                var capacities = new int[_vertexCount + 1, _vertexCount + 1];
                for (var v = 1; v < _vertexCount - 1; v++) {
                    foreach (var e in Edges[v]) {
                        capacities[v, e.To] = e.Capacity;
                    }
                }

                return capacities;
            }
        }

        private class Edge {
            public readonly int To;
            public int Capacity;
            public Edge Reverse;

            public Edge(int to, int capacity) {
                To = to;
                Capacity = capacity;
            }
        }
    }
}*/