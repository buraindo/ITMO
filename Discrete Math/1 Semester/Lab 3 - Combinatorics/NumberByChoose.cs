/*Во входном файле заданы числа n, k и затем сочетание, состоящее из k чисел от 1 до n. Выведите
в выходной файл номер этого сочетания в лексикографическом порядке всех сочетаний из n чисел
по k (1 ≤ k ≤ n ≤ 30). Сочетания нумеруются, начиная с 0*/
﻿using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Net.Configuration;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        public static List<bool> usedOnes = new List<bool>();
        public static List<long> factors = new List<long>();
 
        public static void Initialize()
        {
            for (var i = 0; i != 19; i++)
                usedOnes.Add(false);
            factors.Add(1);
            factors.Add(1);
        }
 
        static long Factor(int n)
        {
            if (n < factors.Count)
                return factors[n];
            var f = Factor(n - 1) * n;
            factors.Add(f);
            return f;
        }
 
        static long Choose(int n, int k)
        {
            if (k == 0) return 1;
            if (k == 1)
                return n;
            var f = Factor(k);
            var p = 1L;
            for (var i = n - k + 1; i != n + 1; i++)
                p *= i;
            return p / f;
        }
 
        public static long Generator(List<int> combination, int nn)
        {
            var result = 0L;
            var n = combination.Count - 1;
            var isFalse = usedOnes[0];
            for (var i = 1; i != n + 1; i++)
            {
                var factorN = Factor(n - i);
                var usedAmount = n / factorN;
                var nonUsedAmount = 0;
                for (var w = combination[i - 1] + 1; w != combination[i]; w++)
                {
                    result += Choose(nn - w, n - i);
                }
            }
            return result;
        }
 
        public static void Main()
        {
            var reader = new StreamReader("choose2num.in");
            var writer = new StreamWriter("choose2num.out");
            var init1 = reader.ReadLine().Split();
            var init2 = reader.ReadLine().Split();
            var combination = new List<int>();
            Initialize();
            var n = int.Parse(init1[0]);
            var k = int.Parse(init1[1]);
            combination.Add(0);
            foreach (var element in init2)
            {
                combination.Add(int.Parse(element));
            }
            var permutations = Generator(combination,n);
            writer.Write(permutations);
            writer.Close();
        }
    }
}