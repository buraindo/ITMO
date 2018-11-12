/*Во входном файле задано число n и затем мультиперестановка, составленная из чисел от 1 до
n. Выведите в выходной файл следующую в лексикографическом порядке мультиперестановку того
же мультимножества. Если искомой перестановки не существует, выведите n нулей. 1 ≤ n ≤ 100 000*/
using System;
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
            var reader = new StreamReader("nextmultiperm.in");
            var writer = new StreamWriter("nextmultiperm.out");
            var n = int.Parse(reader.ReadLine());
            var permutation = reader.ReadLine().Split().ToList().ConvertAll(int.Parse);
            var next = findNext(permutation);
            if (next == null)
            {
                next = new List<int>();
                for (var i = 0; i != n; i++)
                    next.Add(0);
            }
            for (var i = 0; i != n; i++)
            {
                writer.Write(next[i] + " ");
            }
            writer.Close();
        }
    }
}