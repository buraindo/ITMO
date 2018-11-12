/*Во входном файле задано число n. Выведите в выходной файл в лексикографическом порядке
все двоичные вектора длины n. 1 ≤ n ≤ 16
*/
﻿﻿﻿using System;
using System.Collections;
using System.Diagnostics;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters;
 
namespace LabProblem.Properties
{
    public class Class12
    {
        public static void Main()
        {
            var reader = new StreamReader("allvectors.in");
            var writer = new StreamWriter("allvectors.out");
            var n = int.Parse(reader.ReadLine());
            for (var i = 0; i != Math.Pow(2, n); i++)
            {
                writer.WriteLine(Convert.ToString(i, 2).PadLeft(n, '0'));
            }
            writer.Close();
        }
    }
}