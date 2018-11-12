/*Рассмотрим множество первых n натуральных чисел:Nn = {1, 2, . . . , n}. Разбиением на мно-
жества называется представление этого множества, как объединения одного или более, попарно
непересекающихся подмножеств множеств. Например для n = 5 существуют следующие разбиения:
{1, 2, 3, 4, 5} = {1, 2, 3} ∪ {4, 5}
{1, 2, 3, 4, 5} = {1, 3, 5} ∪ {2, 4}
{1, 2, 3, 4, 5} = {1, 2, 3, 4, 5}
{1, 2, 3, 4, 5} = {1} ∪ {2} ∪ {3} ∪ {4} ∪ {5}
Всего существует 52 разбиения множества N5. Заметьте, что мы не различаем разбиения на множе-
ства, которые отличаются только порядком подмножеств.
Упорядочим все разбиения на множества Nn лексикографически. Для этого во-первых в каждом
разбиении упорядочим множества лексикографически.
Дано разбиение Nn„ ваша задача найти следующее разбиение на множества в лексикографиче-
ском порядке.*/
using System;
using System.Collections.Generic;
using System.Diagnostics;
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
        static int findMinMoreThanCertain(List<int> elems, int certain)
        {
            var min = int.MaxValue;
            foreach (var elem in elems)
            {
                if (elem < min && elem > certain)
                    min = elem;
            }
            return min;
        }
 
        static void nextSetPartition(List<List<int>> setPartition, int n, StreamWriter writer)
        {
            var visitedElements = new List<int>();
            var canChange = false;
            for (var i = setPartition.Count - 1; i != -1; i--)
            {
                var vCount = visitedElements.Count;
                var pCount = setPartition[i].Count;
                var lastElement = setPartition[i][setPartition[i].Count - 1];
                var currentMin = findMinMoreThanCertain(visitedElements, lastElement);
                if (visitedElements.Count != 0 && currentMin != int.MaxValue)
                {
                    setPartition[i].Add(currentMin);
                    visitedElements.Remove(currentMin);
                    break;
                }
                for (var k = setPartition[i].Count - 1; k != 0; k--)
                {
                    var localElem = setPartition[i][k];
                    var t = SearchFwd(visitedElements.Count);
                    var localMin = findMinMoreThanCertain(visitedElements, localElem);
                    if (visitedElements.Count != 0 && localMin != int.MaxValue)
                    {
                        visitedElements.Add(localElem);
                        visitedElements.Remove(localMin);
                        setPartition[i][k] = localMin;
                        canChange = true;
                        break;
                    }
                    visitedElements.Add(setPartition[i][k]);
                    setPartition[i].RemoveAt(k);
                }
                if (canChange)
                    break;
                if (setPartition[i].Count == 1)
                {
                    visitedElements.Add(setPartition[i][0]);
                    setPartition.RemoveAt(i);
                }
            }
            visitedElements.Sort();
            foreach (var vElem in visitedElements)
            {
                var currentList = new List<int>();
                setPartition.Add(currentList);
                currentList.Add(vElem);
            }
        }
 
        static void executeTest(int s, int n, List<List<int>> currentPartition, StreamWriter writer)
        {
            if (s == n && s == 1)
            {
                writer.WriteLine("1");
                writer.Close();
                return;
            }
            nextSetPartition(currentPartition, n, writer);
            writer.WriteLine(s + " " + currentPartition.Count);
            foreach (var partition in currentPartition)
            {
                foreach (var elem in partition)
                {
                    writer.Write(elem + " ");
                }
                writer.WriteLine();
            }
            writer.WriteLine();
        }
 
        public static void Main()
        {
            var reader = new StreamReader("nextsetpartition.in");
            var writer = new StreamWriter("nextsetpartition.out");
            StirlingDynamics = new long[202, 202];
            StitlingInitialize(200);
            var line = string.Empty;
            var readingPartition = false;
            var localn = 0;
            var locals = 0;
            var localCounter = 0;
            var localPartition = new List<List<int>>();
            while ((line = reader.ReadLine()) != "0 0")
            {
                var init1 = line.Split();
                if (!readingPartition)
                {
                    locals = int.Parse(init1[0]);
                    localn = int.Parse(init1[1]);
                    readingPartition = true;
                }
                else
                {
                    var nums = line.Split().ToList().ConvertAll(int.Parse);
                    localPartition.Add(nums);
                    localCounter++;
                    if (localCounter == localn)
                    {
                        reader.ReadLine();
                        executeTest(locals, localn, localPartition, writer);
                        readingPartition = false;
                        localCounter = 0;
                        localPartition = new List<List<int>>();
                    }
                }
            }
            writer.Close();
        }
    }
}