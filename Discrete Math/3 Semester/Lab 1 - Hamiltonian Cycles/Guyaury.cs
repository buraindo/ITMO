using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace LabProblem
{
    public static class Guyaury
    {
        
        public static void Main()
        {
            var reader = new StreamReader("guyaury.in");
            var writer = new StreamWriter("guyaury.out");
            var inputN = reader.ReadLine();
            int n;
            if (inputN != null)
            {
                n = int.Parse(inputN);
            }
            else n = -1;

            var traversal = new List<int>(Enumerable.Range(0, n));
            var graph = new bool[n, n];
            var path = new List<int>(n);
            var cycle = new List<int>(n);
            reader.ReadLine();
            for (var i = 1; i < n; i++)
            {
                var input = reader.ReadLine();
                if (string.IsNullOrEmpty(input)) continue;
                for (var j = 0; j < i; j++)
                {
                    var hasEdge = input[j] == '1';
                    graph[i, j] = hasEdge;
                    graph[j, i] = !hasEdge;
                }
            }
            reader.Close();
            foreach (var v in traversal)
            {
                if (path.Count == 0)
                {
                    path.Add(v);
                }
                else
                {
                    var next = 0;
                    while (next < path.Count && graph[path[next], v])
                    {
                        next++;
                    }
                    path.Insert(next, v);
                }
            }

            var start = path[0];
            int index;
            for (index = path.Count - 1; index >= 2; index--)
            {
                if (graph[path[index], start])
                {
                    break;
                }
            }

            cycle.AddRange(path.GetRange(0, index + 1));
            path.RemoveRange(0, index + 1);
            for (var i = 0; i < path.Count;)
            {
                var j = 0;
                while (j != cycle.Count && graph[cycle[j], path[i]])
                {
                    ++j;
                }

                if (j != cycle.Count)
                {
                    cycle.InsertRange(j, path.GetRange(0, i + 1));
                    path.RemoveRange(0, i + 1);
                    i = 0;
                }
                else
                {
                    ++i;
                }
            }
            foreach (var vertex in cycle)
            {
                writer.Write(vertex + 1 + " ");
            }
            writer.Close();
        }
    }
}