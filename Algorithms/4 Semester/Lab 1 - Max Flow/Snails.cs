using System;
using System.Collections.Generic;

namespace LabProblem {
    public class Snails {

        public static void Main() {
            var input = Console.ReadLine().Split();
            var n = int.Parse(input[0]);
            var m = int.Parse(input[1]);
            var s = int.Parse(input[2]);
            var t = int.Parse(input[3]);
            var graph = new Graph(n + 1, s, t);
            for (var i = 0; i < m; i++) {
                var edge = Console.ReadLine().Split();
                var u = int.Parse(edge[0]);
                var v = int.Parse(edge[1]);
                graph.AddEdge(u, v);
            }

            graph.Write();
        }

        private class Graph {
            private readonly List<Edge>[] edges;
            private readonly int s;
            private readonly int t;
            private readonly bool[] used;
            private int maxFlow;

            public Graph(int n, int s, int t) {
                edges = new List<Edge>[n];
                this.s = s;
                this.t = t;
                used = new bool[n];
                for (var i = 0; i < n; i++) {
                    edges[i] = new List<Edge>();
                }
            }

            public void AddEdge(int from, int to) {
                var e1 = new Edge(to, 0, 1);
                var e2 = new Edge(from, 0, 0);
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
                foreach (var e in edges[v]) {
                    if (e.flow >= e.capacity || used[e.to]) continue;
                    var delta = Dfs(e.to, Math.Min(cMin, e.capacity - e.flow));
                    if (delta <= 0) continue;
                    e.flow += delta;
                    e.rev.flow -= delta;
                    return delta;
                }
                return 0;
            }

            private void FindMaxFlow() {
                while (true) {
                    for (var i = 0; i < used.Length; i++) {
                        used[i] = false;
                    }
                    var flow = Dfs(s, 1000000000);
                    maxFlow += flow;
                    if (flow == 0) break;
                }
            }

            public void Write() {
                FindMaxFlow();
                if (maxFlow >= 2) {
                    Console.WriteLine("YES");
                    var dest = s;
                    while (dest != t) {
                        Console.Write(dest + " ");
                        foreach (var e in edges[dest]) {
                            if (e.used || e.flow != 1) continue;
                            e.used = true;
                            dest = e.to;
                            break;
                        }
                    }
                    Console.WriteLine(t);
                    dest = s;
                    while (dest != t) {
                        Console.Write(dest + " ");
                        foreach (var e in edges[dest]) {
                            if (e.used || e.flow != 1) continue;
                            e.used = true;
                            dest = e.to;
                            break;
                        }
                    }
                    Console.WriteLine(t);
                } else Console.WriteLine("NO");
            }
        }

        private class Edge {
            public readonly int to;
            public int flow;
            public readonly int capacity;
            public bool used;
            public Edge rev;

            public Edge(int to, int flow, int capacity) {
                this.to = to;
                this.flow = flow;
                this.capacity = capacity;
                this.used = false;
            }
        }
    }
}