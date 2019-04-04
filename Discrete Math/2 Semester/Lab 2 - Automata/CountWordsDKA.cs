using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace LabProblem
{
    public class CountWordsDKA
    {
        private const int MODULO = 1000000007;

        public enum States
        {
            Start,
            End
        }

        public static void Main()
        {
            var reader = new StreamReader("problem3.in");
            var writer = new StreamWriter("problem3.out");
            var input = reader.ReadLine().Split(' ');
            var n = Convert.ToInt32(input[0]);
            var m = Convert.ToInt32(input[1]);
            var k = Convert.ToInt32(input[2]);
            var transitions = new List<int>[n];
            var transitionsReverse = new List<int>[n];
            var transitionAmounts = new int[n];
            var dynamics = new int[n];
            var terminalPoints = new int[k];
            var usedFromStart = new bool[n];
            var usedFromEnd = new bool[n];
            var state = States.Start;
            for (var i = 0; i != n; i++)
            {
                transitionsReverse[i] = new List<int>();
                transitions[i] = new List<int>();
            }
            input = reader.ReadLine().Split(' ');
            for (var i = 0; i != k; i++)
            {
                var a = Convert.ToInt32(input[i]) - 1;
                terminalPoints[i] = a;
            }
            for (var i = 0; i != m; i++)
            {
                var localInput = reader.ReadLine().Split(' ');
                var a = Convert.ToInt32(localInput[0]) - 1;
                var b = Convert.ToInt32(localInput[1]) - 1;
                transitions[a].Add(b);
                transitionsReverse[b].Add(a);
                transitionAmounts[b]++;
            }
            var path = new Queue<int>();
            path.Enqueue(0);
            for (var i = 0; i < 2; i++)
            {
                while (path.Count > 0)
                {
                    var element = path.Dequeue();
                    switch (state)
                    {
                        case States.Start:
                            if (usedFromStart[element]) continue;
                            usedFromStart[element] = true;
                            foreach (var point in transitions[element])
                            {
                                path.Enqueue(point);
                            }
                            break;
                        case States.End:
                            if (usedFromEnd[element]) continue;
                            usedFromEnd[element] = true;
                            foreach (var point in transitionsReverse[element])
                            {
                                path.Enqueue(point);
                            }
                            break;
                        default:
                            break;
                    }
                }
                if (state == States.Start)
                    foreach (var point in terminalPoints)
                    {
                        path.Enqueue(point);
                    }
                state = States.End;
            }
            for (var i = 0; i < n; i++)
            {
                if (usedFromStart[i] && usedFromEnd[i]) continue;
                foreach (var point in transitions[i])
                    transitionAmounts[point]--;
                transitions[i].Clear();
            }
            if (!usedFromEnd[0])
            {
                writer.WriteLine(0);
                writer.Close();
                return;
            }
            if (transitionAmounts[0] == 0)
            {
                path.Enqueue(0);
                transitionAmounts[0]++;
            }
            else
            {
                writer.WriteLine(-1);
                writer.Close();
                return;
            }
            while (path.Count > 0)
            {
                var current = path.Dequeue();

                if (--transitionAmounts[current] != 0) continue;
                var sum = 0;
                for (var i = 0; i != transitionsReverse[current].Count; ++i)
                {
                    if (!usedFromStart[transitionsReverse[current][i]] ||
                        !usedFromEnd[transitionsReverse[current][i]]) continue;
                    if (dynamics[transitionsReverse[current][i]] == 0)
                        sum = (sum + 1) % MODULO;
                    else
                        sum = (sum + dynamics[transitionsReverse[current][i]]) % MODULO;
                }
                dynamics[current] = sum;

                foreach (var transition in transitions[current])
                {
                    path.Enqueue(transition);
                }
            }
            for (var i = 0; i < n; i++) {
                if (!usedFromStart[i] || !usedFromEnd[i] || transitionAmounts[i] == 0) continue;
                writer.WriteLine(-1);
                writer.Close();
                return;
            }
            dynamics[0] = 1;
            var answer = terminalPoints.Aggregate(0L, (current, point) => (current + dynamics[point]) % MODULO);
            writer.WriteLine(answer);
            writer.Close();
        }
    }
}