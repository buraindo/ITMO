using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace LabProblem {
    public class MinCost {
        public static void Main() {
            using (var reader = new StreamReader("mincost.in"))
            using (var writer = new StreamWriter("mincost.out")) {
                var input = reader.ReadLine().Split().Select(int.Parse).ToArray();
                var n = input[0];
                var m = input[1];
                var graph = new Graph(n, 0, n - 1);
                for (var i = 0; i < m; i++) {
                    input = reader.ReadLine().Split().Select(int.Parse).ToArray();
                    var from = input[0] - 1;
                    var to = input[1] - 1;
                    var capacity = input[2];
                    var cost = input[3];
                    graph.AddEdge(from, to, capacity, cost);
                }
                writer.Write(graph.MinCostFlow() + "");
            }
        }

        private class Graph {
            private long _cost;
            private readonly long[] _distances;
            private readonly List<Edge>[] _edges;
            private readonly Edge[] _path;
            private readonly int[] _parent;
            private readonly int _s;
            private readonly int _t;
            private readonly int _vertexCount;

            public Graph(int vertexCount, int s, int t) {
                _edges = new List<Edge>[vertexCount];
                _vertexCount = vertexCount;
                _s = s;
                _t = t;
                _cost = 0;
                _distances = new long[vertexCount];
                _parent = new int[vertexCount];
                _path = new Edge[vertexCount];
                for (var i = 0; i < vertexCount; i++) _edges[i] = new List<Edge>();
            }

            public void AddEdge(int from, int to, int cap, int cost) {
                var e1 = new Edge(from, to, 0, cap, cost);
                var e2 = new Edge(to, from, 0, 0, -cost);
                e1.Reverse = e2;
                e2.Reverse = e1;
                _edges[from].Add(e1);
                _edges[to].Add(e2);
            }

            private bool FindMinCostWay() {
                var queue = new LinkedList<int>();
                queue.AddLast(_s);
                for (var i = 0; i < _distances.Length; i++) _distances[i] = long.MaxValue;
                var mark = new int[_vertexCount];
                _distances[_s] = 0;
                while (queue.Count > 0) {
                    var v = queue.First.Value;
                    queue.RemoveFirst();
                    mark[v] = 2;
                    for (var i = 0; i < _edges[v].Count; i++) {
                        var e = _edges[v][i];
                        if (_distances[e.From] != long.MaxValue && e.Flow < e.Capacity &&
                            _distances[e.From] + e.Cost < _distances[e.To]) {
                            _distances[e.To] = _distances[e.From] + e.Cost;
                            if (mark[e.To] == 0) queue.AddLast(e.To);
                            else if (mark[e.To] == 2) queue.AddFirst(e.To);
                            mark[e.To] = 1;
                            _parent[e.To] = e.From;
                            _path[e.To] = e;
                        }
                    }
                }
                return _distances[_t] != long.MaxValue;
            }

            public long MinCostFlow() {
                while (FindMinCostWay()) {
                    var toAdd = long.MaxValue;
                    for (var v = _t; v != _s; v = _parent[v]) {
                        var e = _path[v];
                        toAdd = Math.Min(toAdd, e.Capacity - e.Flow);
                    }
                    for (var v = _t; v != _s; v = _parent[v]) {
                        var e = _path[v];
                        e.Flow += toAdd;
                        e.Reverse.Flow -= toAdd;
                        _cost += toAdd * e.Cost;
                    }
                }
                return _cost;
            }
        }

        private class Edge {
            public readonly int Capacity;
            public readonly int Cost;
            public readonly int From;
            public readonly int To;
            public Edge Reverse;
            public long Flow;

            public Edge(int from, int to, int flow, int capacity, int cost) {
                From = from;
                To = to;
                Flow = flow;
                Capacity = capacity;
                Cost = cost;
            }
        }
    }
}