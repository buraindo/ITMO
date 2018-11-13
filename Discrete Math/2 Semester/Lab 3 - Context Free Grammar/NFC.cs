/*using System;
using System.Collections.Generic;
using System.IO;

namespace LabProblem
{
    public class PairCharString
    {
        public readonly int First;
        public readonly string Second;

        public PairCharString(int f, string s)
        {
            First = f;
            Second = s;
        }
    }

    public class PairCharChar
    {
        public readonly char First;
        public readonly char Second;

        public PairCharChar(char f, char s)
        {
            First = f;
            Second = s;
        }
    }

    public static class Nfc
    {

        public static void Main()
        {
            var reader = new StreamReader("cf.in");
            var writer = new StreamWriter("cf.out");
            var transitions = new List<PairCharString>();
            var smallTransitions = new List<PairCharString>();
            var allowed = new List<PairCharChar>();
            var zeroTransitions = new List<int>();
            var input = reader.ReadLine()?.Split(' ');
            if (input != null)
            {
                var n = Convert.ToInt32(input[0]);
                var startState = input[1][0] - 'A';
                for (var i = 0; i < n; i++)
                {
                    var tempInputSplit = reader.ReadLine()?.Split(' ');
                    if (tempInputSplit == null) continue;
                    var symbol = tempInputSplit[0][0] - 'A';
                    var transition = "";
                    if (tempInputSplit.Length > 2) transition = tempInputSplit[2];
                    var small = true;
                    foreach (var t in transition)
                    {
                        if (char.IsLetter(t) && char.IsUpper(t))
                        {
                            small = false;
                        }
                    }
                    if (small)
                    {
                        transitions.Add(new PairCharString(symbol, transition));
                    } else if (transition.Length == 0)
                    {
                        zeroTransitions.Add(symbol);
                    }
                    else
                    {
                        smallTransitions.Add(new PairCharString(symbol, transition));
                    }
                }
                var word = reader.ReadLine();
                if (word != null)
                {
                    var dynamics = new bool[26, word.Length + 1, word.Length + 1];
                    var prefix = new bool[26, word.Length + 1, word.Length + 1, 100];
                    for (var i = 0; i < word.Length; i++)
                    {
                        var j = 0;
                        for (var index = 0; index < smallTransitions.Count; index++, ++j)
                        {
                            var pair = smallTransitions[index];
                            if (pair.Second.Length == 1 && pair.Second[0] == word[i])
                            {
                                dynamics[pair.First - 'A', i, i + 1] = true;
                            }
                            prefix[j, i, i, 0] = true;
                        }
                        foreach (var ch in zeroTransitions)
                        {
                            dynamics[ch, i, i] = true;
                        }
                    }
                    for (var i = 1; i < word.Length; i++)
                    {
                        for (var j = 0; j < word.Length; j++)
                        {
                            var temp = i + j;
                            for (var k = 0; k < transitions.Count; k++)
                            {
                                for (var l = 1; l < transitions[k].Second.Length; l++)
                                {
                                    for (var m = 0; m < temp + 1; m++)
                                        prefix[k, j, temp + 1, l] = prefix[k, j, temp + 1, l] |
                                                                    (prefix[k, j, m, l - 1] &
                                                                     dynamics[transitions[k].Second[l], m, temp + 1]);
                                }
                            }
                        }
                    }
                    for (var i = 0; i < word.Length; i++)
                    {
                        for (var j = 0; j < word.Length; j++)
                        {
                            for (var k = 0; k < transitions.Count; k++)
                            {
                                for (var l = 0; l < transitions.Count; l++)
                                {
                                    if (transitions[k].First == transitions[l].First)
                                    {
                                        dynamics[transitions[l].First, i, j] = dynamics[transitions[l].First, i, j] |
                                                                               prefix[l, i, j,
                                                                                   transitions[l].Second.Length];
                                    }
                                }
                            }
                        }
                    }
                    writer.WriteLine(dynamics[startState, 0, word.Length - 1] ? "yes" : "no");
                }
            }
            writer.Close();
        }
    }
}*/