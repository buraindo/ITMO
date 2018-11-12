/*Во входном файле задано число n. Выведите в выходной файл в порядке произвольного кода
Грея все двоичные вектора длины n. 1 ≤ n ≤ 16.
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
            var reader = new StreamReader("gray.in");
            var writer = new StreamWriter("gray.out");
            var n = int.Parse(reader.ReadLine());
            var grayCode = new List<string>();
            for (var i = 0; i != n; i++)
            {
                if (i == 0)
                {
                    grayCode.Add("0");
                    grayCode.Add("1");
                }
                else
                {
                    for (var k = grayCode.Count - 1; k != -1; k--)
                    {
                        grayCode.Add(grayCode[k]);
                    }
                    for (var t = 0; t != grayCode.Count / 2; t++)
                    {
                        grayCode[t] = grayCode[t].Insert(0, "0");
                    }
                    for (var t = grayCode.Count / 2; t != grayCode.Count; t++)
                    {
                        grayCode[t] = grayCode[t].Insert(0, "1");
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