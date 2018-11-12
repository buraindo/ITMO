/*Во входном файле заданы числа n и k. Выведите в выходной файл k-ю в лексикографическом
порядке правильную скобочную последовательность среди всех правильных скобочных последова-
тельностей с двумя типами скобок с n открывающимися скобками, упорядоченных в лексикографи-
ческом порядке, «(» < «)» < «[» < «]». Последовательности занумерованы, начиная с 0. 1 ≤ n ≤ 20.
Искомая последовательность существует.*/
using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Globalization;
using System.IO;
using System.Net.Configuration;
using System.Runtime.CompilerServices;
 
namespace LabProblem.Properties
{
    public class Class13
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
            var result = string.Empty;
            var brackets = new char[s * 2];
            var bracket = 0;
            var balance = 0;
            try
            {
                for (var i = 0; i != s * 2; i++)
                {
                    long dynamicDiff;
                    if (balance < s)
                        dynamicDiff = Dynamics[s * 2 - i - 1, balance + 1] * (1 << ((s * 2 - i - 2 - balance) / 2));
                    else dynamicDiff = 0L;
                    if (dynamicDiff >= n)
                    {
                        result += '(';
                        brackets[bracket++] = '(';
                        balance++;
                        continue;
                    }
                    n -= dynamicDiff;
                    if (bracket > 0 && brackets[bracket - 1] == '(' && balance > 0)
                        dynamicDiff = Dynamics[s * 2 - i - 1, balance - 1] * (1 << ((s * 2 - i - balance) / 2));
                    else dynamicDiff = 0L;
                    if (dynamicDiff >= n)
                    {
                        result += ')';
                        bracket--;
                        balance--;
                        continue;
                    }
                    n -= dynamicDiff;
                    if (balance < s)
                        dynamicDiff = Dynamics[s * 2 - i - 1, balance + 1] * (1 << ((s * 2 - i - 2 - balance) / 2));
                    else dynamicDiff = 0L;
                    if (dynamicDiff >= n)
                    {
                        result += '[';
                        balance++;
                        brackets[bracket++] = '[';
                        continue;
                    }
                    n -= dynamicDiff;
                    result += ']';
                    balance--;
                    bracket--;
                }
            }
            catch (IndexOutOfRangeException ex)
            {
                Console.WriteLine(ex);
            }
            return result;
        }
 
        public static void Main()
        {
            var reader = new StreamReader("num2brackets2.in");
            var writer = new StreamWriter("num2brackets2.out");
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