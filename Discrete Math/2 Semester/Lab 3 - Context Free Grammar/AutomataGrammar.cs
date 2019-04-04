using System;
using System.Collections.Generic;
using System.IO;

namespace LabProblem
{
    public class AutomataGrammar
    {
        private static Dictionary<char, List<string>> Grammar = new Dictionary<char, List<string>>();

        private static bool Check(char currentState, string word, int step)
        {
            if (step == word.Length) return false;
            var result = false;
            var transitions = Grammar.ContainsKey(currentState) ? Grammar[currentState] : new List<string>();
            foreach (var transition in transitions)
            {
                if (transition.Length == 1)
                {
                    result |= step == word.Length - 1 && transition[0] == word[word.Length - 1];
                }
                else
                {
                    if (transition[0] == word[step])
                    {
                        result |= Check(transition[1], word, step + 1);
                    }
                }
            }
            return result;
        }

        public static void Main()
        {
            var reader = new StreamReader("automaton.in");
            var writer = new StreamWriter("automaton.out");
            var input = reader.ReadLine()?.Split(' ');
            if (input != null)
            {
                var n = Convert.ToInt32(input[0]);
                var startState = input[1][0];
                for (var i = 0; i < n; i++)
                {
                    input = reader.ReadLine()?.Split(' ');
                    if (input == null) continue;
                    var symbol = input[0][0];
                    var transition = input[2];
                    if (Grammar.ContainsKey(symbol))
                    {
                        Grammar[symbol].Add(transition);
                    }
                    else
                    {
                        Grammar.Add(symbol, new List<string> {transition});
                    }
                }
                var m = Convert.ToInt32(reader.ReadLine());
                for (var i = 0; i < m; i++)
                {
                    var word = reader.ReadLine();
                    try
                    {
                        writer.WriteLine(Check(startState, word, 0) ? "yes" : "no");
                    }
                    catch (Exception)
                    {
                        
                    }
                }
            }
            writer.Close();
        }
    }
}