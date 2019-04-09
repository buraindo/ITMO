using System;
using System.Collections.Generic;

namespace LabProblem {
    public class MinimalCut {
        public static void Main() {
            var input = Console.ReadLine().Split(' ');
            var n = int.Parse(input[0]);
            var m = int.Parse(input[1]);
            var graph = new Graph(n, 0, n - 1);
            for (var i = 0; i < m; i++) {
                var s = Console.ReadLine().Split(' ');
                var u = int.Parse(s[0]);
                var v = int.Parse(s[1]);
                var c = long.Parse(s[2]);
                graph.AddEdge(u - 1, v - 1, 0, c, i);
            }
            graph.Write();
        }

        private class Graph {
            private readonly List<Edge>[] edges;
            private readonly int vertexCount;
            private readonly int s;
            private readonly int t;
            private readonly bool[] used;
            private readonly int[] distances;
            private readonly int[] visit;
            private readonly List<int> minCut = new List<int>();
            private readonly List<int> list = new List<int>();

            public Graph(int vertexCount, int s, int t) {
                edges = new List<Edge>[vertexCount];
                this.vertexCount = vertexCount;
                this.s = s;
                this.t = t;
                used = new bool[vertexCount];
                distances = new int[vertexCount];
                visit = new int[vertexCount];
                for (var i = 0; i < vertexCount; i++) {
                    edges[i] = new List<Edge>();
                }
            }

            public void AddEdge(int from, int to, long flow, long maxFlow, int num) {
                var e1 = new Edge(to, flow, maxFlow, num);
                var e2 = new Edge(from, -flow, maxFlow, num);
                e1.rev = e2;
                e2.rev = e1;
                edges[from].Add(e1);
                edges[to].Add(e2);
            }

            private long Dfs(int v, long cMin) {
                if (v == t) {
                    return cMin;
                }
                for (var i = visit[v]; i < edges[v].Count; i++) {
                    var e = edges[v][i];
                    if (e.flow < e.maxFlow && distances[e.to] == distances[v] + 1) {
                        var delta = Dfs(e.to, Math.Min(cMin, e.maxFlow - e.flow));
                        if (delta > 0) {
                            e.flow += delta;
                            e.rev.flow -= delta;
                            return delta;
                        }
                    }
                    visit[v] = i + 1;
                }
                return 0;
            }
            
            private void Dfs2(int v) {
                used[v] = true;
                list.Add(v);
                foreach (var e in edges[v]) {
                    if (!used[e.to] && e.flow < e.maxFlow && e.maxFlow != 0) {
                        Dfs2(e.to);
                    }
                }
            }

            private bool Bfs() {
                var queue = new Queue<int>(vertexCount);
                queue.Enqueue(s);
                for (var i = 0; i < distances.Length; i++) {
                    distances[i] = int.MaxValue;
                }
                distances[s] = 0;
                for (var i = 0; i < used.Length; i++) {
                    used[i] = false;
                }
                used[s] = true;
                while (queue.Count > 0) {
                    var v = queue.Dequeue();
                    foreach (var e in edges[v]) {
                        if (e.flow >= e.maxFlow || used[e.to]) continue;
                        used[e.to] = true;
                        distances[e.to] = distances[v] + 1;
                        queue.Enqueue(e.to);
                        if (e.to == t) {
                            return true;
                        }
                    }
                }
                return false;
            }

            private long FindMaxFlow() {
                long maxFlow = 0;
                while (Bfs()) {
                    for (var i = 0; i < visit.Length; i++) {
                        visit[i] = 0;
                    }
                    long flow;
                    while ((flow = Dfs(s, long.MaxValue)) > 0) {
                        maxFlow += flow;
                    }
                }

                return maxFlow;
            }

            public void Write() {
                var ans = FindMaxFlow();
                for (var i = 0; i < used.Length; i++) {
                    used[i] = false;
                }
                Dfs2(0);
                foreach (var v in list) {
                    foreach (var e in edges[v]) {
                        if (used[e.to]) continue;
                        minCut.Add(e.num + 1);
                    }
                }
                minCut.Sort();
                Console.WriteLine(minCut.Count + " " + ans);
                foreach (var num in minCut) {
                    Console.Write(num + " ");
                }
            }
        }

        private class Edge {
            public readonly int to;
            public long flow;
            public readonly long maxFlow;
            public readonly int num;
            public Edge rev;

            public Edge(int to, long flow, long maxFlow, int num) {
                this.to = to;
                this.flow = flow;
                this.maxFlow = maxFlow;
                this.num = num;
            }
        }
    }
}