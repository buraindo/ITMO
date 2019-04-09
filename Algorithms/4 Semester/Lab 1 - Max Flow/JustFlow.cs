/*using System;
using System.Collections.Generic;

namespace LabProblem {
    public class JustFlow {
        private class Graph {
            private readonly List<Edge>[] edges;
            private readonly int vertexCount;
            private readonly int edgeCount;
            private readonly int s;
            private readonly int t;
            private readonly int[] distances;
            private readonly int[] visit;

            public Graph(int vertexCount, int edgeCount, int s, int t) {
                edges = new List<Edge>[vertexCount];
                this.vertexCount = vertexCount;
                this.edgeCount = edgeCount;
                this.s = s;
                this.t = t;
                distances = new int[vertexCount];
                visit = new int[vertexCount];
                for (var i = 0; i < vertexCount; i++) {
                    edges[i] = new List<Edge>();
                }
            }

            public void AddEdge(int from, int to, double flow, double maxFlow, int num) {
                var e1 = new Edge(to, flow, maxFlow, num, true);
                var e2 = new Edge(from, -flow, maxFlow, num, false);
                e1.reversed = e2;
                e2.reversed = e1;
                edges[from].Add(e1);
                edges[to].Add(e2);
            }

            private double Dfs(int v, double cMin) {
                if (v == t) {
                    return cMin;
                }
                for (var i = visit[v]; i < edges[v].Count; i++) {
                    var e = edges[v][i];
                    if (e.flow < e.capacity && distances[e.to] == distances[v] + 1) {
                        var delta = Dfs(e.to, Math.Min(cMin, e.capacity - e.flow));
                        if (delta > 0) {
                            e.flow += delta;
                            e.reversed.flow -= delta;
                            return delta;
                        }
                    }
                    visit[v] = i + 1;
                }
                return 0;
            }

            private bool Bfs() {
                var queue = new Queue<int>(vertexCount);
                queue.Enqueue(s);
                for (var i = 0; i < distances.Length; i++) {
                    distances[i] = int.MaxValue;
                }
                distances[s] = 0;
                while (queue.Count > 0) {
                    var v = queue.Dequeue();
                    foreach (var e in edges[v]) {
                        if (!(e.flow < e.capacity) || distances[e.to] != int.MaxValue) continue;
                        distances[e.to] = distances[v] + 1;
                        queue.Enqueue(e.to);
                        if (e.to == t) {
                            return true;
                        }
                    }
                }
                return false;
            }

            private double FindMaxFlow() {
                double maxFlow = 0;
                while (Bfs()) {
                    var i = 0;
                    for (; i < visit.Length; i++) {
                        visit[i] = 0;
                    }
                    double flow;
                    while ((flow = Dfs(s, int.MaxValue)) > 0) {
                        maxFlow += flow;
                    }
                }
                return maxFlow;
            }

            public void Write() {
                Console.WriteLine(FindMaxFlow());
                var flows = new double[edgeCount];
                for (var i = 0; i < vertexCount; i++) {
                    foreach (var v in edges[i]) {
                        if (!v.isMain) continue;
                        flows[v.num] = v.flow;
                    }
                }
                for (var i = 0; i < edgeCount; i++) {
                    Console.WriteLine(flows[i]);
                }
            }
        }

        public class Edge {
            public readonly int to;
            public double flow;
            public readonly double capacity;
            public readonly int num;
            public readonly bool isMain;
            public Edge reversed;

            public Edge(int to, double flow, double capacity, int num, bool isMain) {
                this.to = to;
                this.flow = flow;
                this.capacity = capacity;
                this.num = num;
                this.isMain = isMain;
            }
        }

        public static void Main() {
            var n = int.Parse(Console.ReadLine());
            var m = int.Parse(Console.ReadLine());
            var graph = new Graph(n, m, 0, n - 1);
            for (var i = 0; i < m; i++) {
                var s = Console.ReadLine().Split(' ');
                var u = int.Parse(s[0]);
                var v = int.Parse(s[1]);
                var c = long.Parse(s[2]);
                graph.AddEdge(u - 1, v - 1, 0, c, i);
            }
            graph.Write();
        }
    }
}*/