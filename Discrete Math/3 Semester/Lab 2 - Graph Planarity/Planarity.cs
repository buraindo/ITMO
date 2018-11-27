using System;
using System.Collections.Generic;

namespace LabProblem
{
    public static class Util
    {
        public static List<int>[] AdjacencyMatrix;
        public static List<Edge> Edges;
        public static int[] Traversal;
        public static int[] Part;
    }
    public class Edge
    {
        public int Start { get; private set; }
        public int End { get; private set; }

        public Edge()
        {
        }

        public Edge(int start, int end)
        {
            Start = start;
            End = end;
        }

        public bool Intersects(Edge other)
        {
            return  Util.Traversal[other.End] > Util.Traversal[Start] 
                    && Util.Traversal[other.End] < Util.Traversal[End] 
                    && Util.Traversal[other.Start] < Util.Traversal[Start]
                    || Util.Traversal[End] > Util.Traversal[other.Start] 
                    && Util.Traversal[End] < Util.Traversal[other.End] 
                    && Util.Traversal[Start] < Util.Traversal[other.Start];
        }

        public void Turn()
        {
            var t = Start;
            Start = End;
            End = t;
        }
    }

    public class Planarity
    {
        private static bool IsBipartite()
        {
            var result = true;
            for (var i = 0; i < Util.AdjacencyMatrix.Length; i++)
            {
                if (Util.Part[i] != 0) continue;
                var q = new Queue<int>();
                q.Enqueue(i);
                Util.Part[i] = 1;
                while (q.Count > 0)
                {
                    var start = q.Dequeue();
                    foreach (var end in Util.AdjacencyMatrix[start])
                    {
                        if (Util.Part[end] == 0)
                        {
                            Util.Part[end] = -1 * Util.Part[start];
                            q.Enqueue(end);
                        }
                        else
                        {
                            result &= Util.Part[start] != Util.Part[end];
                        }
                    }
                }
            }

            return result;
        }
        public static void Main()
        {
            var input = Console.ReadLine().Split(' ');
            var n = Convert.ToInt32(input[0]);
            var m = Convert.ToInt32(input[1]);
            Util.AdjacencyMatrix = new List<int>[m];
            Util.Edges = new List<Edge>();
            Util.Traversal = new int[n];
            Util.Part = new int[m];
            for (var i = 0; i < m; i++)
            {
                var inputEdge = Console.ReadLine().Split(' ');
                var start = Convert.ToInt32(inputEdge[0]) - 1;
                var end = Convert.ToInt32(inputEdge[1]) - 1;
                Util.Edges.Add(new Edge(start, end));
            }

            var inputTraversal = Console.ReadLine().Split(' ');
            for (var i = 0; i < n; i++)
            {
                Util.Traversal[Convert.ToInt32(inputTraversal[i]) - 1] = i;
            }

            for (var i = 0; i < m; i++)
            {
                Util.AdjacencyMatrix[i] = new List<int>();
                if (Util.Traversal[Util.Edges[i].Start] > Util.Traversal[Util.Edges[i].End])
                {
                    Util.Edges[i].Turn();
                }
            }

            for (var i = 0; i < m; i++)
            {
                for (var j = 0; j < m; j++)
                {
                    if (i != j && Util.Edges[i].Intersects(Util.Edges[j]))
                    {
                        Util.AdjacencyMatrix[i].Add(j);
                    }
                }
            }

            if (!IsBipartite())
            {
                Console.WriteLine("NO");
            }
            else
            {
                Console.WriteLine("YES");
                for (var i = 0; i < n; i++)
                {
                    Console.Write(Util.Traversal[i] * 2 + " " + 0 + " ");
                }
                Console.WriteLine();
                for (var i = 0; i < m; i++)
                {
                    Console.WriteLine(Util.Traversal[Util.Edges[i].Start] + Util.Traversal[Util.Edges[i].End] + " " + Math.Abs(Util.Traversal[Util.Edges[i].Start] - Util.Traversal[Util.Edges[i].End]) * Util.Part[i]);
                }
            }
        }
    }
}