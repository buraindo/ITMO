/*using System;
using System.Collections.Generic;

namespace LabProblem {
    public class Matching {
        public static void Main() {
            var input = Console.ReadLine().Split();
            var n = int.Parse(input[0]);
            var m = int.Parse(input[1]);
            var graph = new Graph(n, n + m + 2, 0, n + m + 1);
            for (var i = 1; i <= n; i++) {
                graph.AddEdge(0, i, 0, 1);
                var edge = Array.ConvertAll(Console.ReadLine().Split(), Convert.ToInt32);
                foreach (var item in edge) {
                    if (item != 0)
                        graph.AddEdge(i, n + item, 0, 1);
                }
            }
            for (var i = 1; i <= m; i++) {
                graph.AddEdge(n + i, n + m + 1, 0, 1);
            }
            graph.Write();
        }

        private class Graph {
            private readonly List<Edge>[] edges;
            private readonly int s;
            private readonly int t;
            private readonly int vertexCount;
            private readonly bool[] used;

            public Graph(int vertexCount, int count, int s, int t) {
                this.vertexCount = vertexCount;
                edges = new List<Edge>[count];
                this.s = s;
                this.t = t;
                used = new bool[count];
                for (var i = 0; i < count; i++) {
                    edges[i] = new List<Edge>();
                }
            }

            public void AddEdge(int from, int to, int flow, int maxFlow) {
                var e1 = new Edge(from, to, flow, maxFlow);
                var e2 = new Edge(to, from, -flow, 0);
                e1.rev = e2;
                e2.rev = e1;
                edges[from].Add(e1);
                edges[to].Add(e2);
            }

            private int Dfs(int v, int cMin) {
                if (v == t) {
                    return cMin;
                }
                used[v] = true;
                foreach (var edge in edges[v]) {
                    if (edge.flow >= edge.maxFlow || used[edge.to]) continue;
                    var delta = Dfs(edge.to, Math.Min(cMin, edge.maxFlow - edge.flow));
                    if (delta <= 0) continue;
                    edge.flow += delta;
                    edge.rev.flow -= delta;
                    return delta;
                }
                return 0;
            }

            private long FindMaxFlow() {
                var maxFlow = 0L;
                for (var i = 0; i < used.Length; i++) {
                    used[i] = false;
                }
                int flow;
                while ((flow = Dfs(s, int.MaxValue)) > 0) {
                    for (var i = 0; i < used.Length; i++) {
                        used[i] = false;
                    }
                    maxFlow += flow;
                }
                return maxFlow;
            }

            public void Write() {
                var result = FindMaxFlow();
                Console.WriteLine(result);
                for (var i = 1; i <= vertexCount; i++) {
                    foreach (var edge in edges[i]) {
                        if (edge.flow == 1) {
                            Console.WriteLine($"{edge.from} {edge.to - vertexCount}");
                        }
                    }
                }
            }
        }

        private class Edge {
            public readonly int from;
            public readonly int to;
            public int flow;
            public readonly int maxFlow;
            public Edge rev;

            public Edge(int from, int to, int flow, int maxFlow) {
                this.from = from;
                this.to = to;
                this.flow = flow;
                this.maxFlow = maxFlow;
            }
        }
    }
}*/