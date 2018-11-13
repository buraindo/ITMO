using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace LabProblem
{
    public class NKA_Word
    {
        public static Dictionary<char, List<int>>[] Transitions;
        public static List<int> Terminal;
        public static int DevilIndex;

        public static IEnumerable<int> TransitionFunction(Dictionary<char, List<int>> dictionary, char letter)
        {
            return dictionary.ContainsKey(letter) ? dictionary[letter] : new List<int> {DevilIndex};
        }

        public static bool Accepts(string word)
        {
            var R = new List<List<int>> {new List<int> {0}};
            for (var i = 1; i <= word.Length; i++)
            {
                var Current = new List<int>();
                Current = R[i - 1].Where(q => q != DevilIndex).Aggregate(Current, (current, q) => current.Union(TransitionFunction(Transitions[q], word[i - 1])).ToList());
                R.Add(Current);
            }
            var result = R[R.Count - 1];
            return result.Intersect(Terminal).ToArray().Length > 0;
        }

        public static void Main()
        {
            var reader = new StreamReader("problem2.in");
            var writer = new StreamWriter("problem2.out");
            var word = reader.ReadLine();
            var input = reader.ReadLine().Split(' ').ToList().ConvertAll(int.Parse).ToArray();
            var n = input[0];
            var m = input[1];
            var k = input[2];
            Terminal = new List<int>();
            DevilIndex = n;
            Transitions = new Dictionary<char, List<int>>[n];
            for (var i = 0; i < n; i++)
            {
                Transitions[i] = new Dictionary<char, List<int>>();
            }
            var terminals = reader.ReadLine().Split(' ');
            for (var i = 0; i < k; i++)
            {
                Terminal.Add(Convert.ToInt32(terminals[i]) - 1);
            }
            for (var i = 0; i < m; i++)
            {
                var info = reader.ReadLine().Split(' ');
                var from = Convert.ToInt32(info[0]);
                var to = Convert.ToInt32(info[1]);
                var condition = Convert.ToChar(info[2]);
                if (!Transitions[from - 1].ContainsKey(condition))
                    Transitions[from - 1].Add(condition, new List<int> {to - 1});
                else
                {
                    Transitions[from - 1][condition].Add(to - 1);
                }
            }
            reader.Close();
            var accepts = Accepts(word);
            writer.WriteLine(accepts ? "Accepts" : "Rejects");
            writer.Close();
        }
    }
}