using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace LabProblem
{
    public class CountWordsL_DKA
    {
        private const int MODULO = 1000000007;

        public static void Main()
        {
            var reader = new StreamReader("problem4.in");
            var writer = new StreamWriter("problem4.out");
            var input = reader.ReadLine().Split(' ');
            var n = Convert.ToInt32(input[0]);
            var m = Convert.ToInt32(input[1]);
            var k = Convert.ToInt32(input[2]);
            var l = Convert.ToInt32(input[3]);
            var transitions = new Dictionary<char, int>[n];
            var terminalPoints = new HashSet<int>();
            for (var i = 0; i != n; i++)
            {
                transitions[i] = new Dictionary<char, int>();
            }
            input = reader.ReadLine().Split(' ');
            for (var i = 0; i != k; i++)
            {
                var a = Convert.ToInt32(input[i]) - 1;
                terminalPoints.Add(a);
            }
            for (var i = 0; i != m; i++)
            {
                var localInput = reader.ReadLine().Split(' ');
                var a = Convert.ToInt32(localInput[0]) - 1;
                var b = Convert.ToInt32(localInput[1]) - 1;
                var key = localInput[2][0];
                if (!transitions[a].ContainsKey(key))
                    transitions[a].Add(key, b);
            }
            var currentPath = new HashSet<int> {0};
            var copyOfCurrentPath = new HashSet<int>();
            var counts = new long[n];
            var copyCounts = new long[n];
            for (var i = 0; i != l; ++i) {
                foreach (var point in currentPath) {
                    foreach (var transition in transitions[point]) {
                        if (counts[point] == 0)
                            copyCounts[transition.Value] = (copyCounts[transition.Value] + 1) % MODULO;
                        else
                            copyCounts[transition.Value] = (copyCounts[transition.Value] + counts[point]) % MODULO;

                        copyOfCurrentPath.Add(transition.Value);
                    }
                }
                for (var j = 0; j != n; j++) {
                    counts[j] = copyCounts[j];
                    copyCounts[j] = 0;
                }
                currentPath = new HashSet<int>(copyOfCurrentPath);
                copyOfCurrentPath.Clear();
            }
            var answer = currentPath.Where(j => terminalPoints.Contains(j)).Aggregate(0L, (current, j) => (current + counts[j]) % MODULO);
            writer.WriteLine(answer);
            writer.Close();
        }
    }
}