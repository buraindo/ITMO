/*using System;
using System.Collections.Generic;

namespace LabProblem {
    public class MovingObjects {
        
        public static void Main() {
            var n = int.Parse(Console.ReadLine());
            var graph = new Graph(n, 2 * n + 2, 0, 2 * n + 1);
            var speed = new double[n];
            var xs = new double[n];
            var xe = new double[n];
            var ys = new double[n];
            var ye = new double[n];
            for (var i = 0; i < n; i++) {
                var input = Console.ReadLine().Split();
                xs[i] = double.Parse(input[0]);
                ys[i] = double.Parse(input[1]);
                speed[i] = double.Parse(input[2]);
            }
            for (var i = 0; i < n; i++) {
                var input = Console.ReadLine().Split();
                xe[i] = double.Parse(input[0]);
                ye[i] = double.Parse(input[1]);
            }
            for (var i = 0; i < n; i++) {
                for (var j = 0; j < n; j++) {
                    var time = Math.Sqrt((xe[j] - xs[i]) * (xe[j] - xs[i])
                                            + (ye[j] - ys[i]) * (ye[j] - ys[i])) / speed[i];
                    graph.AddEdge(1 + i, n + 1 + j, time);
                }
            }
            for (var i = 1; i <= n; i++) {
                graph.AddEdge(0, i, 0);
                graph.AddEdge(n + i, 2 * n + 1, 0);
            }
            graph.Write();
        }

        private class Graph {
            private readonly List<Edge>[] edges;
            private readonly int s;
            private readonly int t;
            private readonly int vertexCount;
            private readonly int count;
            private readonly bool[] used;

            public Graph(int vertexCount, int count, int s, int t) {
                this.vertexCount = vertexCount;
                edges = new List<Edge>[count];
                this.s = s;
                this.t = t;
                this.count = count;
                used = new bool[count];
                for (var i = 0; i < count; i++) {
                    edges[i] = new List<Edge>();
                }
            }

            public void AddEdge(int from, int to, double cost) {
                var e1 = new Edge(to, 0, 1, cost);
                var e2 = new Edge(from, 0, 0, cost);
                e1.rev = e2;
                e2.rev = e1;
                edges[from].Add(e1);
                edges[to].Add(e2);
            }

            private int Dfs(int v, int cMin, double cost) {
                if (v == t) {
                    return cMin;
                }
                used[v] = true;
                foreach (var edge in edges[v]) {
                    if (edge.flow >= edge.maxFlow || used[edge.to] || edge.cost > cost) continue;
                    var delta = Dfs(edge.to, Math.Min(cMin, edge.maxFlow - edge.flow), cost);
                    if (delta <= 0) continue;
                    edge.flow += delta;
                    edge.rev.flow -= delta;
                    return delta;
                }
                return 0;
            }

            private int FindMaxFlow(double cost) {
                var maxFlow = 0;
                for (var i = 0; i < used.Length; i++) {
                    used[i] = false;
                }
                int flow;
                while ((flow = Dfs(s, int.MaxValue, cost)) > 0) {
                    for (var i = 0; i < used.Length; i++) {
                        used[i] = false;
                    }
                    maxFlow += flow;
                }
                return maxFlow;
            }

            private void ResetEdges() {
                for (var i = 0; i < count; i++) {
                    foreach (var edge in edges[i]) {
                        edge.flow = 0;
                    }
                }
            }

            public void Write() {
                const double eps = 0.00001;
                var l = 0.0;
                var r = (double) int.MaxValue;
                while (r - l >= eps) {
                    var mid = (l + r) / 2;
                    int time = FindMaxFlow(mid);
                    if (time == vertexCount) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                    ResetEdges();
                }
                Console.WriteLine(r);
            }
        }
        
        private class Edge {
            public readonly int to;
            public int flow;
            public readonly int maxFlow;
            public readonly double cost;
            public Edge rev;

            public Edge(int to, int flow, int maxFlow, double cost) {
                this.to = to;
                this.flow = flow;
                this.maxFlow = maxFlow;
                this.cost = cost;
            }
        }
    }
}*/