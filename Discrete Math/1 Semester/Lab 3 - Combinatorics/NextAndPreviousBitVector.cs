/*Во входном файле задан двоичный вектор. Выведите в выходной файл предыдущий и следующий
двоичный вектор в лексикографическом порядке. Если какого-либо из них не существует, выведите
вместо него «-». Длина вектора во входном файле — от 1 до 200000*/
using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Globalization;
using System.IO;
using System.Net.Configuration;
using System.Runtime.CompilerServices;
using System.Text;
 
namespace LabProblem.Properties
{
    public class Class13
    {
        static string findNext(StringBuilder init)
        {
            for (var i = init.Length - 1; i != -1; i--)
            {
                if (init[i] == '1') init.Replace('1', '0', i, 1);
                else if (init[i] == '0')
                {
                    init.Replace('0', '1', i, 1);
                    return init.ToString();
                }
            }
            return init.ToString();
        }
 
        static string findPrev(StringBuilder init)
        {
            for (var i = init.Length - 1; i != -1; i--)
            {
                if (init[i] == '0') init.Replace('0', '1', i, 1);
                else if (init[i] == '1')
                {
                    init.Replace('1', '0', i, 1);
                    return init.ToString();
                }
            }
            return init.ToString();
        }
 
        public static void Main()
        {
            var reader = new StreamReader("nextvector.in");
            var writer = new StreamWriter("nextvector.out");
            var init1 = reader.ReadLine();
            var next = string.Empty;
            var previous = string.Empty;
            if (!init1.Contains("1"))
                previous = "-";
            else previous = findPrev(new StringBuilder(init1));
            if (!init1.Contains("0"))
                next = "-";
            else next = findNext(new StringBuilder(init1));
            writer.WriteLine(previous);
            writer.Write(next);
            writer.Close();
        }
    }
}