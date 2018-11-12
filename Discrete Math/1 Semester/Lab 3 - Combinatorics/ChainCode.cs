/*Во входном файле задано число n. Выведите в выходной файл все двоичные вектора длины n,
в порядке какого-нибудь цепного кода. 1 ≤ n ≤ 15.
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
            var reader = new StreamReader("chaincode.in");
            var writer = new StreamWriter("chaincode.out");
            var n = int.Parse(reader.ReadLine());
            var dic = new Dictionary<string,int>();
            var vectors = new List<string>();
            for (var i = 0; i != (int) Math.Pow(2, n); i++)
            {
                if (i == 0)
                {
                    vectors.Add(new string('0', n));
                    writer.WriteLine(vectors[0]);
                    dic.Add(vectors[0],1);
                }
                else
                {
                    var str = vectors[i - 1].Substring(1, vectors[i - 1].Length - 1);
                    if (!dic.ContainsKey(str+'1'))
                        vectors.Add(str + '1');
                    else vectors.Add(str + '0');
                    dic.Add(vectors[i],1);
                    writer.WriteLine(vectors[i]);
                     
                }
            }
            writer.Close();
        }
    }
}