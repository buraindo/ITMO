/*Пусть a1, a2, . . . , an — числовая последовательность. Длина последовательности — это количество
элементов этой последовательности. Последовательность a_i1, a_i2, . . . , a_ik
называется подпоследовательностью последовательности a, если 1 <= i1 < i2 < . . . < ik <= n. Последовательность a называется
возрастающей, если a1 < a2 < . . . < an.
Вам дана последовательность, содержащая n целых чисел. Найдите ее самую длинную возрастающую подпоследовательность.*/
using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net.Configuration;
using System.Runtime.CompilerServices;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        static void Main()
        {
            var n = int.Parse(Console.ReadLine());
            var init = Console.ReadLine().Split().ToList().ConvertAll(int.Parse);
            var path = new List<int>();
            var lengths = new List<int>();
            var indexes = new List<int>();
            for (var i = 0; i != init.Count; i++)
            {
                lengths.Add(1);
                indexes.Add(-1);
            }
            var last = 0;
            for (var i = 0; i != init.Count; i++)
            {
                for (var k = 0; k != i; k++)
                {
                    if (init[k] < init[i] && lengths[k] >= lengths[i])
                    {
                        lengths[i] = lengths[k] + 1;
                        indexes[i] = k;
                    }
                }
            }
            var length = lengths[0];
            for (var i = 0; i != init.Count; i++)
            {
                if (lengths[i] > length)
                {
                    length = lengths[i];
                    last = i;
                }
            }
            while (last != -1)
            {
                path.Insert(0, init[last]);
                last = indexes[last];
            }
            Console.WriteLine(length);
            foreach (var e in path)
                Console.Write(e + " ");
        }
    }
}