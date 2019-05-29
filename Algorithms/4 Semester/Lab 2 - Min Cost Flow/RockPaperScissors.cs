using System;
using System.IO;
using System.Linq;

namespace LabProblem {
    public static class RockPaperScissors {
        public static void Main() {
            using (var reader = new StreamReader("rps2.in"))
            using (var writer = new StreamWriter("rps2.out")) {
                var input = reader.ReadLine().Split().Select(int.Parse).ToArray();
                var r1 = input[0];
                var s1 = input[1];
                var p1 = input[2];
                input = reader.ReadLine().Split().Select(int.Parse).ToArray();
                var r2 = input[0];
                var s2 = input[1];
                var p2 = input[2];
                writer.WriteLine(Math.Max(p1 - p2 - s2, Math.Max(r1 - r2 - p2, Math.Max(s1 - s2 - r2, 0))));
            }
        }
    }
}