/*Во входном файле заданы числа n и k. Выведите в выходной файл все сочетания по k из чисел
от 1 до n в лексикографическом порядке. 1 ≤ k ≤ n ≤ 16.
*/
﻿﻿using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
 
namespace LabProblem.Properties
{
    public class Class13
    {
        public long Combinations(int num, int Base)
        {
            if (Base == 0 || Base == num) return 1;
            if (Base == num-1 || Base == 1) return num;
            return Combinations(num - 1, Base - 1) + Combinations(num - 1, Base);
        }
 
        public static void Main()
        {
            var reader = new StreamReader("choose.in");
            var computer = new Class13();
            var writer = new StreamWriter("choose.out");
            var s = reader.ReadLine().Split();
            var n = int.Parse(s[0]);
            var k = int.Parse(s[1]);
            var combinations = new List<List<int>>();
            var f = new List<int>();
            for (var i = 1; i != k + 1; i++)
                f.Add(i);
            combinations.Add(f);
            var comboNum = computer.Combinations(n,k);
            var localMaximums = new List<int>();
            for (var i = n - k + 1; i != n + 1; i++)
                localMaximums.Add(i);
            for (var i = 1; i != comboNum; i++)
            {
                var list = new List<int>(combinations[i - 1]);
                for (var t = list.Count - 1; t != -1; t--)
                {
                    if (list[t] >= localMaximums[t]) continue;
                    var index = t;
                    list[index]++;
                    for (var ind = index + 1; ind != list.Count; ind++)
                        list[ind] = list[ind - 1] + 1;
                    combinations.Add(list);
                    break;
                }
            }
            foreach (var combination in combinations)
            {
                foreach (var element in combination)
                {
                    writer.Write(element + " ");
                }
                writer.WriteLine();
            }
            writer.Close();
        }
    }
}