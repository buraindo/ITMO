/*Во входном файле задана правильная скобочная последовательность. Выведите в выходной ее но-
мер в лексикографическом порядке среди всех правильных скобочных последовательностей с таким
же количеством открывающихся скобок, «(» < «)». Последовательности занумерованы, начиная с
0. Количество открывающихся скобок в последовательности — от 1 до 20.
*/
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
		
        static long Generator(string s)
        {
            long result = 0L;
            var balance = 0;
            for (var i = 0; i != s.Length; i++)
            {
                if (s[i] == '(') balance++;
                else if (s[i] == ')')
                {
                    result += Dynamics[s.Length - i - 1, balance + 1];
                    balance--;
                }
            }
            return result;
        }
 
        public static void Main()
        {
            var reader = new StreamReader("brackets2num.in");
            var writer = new StreamWriter("brackets2num.out");
            var init1 = reader.ReadLine();
            Dynamics = new long[init1.Length + 1, init1.Length + 1];
            Initialize(init1.Length);
            var permutations = Generator(init1);
            writer.Write(permutations);
            writer.Close();
        }
    }
}