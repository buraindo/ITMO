using System.IO;

namespace LabProblem
{
    public class Fullham
    {
        public static void Main()
        {
            var reader = new StreamReader("fullham.in");
            var writer = new StreamWriter("fullham.out");
            var path = new int[22800000];
            var inputN = reader.ReadLine();
            int n;
            if (inputN != null)
            {
                n = int.Parse(inputN);
            }
            else n = -1;

            for (var i = 0; i < n; i++)
            {
                path[i] = i;
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
            for (var i = 0; i < 2 * n; i++)
            {
                var first = begin;
                var second = first + 1;
                if (!graph[path[first], path[second]])
                {
                    var next = begin + 2;
                    while (!graph[path[first], path[next]] || !graph[path[second], path[next + 1]])
                    {
                        ++next;
                    }

                    for (var j = 0; j < (next - begin) / 2; j++)
                    {
                        var temp = path[begin + j + 1];
                        path[begin + j + 1] = path[next - j];
                        path[next - j] = temp;
                    }
                }
                path[end++] = path[begin++];
            }

            for (var i = begin; i < end; i++)
            {
                writer.Write(path[i] + 1 + " ");
            }
            writer.Close();
        }
    }
}