/*Во входном файле заданы числа n и k. Выведите в выходной файл все k-ичные вектора длины
n, так чтобы у двух подряд идущих векторов, значения на всех кроме одной позиции совпадали, а
значения на оставшейся позиции отличались ровно на 1 (n ≥ 2, 2 ≤ k ≤ 9, 1 ≤ k
n ≤ 100000).
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
        public static void Main()
        {
            var reader = new StreamReader("telemetry.in");
            var writer = new StreamWriter("telemetry.out");
            var s = reader.ReadLine().Split();
            var n = int.Parse(s[0]);
            var m = int.Parse(s[1]);
            var grayCode = new List<string>();
            for (var i = 0; i != n; i++)
            {
                if (i == 0)
                {
                    for (var r = 0; r != m; r++)
                        grayCode.Add(r.ToString());
                }
                else
                {
                    for (var x = 0; x != m - 1; x++)
                    {
                        var size = grayCode.Count - 1;
                        for (var j = size; j != size - (int) Math.Pow(m, i); j--)
                            grayCode.Add(grayCode[j]);
                    }
                    for (var q = 0; q != m; q++)
                    {
                        var left = q * (int) Math.Pow(m, i);
                        var right = left + (int) Math.Pow(m, i);
                        for (var l = left; l != right; l++)
                            grayCode[l] = grayCode[l].Insert(0, q.ToString());
                    }
                }
            }
            foreach (var sb in grayCode)
            {
                writer.WriteLine(sb);
            }
            writer.Close();
        }
    }
}