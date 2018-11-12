/*Во входном файле задано число n и затем перестановка чисел от 1 до n. Выведите в выход-
ной файл предыдущую и следующую перестановку чисел от 1 до n. Если какой либо из них не
существует, выведите вместо нее n нулей. 1 ≤ n ≤ 100 000.*/
﻿using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net.Configuration;
using System.Runtime.CompilerServices;
using System.Text;
 
namespace LabProblem.Properties
{
    public class Class13
    {
        static List<int> findNext(List<int> init)
        {
            for (var i = init.Count - 1; i != 0; i--)
            {
                if (init[i] > init[i - 1])
                {
                    var min = init[i] + 1;
                    var minIndex = -1;
                    for (var j = i; j != init.Count; j++)
                        if (init[j] > init[i - 1] && init[j] < min)
                        {
                            min = init[j];
                            minIndex = j;
                        }
                    var temp = init[minIndex];
                    init[minIndex] = init[i - 1];
                    init[i - 1] = temp;
                    var subArray = init.GetRange(i, init.Count - i);
                    var currIndex = 0;
                    for (var j = init.Count - 1; j != i - 1; j--)
                        init[j] = subArray[currIndex++];
                    return init;
                }
            }
            return null;
        }
 
        static List<int> findPrev(List<int> init)
        {
            for (var i = init.Count - 1; i != 0; i--)
            {
                if (init[i] < init[i - 1])
                {
                    var min = 0;
                    var minIndex = -1;
                    for (var j = i; j != init.Count; j++)
                        if (init[j] < init[i - 1] && init[j] > min)
                        {
                            min = init[j];
                            minIndex = j;
                        }
                    var temp = init[minIndex];
                    init[minIndex] = init[i - 1];
                    init[i - 1] = temp;
                    var subArray = init.GetRange(i, init.Count - i);
                    var currIndex = 0;
                    for (var j = init.Count - 1; j != i - 1; j--)
                        init[j] = subArray[currIndex++];
                    return init;
                }
            }
            return null;
        }
 
        public static void Main()
        {
            var reader = new StreamReader("nextperm.in");
            var writer = new StreamWriter("nextperm.out");
            var n = int.Parse(reader.ReadLine());
            var permutation = reader.ReadLine().Split().ToList().ConvertAll(int.Parse);
            var next = findNext(new List<int>(permutation));
            var previous = findPrev(permutation);
            if (next == null)
            {
                next = new List<int>();
                for (var i = 0; i != n; i++)
                    next.Add(0);
            }
            if (previous == null)
            {
                previous = new List<int>();
                for (var i = 0; i != n; i++)
                    previous.Add(0);
            }
            for (var i = 0; i != n; i++)
            {
                writer.Write(previous[i] + " ");
            }
            writer.WriteLine();
            for (var i = 0; i != n; i++)
            {
                writer.Write(next[i] + " ");
            }
            writer.Close();
        }
    }
}