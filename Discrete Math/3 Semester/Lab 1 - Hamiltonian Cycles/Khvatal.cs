using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace LabProblem
{
    public class Khvatal
    {
        public static void Main()
        {
            var reader = new StreamReader("chvatal.in");
            var writer = new StreamWriter("chvatal.out");
            var inputN = reader.ReadLine();
            int n;
            if (inputN != null)
            {
                n = int.Parse(inputN);
            }
            else n = -1;

            var path = new List<int>(n);
            for (var i = 0; i < n; i++)
            {
                path.Add(i);
            }

            reader.ReadLine();
            var graph = new bool[n, n];
            for (var i = 1; i < n; i++)
            {
                var input = reader.ReadLine();
                if (string.IsNullOrEmpty(input)) continue;
                for (var j = 0; j < i; j++)
                {
                    var hasEdge = input[j] == '1';
                    graph[i, j] = hasEdge;
                    graph[j, i] = hasEdge;
                }
            }

            reader.Close();
            var begin = 0;
            var end = n;
            for (var i = 0; i < n * (n - 1); i++)
            {
                var first = begin;
                var second = first + 1;
                if (!graph[path[first], path[second]])
                {
                    var next = begin + 2;
                    while (next != path.Count - 1 &&
                           (!graph[path[first], path[next]] || !graph[path[second], path[next + 1]]))
                    {
                        ++next;
                    }

                    if (next == path.Count - 1)
                    {
                        var tNext = begin + 2;
                        while (tNext != path.Count && !graph[path[first], path[tNext]])
                        {
                            ++tNext;
                        }

                        path.Reverse(1, tNext);
                    }
                    else
                    {
                        path.Reverse(1, next);
                    }
                }

                path.Add(path[0]);
                path.RemoveAt(0);
            }

            foreach (var v in path)
            {
                writer.Write(v + 1 + " ");
            }

            writer.Close();
        }
    }
}