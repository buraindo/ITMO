using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Linq;
using System.Runtime.CompilerServices;

namespace LabProblem
{
    public class Equivalence
    {
        public static int[,] Aut1;
        public static int[,] Aut2;
        public static bool[] IsTerminal1;
        public static bool[] IsTerminal2;
        public static bool[] Used1;
        public static bool[] Used2;
        public static char[] Alphabet;
        public static int N;

        static bool BfsEquivalenceCheck()
        {
            var Q = new Queue<KeyValuePair<int, int>>();
            Q.Enqueue(new KeyValuePair<int, int>(1, 1));
            while (Q.Count > 0)
            {
                var entry = Q.Dequeue();
                var u = entry.Key;
                var v = entry.Value;
                if (IsTerminal1[u] != IsTerminal2[v])
                    return false;
                Used1[u] = u != 0;
                Used2[v] = v != 0;
                if (u == 0 && v == 0) continue;
                foreach (var ch in Alphabet)
                {
                    var c = ch - 'a';
                    if (!Used1[Aut1[u, c]] || !Used2[Aut2[v, c]])
                        Q.Enqueue(new KeyValuePair<int, int>(Aut1[u, c], Aut2[v, c]));
                }
            }
            return true;
        }

        public static void Main()
        {
            var reader = new StreamReader("equivalence.in");
            var writer = new StreamWriter("equivalence.out");
            var input = reader.ReadLine().Split(' ');
            var n = Convert.ToInt32(input[0]);
            var m = Convert.ToInt32(input[1]);
            var k = Convert.ToInt32(input[2]);
            Aut1 = new int[n + 2, 27];
            IsTerminal1 = new bool[n + 1];
            Used1 = new bool[n + 1];
            Alphabet = new char[26];

            for (var i = 'a'; i <= 'z'; i++)
            {
                Alphabet[i - 'a'] = i;
            }
            var terminals1 = reader.ReadLine().Split(' ');

            for (var i = 0; i < k; i++)
            {
                IsTerminal1[Convert.ToInt32(terminals1[i])] = true;
            }
            for (var i = 0; i < m; i++)
            {
                var info = reader.ReadLine().Split(' ');
                var fr = Convert.ToInt32(info[0]);
                var to = Convert.ToInt32(info[1]);
                var condition = Convert.ToChar(info[2]) - 'a';
                Aut1[fr, condition] = to;
            }
            input = reader.ReadLine().Split(' ');
            n = Convert.ToInt32(input[0]);
            m = Convert.ToInt32(input[1]);
            k = Convert.ToInt32(input[2]);
            IsTerminal2 = new bool[n + 1];
            Used2 = new bool[n + 1];
            Aut2 = new int[n + 2, 27];
            var terminals2 = reader.ReadLine().Split(' ');

            for (var i = 0; i < k; i++)
            {
                IsTerminal2[Convert.ToInt32(terminals2[i])] = true;
            }
            for (var i = 0; i < m; i++)
            {
                var info = reader.ReadLine().Split(' ');
                var fr = Convert.ToInt32(info[0]);
                var to = Convert.ToInt32(info[1]);
                var condition = Convert.ToChar(info[2]) - 'a';
                Aut2[fr, condition] = to;
            }

            var check = BfsEquivalenceCheck();
            writer.WriteLine(check ? "YES" : "NO");
            writer.Close();
        }
    }
}