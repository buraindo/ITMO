using System;
using System.Collections.Generic;
using System.Linq;

namespace LabProblem {
    public class TravellingSalesmanProblem {
        public static void Main() {
            var input = Console.ReadLine().Split().Select(int.Parse).ToArray();
            var n = input[0];
            var m = input[1];
            var graph = new Graph(2 * n + 2, 0, 2 * n + 1);
            input = Console.ReadLine().Split().Select(int.Parse).ToArray();
            for (var i = 1; i <= n; i++) {
                graph.AddEdge(n + i, i, int.MaxValue, input[i - 1]);
                graph.AddEdge(0, n + i, 1, 0);
                graph.AddEdge(i, 2 * n + 1, 1, 0);
                graph.AddEdge(i, n + i, int.MaxValue, 0);
            }
            for (var i = 0; i < m; i++) {
                input = Console.ReadLine().Split().Select(int.Parse).ToArray();
                var from = input[0];
                var to = input[1];
                var cost = input[2];
                graph.AddEdge(n + from, to, int.MaxValue, cost);
            }
            Console.WriteLine(graph.MinCostFlow());
        }

        private class Graph {
            private readonly long[] _distances;
            private readonly List<Edge>[] _edges;
            private readonly long[] _minimum;
            private readonly int[] _parent;
            private readonly int[] _path;
            private readonly int _s;
            private readonly int _t;
            private readonly int _vertexCount;
            private long _cost;

            public Graph(int vertexCount, int s, int t) {
                _edges = new List<Edge>[vertexCount];
                _vertexCount = vertexCount;
                _s = s;
                _t = t;
                _cost = 0;
                _distances = new long[vertexCount];
                _minimum = new long[vertexCount];
                _parent = new int[vertexCount];
                _path = new int[vertexCount];
                for (var i = 0; i < vertexCount; i++) _edges[i] = new List<Edge>();
            }

            public void AddEdge(int from, int to, int cap, int cost) {
                _edges[from].Add(new Edge(from, to, 0, cap, cost, _edges[to].Count));
                _edges[to].Add(new Edge(to, from, 0, 0, -cost, _edges[from].Count - 1));
            }

            private bool FindMinCostWay() {
                var queue = new LinkedList<int>();
                queue.AddLast(_s);
                for (var i = 0; i < _distances.Length; i++) _distances[i] = long.MaxValue;
                var mark = new int[_vertexCount];
                _distances[_s] = 0;
                _minimum[_s] = long.MaxValue;
                while (queue.Count > 0) {
                    var v = queue.First.Value;
                    queue.RemoveFirst();
                    mark[v] = 2;
                    for (var i = 0; i < _edges[v].Count; i++) {
                        var e = _edges[v][i];
                        if (e.Flow < e.Capacity && _distances[e.From] + e.Cost < _distances[e.To]) {
                            _distances[e.To] = _distances[e.From] + e.Cost;
                            _minimum[e.To] = Math.Min(_minimum[e.From], e.Capacity - e.Flow);
                            if (mark[e.To] == 0) queue.AddLast(e.To);
                            else if (mark[e.To] == 2) queue.AddFirst(e.To);
                            mark[e.To] = 1;
                            _parent[e.To] = e.From;
                            _path[e.To] = i;
                        }
                    }
                }

                return _distances[_t] != long.MaxValue;
            }

            public long MinCostFlow() {
                while (FindMinCostWay()) {
                    var toAdd = _minimum[_t];
                    for (var v = _t; v != _s; v = _parent[v]) {
                        var e = _edges[_parent[v]][_path[v]];
                        _edges[_parent[v]][_path[v]].Flow += toAdd;
                        _edges[e.To][e.Reverse].Flow -= toAdd;
                    }
                }
                for (var v = 0; v < _vertexCount; ++v)
                    foreach (var e in _edges[v])
                        if (e.Flow > 0)
                            _cost += e.Flow * e.Cost;
                return _cost;
            }
        }

        private class Edge {
            public readonly int Capacity;
            public readonly int Cost;
            public readonly int From;
            public readonly int Reverse;
            public readonly int To;
            public long Flow;

            public Edge(int from, int to, int flow, int capacity, int cost, int reverse) {
                From = from;
                To = to;
                Flow = flow;
                Capacity = capacity;
                Cost = cost;
                Reverse = reverse;
            }
        }
    }
}