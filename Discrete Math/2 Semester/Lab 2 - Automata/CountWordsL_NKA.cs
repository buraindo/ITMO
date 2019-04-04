using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Runtime.CompilerServices;

namespace LabProblem
{
    public class CountWordsL_NKA
    {

        private const int MODULO = 1000000007;
        private static int FoundIndex;

        private static bool Exists(List<HashSet<int>> arr, HashSet<int> set)
        {
            for (var i = 0; i < arr.Count; i++)
            {
                if (!arr[i].Equals(set)) continue;
                FoundIndex = i;
                return true;
            }
            return false;
        }
        
        public static void Main()
        {
            var reader = new StreamReader("problem5.in");
            var writer = new StreamWriter("problem5.out");
            var input = reader.ReadLine().Split(' ');
            var n = Convert.ToInt32(input[0]);
            var m = Convert.ToInt32(input[1]);
            var k = Convert.ToInt32(input[2]);
            var l = Convert.ToInt32(input[3]);
            var NKATransitions = new List<int>[n, 30];
            var transitions = new List<Dictionary<char, int>>();
            var terminalPoints = new HashSet<int>();
            for (var i = 0; i != n; i++)
            {
                for (var j = 0; j != 26; j++)
                {
                    NKATransitions[i, j] = new List<int>();
                }
            }
            input = reader.ReadLine().Split(' ');
            for (var i = 0; i != k; i++)
            {
                terminalPoints.Add(Convert.ToInt32(input[i]));
            }
            for (var i = 0; i != m; i++)
            {
                input = reader.ReadLine().Split(' ');
                var a = Convert.ToInt32(input[0]) - 1;
                var b = Convert.ToInt32(input[1]) - 1;
                var key = input[2][0];
                NKATransitions[a, key - 'a'].Add(b);
            }
            var path = new Queue<KeyValuePair<HashSet<int>, int>>();
            var currentPath = new HashSet<int>{0};
            var current = new List<HashSet<int>> {currentPath};
            var currentTransitions = new Dictionary<char, int>();
            var temp = new KeyValuePair<HashSet<int>, int>(currentPath, 0);
            path.Enqueue(temp);
            transitions.Add(currentTransitions);
            var DKATerminals = new HashSet<int>();
            if (terminalPoints.Contains(0))
            {
                DKATerminals.Add(0);
            }
            while (path.Count > 0)
            {
                temp = path.Dequeue();
                for (var i = 0; i < 26; i++)
                {
                    var last = new HashSet<int>();
                    var terminal = false;
                    foreach (var point in temp.Key)
                    {
                        if (NKATransitions[point, i].Count == 0) continue;
                        var tempList = NKATransitions[point, i];
                        for (var j = 0; j != tempList.Count; j++)
                        {
                            last.Add(tempList[j]);
                            if (terminalPoints.Contains(tempList[j]))
                            {
                                terminal = true;
                            }
                        }
                    }
                    if (last.Count > 0 && !Exists(current, last))
                    {
                        path.Enqueue(new KeyValuePair<HashSet<int>, int>(last, current.Count));
                        transitions[temp.Value][(char)('a' + i)] = current.Count;
                        if (terminal)
                        {
                            DKATerminals.Add(current.Count);
                        }
                        current.Add(last);
                        transitions.Add(currentTransitions);
                    }
                    else if (last.Count > 0)
                    {
                        transitions[temp.Value][(char)('a' + i)] = FoundIndex;
                    }
                }
            }
            terminalPoints = new HashSet<int>(DKATerminals);
            n = transitions.Count;
            var res = new long[n];
            var newRes = new long[n];
            var curVert = new HashSet<int> {0};
            var newCurVert = new HashSet<int>();
            for (var i = 0; i < l; i++)
            {
                foreach (var j in curVert)
                {
                    foreach (var transition in transitions[j])
                    {
                        if (res[j] == 0)
                        {
                            newRes[transition.Value] = (newRes[transition.Value] + 1) % MODULO;
                        }
                        else
                        {
                            newRes[transition.Value] = (newRes[transition.Value] + res[j]) % MODULO;
                        }
                        newCurVert.Add(transition.Value);
                    }
                }
                for (var j = 0; j != n; j++)
                {
                    res[j] = newRes[j];
                    newRes[j] = 0;
                }
                curVert = new HashSet<int>(newCurVert);
                newCurVert.Clear();
            }
            var resCount = curVert.Where(j => terminalPoints.Contains(j)).Aggregate(0L, (current1, j) => (current1 + res[j]) % MODULO);
            writer.WriteLine(resCount);
            writer.Close();
        }
    }
}