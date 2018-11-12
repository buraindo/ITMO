/*Дана текстовая строка. С ней можно выполнять следующие операции:
1. Заменить один символ строки на другой символ.
2. Удалить один произвольный символ.
3. Вставить произвольный символ в произвольное место строки.
Например, при помощи первой операции из строки "СОК" можно получить строку "СУК при
помощи второй операции - строку "ОК при помощи третьей операции - строку "СТОК.
Минимальное количество таких операций, при помощи которых можно из одной строки получить
другую, называется стоимостью редактирования или расстоянием Левенштейна.
Определите расстояние Левенштейна для двух данных строк.*/
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Configuration;
using System.Runtime.CompilerServices;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        static int MinimumOfThree(params int[] nums)
        {
            var min = int.MaxValue;
            foreach (var param in nums)
            {
                if (param < min)
                    min = param;
            }
            return min;
        }
 
        static int CalculateLevenshtein(string lhs, string rhs)
        {
            var Dynamics = new int[lhs.Length + 1, rhs.Length + 1];
            Dynamics[0, 0] = 0;
            for (var i = 1; i <= lhs.Length; i++)
            {
                Dynamics[i, 0] = i;
            }
            for (var i = 1; i <= rhs.Length; i++)
            {
                Dynamics[0, i] = i;
            }
            for (var i = 1; i <= lhs.Length; i++)
            {
                for (var j = 1; j <= rhs.Length; j++)
                {
                    var editCost = lhs[i - 1] == rhs[j - 1] ? 0 : 1;
                    Dynamics[i, j] = MinimumOfThree(Dynamics[i - 1, j] + 1, 
                        Dynamics[i, j - 1] + 1,
                        Dynamics[i - 1, j - 1] + editCost);
                }
            }
            return Dynamics[lhs.Length, rhs.Length];
        }
 
        static void Main()
        {
            var reader = new StreamReader("input.txt");
            var writer = new StreamWriter("output.txt");
            var lhs = reader.ReadLine();
            var rhs = reader.ReadLine();
            writer.WriteLine(CalculateLevenshtein(lhs, rhs));
            writer.Close();
        }
    }
}