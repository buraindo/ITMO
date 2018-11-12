/*Во входном файле заданы числа n и k. Выведите в выходной файл k-ю в лексикографическом
порядке правильную скобочную последовательность среди всех правильных скобочных последова-
тельностей с n открывающимися скобками, упорядоченных в лексикографическом порядке, «(» <
«)». Последовательности занумерованы, начиная с 0. 1 ≤ n ≤ 20. Искомая последовательность
существует*/
using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Globalization;
using System.IO;
using System.Net.Configuration;
using System.Runtime.CompilerServices;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        public static long[,] Dynamics;
 
        public static void Initialize(int l)
        {
            for (var i = 0; i != l + 1; i++)
            for (var k = 0; k != l + 1; k++)
                Dynamics[i, k] = 0;
            Dynamics[0, 0] = 1;
            for (var i = 1; i != l + 1; i++)
            {
                Dynamics[0, i] = 0;
            }
            for (var i = 1; i != l + 1; i++)
            {
                for (var k = 0; k != l; k++)
                {
                    if (k == 0)
                        Dynamics[i, k] = Dynamics[i - 1, k + 1];
                    else Dynamics[i, k] = Dynamics[i - 1, k + 1] + Dynamics[i - 1, k - 1];
                }
            }
        }
        static string Generator(int s, long n)
        {
            var result = String.Empty;
            var balance = 0;
            for (var i = 0; i != s * 2; i++)
            {
                if (Dynamics[s * 2 - i - 1, balance + 1] >= n)
                {
                    result += '(';
                    balance++;
                }
                else
                {
                    n -= Dynamics[s * 2 - i - 1, balance + 1];
                    result += ')';
                    balance--;
                }
            }
            return result;
        }
 
        public static void Main()
        {
            var reader = new StreamReader("num2brackets.in");
            var writer = new StreamWriter("num2brackets.out");
            var init1 = reader.ReadLine().Split();
            var s = int.Parse(init1[0]);
            var n = long.Parse(init1[1]);
            Dynamics = new long[s * 2 + 1, s * 2 + 1];
            Initialize(s * 2);
            var permutations = Generator(s, n + 1);
            writer.Write(permutations);
            writer.Close();
        }
    }
}