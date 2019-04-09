using System;
using System.Collections.Generic;

namespace LabProblem {
    public class GreatWall {
        public static void Main() {
            var input = Console.ReadLine().Split();
            var m = int.Parse(input[0]);
            var n = int.Parse(input[1]);
            var graph = new Graph(n * m * 2, m, n);
            for (var i = 0; i < m; i++) {
                graph.map[i] = Console.ReadLine();
            }

            for (var i = 0; i < m; i++) {
                for (var j = 0; j < n; j++) {
                    switch (graph.map[i][j]) {
                        case '-':
                            graph.AddOriented(i * n + j, i * n + j + n * m, int.MaxValue, i, j);
                            break;
                        case '.':
                            graph.AddOriented(i * n + j, i * n + j + n * m, 1, i, j);
                            break;
                        case '#':
                            graph.AddOriented(i * n + j, i * n + j + n * m, 0, i, j);
                            break;
                        case 'A':
                            graph.s = i * n + j + n * m;
                            break;
                        default:
                            graph.t = i * n + j;
                            break;
                    }
                }
            }

            for (var i = 0; i < m - 1; i++) {
                for (var j = 0; j < n - 1; j++) {
                    graph.AddEdge(i * n + j + n * m, (i + 1) * n + j, int.MaxValue, -i, j);
                    graph.AddEdge((i + 1) * n + j + n * m, i * n + j, int.MaxValue, -i, j);
                    graph.AddEdge(i * n + j + n * m, i * n + j + 1, int.MaxValue, -i, j);
                    graph.AddEdge(i * n + j + 1 + n * m, i * n + j, int.MaxValue, -i, j);
                }
            }

            for (var i = 0; i < m - 1; i++) {
                graph.AddEdge(i * n + (n - 1) + n * m, (i + 1) * n + (n - 1), int.MaxValue, -(n - 1), m);
                graph.AddEdge((i + 1) * n + (n - 1) + n * m, i * n + (n - 1), int.MaxValue, -(n - 1), m);
            }

            for (var j = 0; j < n - 1; j++) {
                graph.AddEdge((m - 1) * n + j + n * m, (m - 1) * n + j + 1, int.MaxValue, -(m - 1), n);
                graph.AddEdge((m - 1) * n + j + 1 + n * m, (m - 1) * n + j, int.MaxValue, -(m - 1), n);
            }

            graph.Write();
        }

        private class Graph {
            private readonly List<Edge>[] edges;
            private readonly int vertexCount;
            private readonly int width;
            private readonly int height;
            private readonly bool[] used;
            private readonly int[] distances;
            private readonly int[] visit;
            private readonly List<int> path = new List<int>();

            public readonly string[] map;

            public int s;
            public int t;

            public Graph(int vertexCount, int width, int height) {
                edges = new List<Edge>[vertexCount];
                map = new string[width];
                this.vertexCount = vertexCount;
                this.width = width;
                this.height = height;
                used = new bool[vertexCount];
                distances = new int[vertexCount];
                visit = new int[vertexCount];
                for (var i = 0; i < vertexCount; i++) {
                    edges[i] = new List<Edge>();
                }
            }

            public void AddEdge(int from, int to, long capacity, int x, int y) {
                var e1 = new Edge(to, 0, capacity, x, y);
                var e2 = new Edge(from, 0, 0, x, y);
                e1.rev = e2;
                e2.rev = e1;
                edges[from].Add(e1);
                edges[to].Add(e2);
            }

            public void AddOriented(int from, int to, long capacity, int x, int y) {
                edges[from].Add(new Edge(to, 0, capacity, x, y, true));
            }

            private long Dfs(int v, long cMin) {
                if (v == t) {
                    return cMin;
                }

                for (var i = visit[v]; i < edges[v].Count; i++) {
                    var e = edges[v][i];
                    if (e.flow < e.capacity && distances[e.to] == distances[v] + 1) {
                        var delta = Dfs(e.to, Math.Min(cMin, e.capacity - e.flow));
                        if (delta > 0) {
                            e.flow += delta;
                            if (!e.isOriented) e.rev.flow -= delta;
                            return delta;
                        }
                    }

                    visit[v] = i + 1;
                }

                return 0;
            }

            private void Dfs2(int v) {
                used[v] = true;
                path.Add(v);
                foreach (var e in edges[v]) {
                    if (!used[e.to] && e.flow < e.capacity) {
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
                        if (e.flow >= e.capacity || used[e.to]) continue;
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
                var maxFlow = 0L;
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
                var maxFlow = FindMaxFlow();
                if (maxFlow >= int.MaxValue) {
                    Console.WriteLine(-1);
                    return;
                }

                Console.WriteLine(maxFlow);
                for (var i = 0; i < used.Length; i++) {
                    used[i] = false;
                }

                Dfs2(s);
                var newMap = new char[width, height];
                for (var i = 0; i < width; i++) {
                    for (var j = 0; j < height; j++) {
                        newMap[i, j] = map[i][j];
                    }
                }

                foreach (var v in path) {
                    foreach (var e in edges[v]) {
                        if (used[e.to]) continue;
                        if (e.flow == 1) newMap[e.xInMap, e.yInMap] = '+';
                    }
                }

                for (var i = 0; i < width; i++) {
                    for (var j = 0; j < height; j++) {
                        Console.Write(newMap[i, j]);
                    }

                    Console.WriteLine();
                }
            }
        }

        private class Edge {
            public readonly int to;
            public long flow;
            public readonly long capacity;
            public readonly int xInMap;
            public readonly int yInMap;
            public readonly bool isOriented;
            public Edge rev;

            public Edge(int to, long flow, long capacity, int xInMap, int yInMap, bool isOriented = false) {
                this.to = to;
                this.flow = flow;
                this.capacity = capacity;
                this.xInMap = xInMap;
                this.yInMap = yInMap;
                this.isOriented = isOriented;
            }
        }
    }
}