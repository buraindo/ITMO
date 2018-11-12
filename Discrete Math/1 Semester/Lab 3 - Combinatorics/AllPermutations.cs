/*Во входном файле задано число n (1 ≤ n ≤ 8). Выведите в выходной файл в лексикографическом
порядке все перестановки чисел от 1 до n.
*/
﻿﻿using System;
using System.Diagnostics;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices;
using System.Runtime.Remoting.Messaging;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        public static List<string>[] Cash = new List<string>[9];
 
        static void Initialize()
        {
            Cash[1] = new List<string> {"1"};
            Cash[2] = new List<string> {"12", "21"};
        }
 
        public List<string> Generator(int n)
        {
            if (n <= Cash.Rank) return Cash[n];
            var list = new List<string>();
            var prevGen = Generator(n - 1);
            for (var i = 0; i != n; i++)
            {
                for (var k = 0; k != prevGen.Count; k++)
                {
                    var l = prevGen[k];
                    l = l.Insert(i, n.ToString());
                    list.Add(l);
                }
            }
            list.Sort();
            Cash[n] = new List<string>(list);
            return list;
        }
 
        public static void Main()
        {
            Initialize();
            var reader = new StreamReader("permutations.in");
            var writer = new StreamWriter("permutations.out");
            var n = int.Parse(reader.ReadLine());
            var computer = new Class1();
            var permutations = computer.Generator(n);
            foreach (var permutation in permutations)
            {
                foreach (var elem in permutation)
                {
                    writer.Write(elem + " ");
                }
                writer.Write("\n");
            }
            writer.Close();
        }
    }
}