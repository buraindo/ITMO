/*Во входном файле заданы числа n и k. Выведите в выходной файл все разбиения n-элементного
множества на k неупорядоченных множеств. Разбиения можно выводить в любом порядке. Внутри
разбиения множества можно выводить в любом порядке. Внутри множества числа надо выводить в
возрастающем порядке. 1 ≤ k ≤ n ≤ 10*/
﻿using System;
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
        public static int[,] Dynamics;
        public static long[,] StirlingDynamics;
        public static long amount = 0L;
 
        public static void Initialize(int l)
        {
            for (var i = 0; i != l + 1; i++)
            for (var k = 0; k != l + 1; k++)
                Dynamics[i, k] = 0;
            Dynamics[0, 0] = 1;
            for (var i = 1; i != l + 1; i++)
            {
                Dynamics[0, i] = 0;
            }
            for (var i = 1; i != l + 1; i++)
            {
                for (var k = 0; k != l; k++)
                {
                    if (k == 0)
                        Dynamics[i, k] = Dynamics[i - 1, k + 1];
                    else Dynamics[i, k] = Dynamics[i - 1, k + 1] + Dynamics[i - 1, k - 1];
                }
            }
        }
 
        public static void StitlingInitialize(int l)
        {
            for (var i = 0; i != l + 1; i++)
            {
                for (var j = 0; j != l + 1; j++)
                {
                    if (i == j)
                        StirlingDynamics[i, j] = 1;
                    else if (i == 0 || j == 0)
                        StirlingDynamics[i, j] = 0;
                    else StirlingDynamics[i, j] = j * StirlingDynamics[i - 1, j] + StirlingDynamics[i - 1, j - 1];
                }
            }
        }
 
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
 
        public static void Main()
        {
            var reader = new StreamReader("part2sets.in");
            var writer = new StreamWriter("part2sets.out");
            var init1 = reader.ReadLine().Split();
            var s = int.Parse(init1[0]);
            var n = int.Parse(init1[1]);
            if (s == n && s == 1)
            {
                writer.WriteLine("1");
                writer.Close();
                return;
            }
            StirlingDynamics = new long[s + 2, s + 2];
            StitlingInitialize(s);
            var setPartition = new List<List<int>>();
            var tempList = new List<int>();
            for (var i = 1; i != s; i++)
                tempList.Add(i);
            setPartition.Add(tempList);
            setPartition.Add(new List<int> {s});
            while (amount != StirlingDynamics[s, n])
            {
                nextSetPartition(setPartition, n, writer);
                if (setPartition.Count == n)
                {
                    amount++;
                    foreach (var partition in setPartition)
                    {
                        foreach (var elem in partition)
                        {
                            writer.Write(elem + " ");
                        }
                        writer.WriteLine();
                    }
                    writer.WriteLine();
                }
            }
            writer.Close();
        }
    }
}