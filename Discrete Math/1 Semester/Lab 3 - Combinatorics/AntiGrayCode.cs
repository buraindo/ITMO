/*Во входном файле задано число n. Выведите в выходной файл все троичные вектора длины n,
так чтобы в соседних отличались значения на всех n позициях. 1 ≤ n ≤ 10.
*/
﻿﻿﻿using System;
using System.Collections;
using System.Diagnostics;
using System.Collections.Generic;
using System.ComponentModel;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters;
using System.Text;
 
namespace LabProblem.Properties
{
    public class Class12
    {
        static string ToTernary (int num)
        {
            var s = string.Empty;
            do
            {
                s += num % 3;
                num /= 3;
            } while (num > 0);
            return new string(s.Reverse().ToArray());
        }
        public static void Main()
        {
            var reader = new StreamReader("antigray.in");
            var writer = new StreamWriter("antigray.out");
            var n = int.Parse(reader.ReadLine());
            for (var i = 0; i != (int) Math.Pow(3, n - 1); i++)
            {
                var vec = ToTernary(i).PadLeft(n, '0');
                writer.WriteLine(vec);
                for (var k = 0; k != 2; k++)
                {
                    var vecArr = vec.ToCharArray();
                    for (var t = 0; t != vec.Length; t++)
                    {
                        vecArr[t] = ((int.Parse(vecArr[t].ToString()) + 1) % 3).ToString()[0];
                    }
                    writer.WriteLine(new string(vecArr));
                    vec = new string(vecArr);
                }
            }
            writer.Close();
        }
    }
}