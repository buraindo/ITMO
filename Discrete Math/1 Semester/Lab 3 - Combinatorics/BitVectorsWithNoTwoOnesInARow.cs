/*Во входном файле задано число n (1 ≤ n ≤ 16). В первой строке выходного файла выведите
количество двоичных векторов длины n в которых нет двух единиц подряд. В следующих строках
выведите сами эти вектора в лексикографическом порядке по одному в строке.
*/
﻿﻿﻿using System;
using System.Diagnostics;
using System.Collections.Generic;
using System.IO;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        public static void Main()
        {
            var reader = new StreamReader("vectors.in");
            var writer = new StreamWriter("vectors.out");
            var vectors = new List<string>();
            var neededVectors = new List<string>();
            var n = int.Parse(reader.ReadLine());
            for (var i = 0; i != (int) Math.Pow(2, n); i++)
            {
                vectors.Add(Convert.ToString(i, 2).PadLeft(n, '0'));
            }
            var hasTwoInARow = false;
            foreach (var vector in vectors)
            {
                for (var i = 0; i != n-1; i++)
                {
                    if (vector[i] == vector[i + 1] && vector[i] == '1')
                    {
                        hasTwoInARow = true;
                        break;
                    }
                }
                if (!hasTwoInARow)
                neededVectors.Add(vector);
                else hasTwoInARow = false;
            }
            writer.WriteLine(neededVectors.Count);
            foreach (var nv in neededVectors)
            {
                writer.WriteLine(nv);
            }
            writer.Close();
        }
    }
}