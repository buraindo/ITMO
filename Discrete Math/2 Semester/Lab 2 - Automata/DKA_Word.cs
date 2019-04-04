using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
 
namespace LabProblem
{
     
    public class State
    {
        public bool Terminal;
        public int DevilIndex;
        public Dictionary<char, int> Transitions;
 
        public void Add(char c, int b)
        {
            if (!Transitions.ContainsKey(c))
                Transitions.Add(c, b);
        }
 
        public int Next(char c)
        {
            return Transitions.ContainsKey(c) ? Transitions[c] : DevilIndex;
        }
 
        public State(int devilIndex)
        {
            Transitions = new Dictionary<char, int>();
            Terminal = false;
            DevilIndex = devilIndex;
        }
    }
     
    public class DKA_Word
    {
        public static void Main()
        {
            var reader = new StreamReader("problem1.in");
            var writer = new StreamWriter("problem1.out");
            var word = reader.ReadLine();
            var input = reader.ReadLine().Split(' ').ToList().ConvertAll(int.Parse).ToArray();
            var n = input[0];
            var m = input[1];
            var k = input[2];
            var states = new State[n + 1];
            for (var i = 0; i < n + 1; i++)
            {
                states[i] = new State(n);
            }
            var terminals = reader.ReadLine().Split(' ');
            for (var i = 0; i < k; i++)
            {
                states[Convert.ToInt32(terminals[i]) - 1].Terminal = true;
            }
            for (var i = 0; i < m; i++)
            {
                var info = reader.ReadLine().Split(' ');
                var from = Convert.ToInt32(info[0]);
                var to = Convert.ToInt32(info[1]);
                var condition = Convert.ToChar(info[2]);
                states[from - 1].Add(condition, to - 1);
            }
            reader.Close();
            var index = word.Aggregate(0, (current, letter) => states[current].Next(letter));
            writer.WriteLine(states[index].Terminal ? "Accepts" : "Rejects");
            writer.Close();
        }
    }
}