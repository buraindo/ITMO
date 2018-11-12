/*Разбиения числа n на слагаемые — это набор целых положительных чисел, сумма которых рав-
на n. При этом разбиения, отличающиеся лишь порядком слагаемых, считаются одинаковыми, по-
этому можно считать, что слагаемые в разбиении упорядочены по неубыванию.
Например, существует 7 разбиений числа 5 на слагаемые:
5 = 1 + 1 + 1 + 1 + 1
5 = 1 + 1 + 1 + 2
5 = 1 + 1 + 3
5 = 1 + 2 + 2
5 = 1 + 4
5 = 2 + 3
5 = 5
В приведенном примере разбиения упорядочены лексикографически — сначала по первому сла-
гаемому в разбиении, затем по второму, и так далее. В этой задаче вам потребуется по заданному
разбиению на слагаемые найти следующее в лексикографическом порядке разбиение.
Формат входного файла
Входной файл содержит одну строку — разбиение числа n на слагаемые (1 ≤ n ≤ 100 000).
Слагаемые в разбиении следуют в неубывающем порядке.
Формат выходного файла
Выведите в выходной файл одну строку — разбиение числа n на слагаемые, следующее в лекси-
кографическом порядке после приведенного во входном файле. Если во входном файле приведено
последнее разбиение числа n на слагаемые, выведите «No solution».*/
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
        static void nextPartition(List<int> partition)
        {
            partition[partition.Count - 1]--;
            partition[partition.Count - 2]++;
            if (partition[partition.Count - 2] > partition[partition.Count - 1])
            {
                partition[partition.Count - 2] += partition[partition.Count - 1];
                partition.RemoveAt(partition.Count - 1);
            }
            else
            {
                while (partition[partition.Count - 2] * 2 <= partition[partition.Count - 1])
                {
                    partition.Add(partition[partition.Count - 1] - partition[partition.Count - 2]);
                    partition[partition.Count - 2] = partition[partition.Count - 3];
                }
            }
        }
 
        public static void Main()
        {
            var reader = new StreamReader("nextpartition.in");
            var writer = new StreamWriter("nextpartition.out");
            var n = reader.ReadLine();
            var next = new List<int>();
            var num = int.Parse(n.Split('=')[0]);
            var len = num.ToString().Length;
            n = n.Remove(0, len + 1);
            next = n.Split('+').ToList().ConvertAll(int.Parse);
            if (num.ToString().Equals(next[0].ToString()))
            {
                writer.Write("No solution");
                writer.Close();
                return;
            }
            nextPartition(next);
            writer.Write(num + "=");
            for (var i = 0; i != next.Count - 1; i++)
            {
                writer.Write(next[i] + "+");
            }
            writer.Write(next[next.Count - 1]);
            writer.Close();
        }
    }
}