/*Во входном файле заданы числа n, k и затем сочетание, состоящее из k чисел от 1 до n.
(1 ≤ k ≤ n ≤ 10000)
Выведите в выходной файл следующее сочетание в лексикографическом порядке из n чисел по
k.
Если его не существует, выведите -1*/
using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net.Configuration;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
using System.Text;
 
namespace LabProblem.Properties
{
    public class Class13
    {
        static List<int> findNextChoose(List<int> init, int n, int k)
        {
            init.Add(n + 1);
            var certainIndex = -1;
            for (var i = 0; i != init.Count - 1; i++)
            {
                if (init[i + 1] - init[i] >= 2)
                {
                    certainIndex = i;
                }
            }
            if (certainIndex == -1)
                return null;
            init[certainIndex]++;
            for (var i = certainIndex + 1; i != init.Count; i++)
            {
                init[i] = init[i - 1] + 1;
            }
            var result = init.GetRange(0, k);
            return result;
        }
        public static void Main()
        {
            var reader = new StreamReader("nextchoose.in");
            var writer = new StreamWriter("nextchoose.out");
            var init = reader.ReadLine().Split();
            var n = int.Parse(init[0]);
            var k = int.Parse(init[1]);
            var permutation = reader.ReadLine().Split().ToList().ConvertAll(int.Parse);
            var next = findNextChoose(permutation, n, k);
            if (next == null)
            {
                writer.Write(-1);
                writer.Close();
                return;
            }
            for (var i = 0; i != k; i++)
            {
                writer.Write(next[i] + " ");
            }
            writer.Close();
        }
    }
}