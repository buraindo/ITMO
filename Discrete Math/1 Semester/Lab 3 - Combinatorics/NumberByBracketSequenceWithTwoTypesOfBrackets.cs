/*Во входном файле задана правильная скобочная последовательность с двумя типами скобок.
Выведите в выходной ее номер в лексикографическом порядке среди всех правильных скобочных
последовательностей с таким же количеством открывающихся скобок, «(» < «)» < «[» < «]».
Последовательности занумерованы, начиная с 0. Количество открывающихся скобок в последова-
тельности — от 1 до 20*/
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
        static long Generator(string str)
        {
            var result = 0L;
            var balance = 0;
            var brackets = new Stack<char>();
            var s = str.Length;
            try
            {
                for (var i = 0; i != s; i++)
                {
                    var dynamicDiff = 0L;
                    if (str[i] == '(')
                    {
                        balance++;
                        brackets.Push('(');
                        continue;
                    }
                    dynamicDiff += Dynamics[s - i - 1, balance + 1] * (1 << ((s - i - 2 - balance) / 2));
                    if (str[i] == ')')
                    {
                        result += Dynamics[s - i - 1, balance + 1] * (1 << ((s - i - 2 - balance) / 2));
                        balance--;
                        brackets.Pop();
                        continue;
                    }
                    if (str[i] == '[')
                    {
                        if (i > 0 && brackets.Count > 0 && brackets.Peek() == '(')
                        {
                            result += Dynamics[s - i - 1, balance - 1] * (1 << ((s - i - balance) / 2));
                        }
                        result += Dynamics[s - i - 1, balance + 1] * (1 << ((s - i - 2 - balance) / 2));
                        balance++;
                        brackets.Push('[');
                        continue;
                    }
                    dynamicDiff += Dynamics[s - i - 1, balance + 1] * (1 << ((s - i - 2 - balance) / 2));
                    result += 2 * Dynamics[s - i - 1, balance + 1] * (1 << ((s - i - 2 - balance) / 2));
                    balance--;
                    brackets.Pop();
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
            var reader = new StreamReader("brackets2num2.in");
            var writer = new StreamWriter("brackets2num2.out");
            var init1 = reader.ReadLine();
            Dynamics = new long[init1.Length + 1, init1.Length + 1];
            Initialize(init1.Length);
            var permutations = Generator(init1);
            writer.Write(permutations);
            writer.Close();
        }
    }
}