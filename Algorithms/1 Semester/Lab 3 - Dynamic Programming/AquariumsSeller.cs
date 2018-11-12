/*Продавец аквариумов для кошек хочет объехать n городов, посетив каждый из них ровно один
раз. Помогите ему найти кратчайший путь.*/
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Diagnostics;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Configuration;
using System.Reflection;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
using System.Runtime.Remoting.Messaging;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        private const int dim = 120;
        private const int pow = 1 << 13;
        private const int Infinity = int.MaxValue / 2;
        static long[,] a = new long[dim, dim];
        static long[,] d = new long[pow, dim];
        static long n;
 
        static bool Inset(int mask, int v)
        {
            return Convert.ToBoolean(mask & (1 << (v - 1)));
        }
 
        static int Removebit(int mask, int bit)
        {
            return mask & ~(1 << (bit - 1));
        }
 
        static void Print(int mask, int last)
        {
            Console.Write(last + " ");
            var PreviousMask = Removebit(mask, last);
 
            for (var i = 1; i <= n; i++)
                if (Inset(PreviousMask, i))
                    if (d[mask, last] == d[PreviousMask, i] + a[i, last])
                    {
                        Print(PreviousMask, i);
                        break;
                    }
        }
 
        static long FindWay(int mask, int last)
        {
            if (d[mask, last] != -1) return d[mask, last];
            if (mask == 1 << (last - 1)) d[mask, last] = 0;
            else
            {
                d[mask, last] = Infinity;
                var PreviousMask = Removebit(mask, last);
                for (var i = 1; i <= n; i++)
                    if (Inset(PreviousMask, i))
                        d[mask, last] = Math.Min(d[mask, last], FindWay(PreviousMask, i) + a[i, last]);
            }
            return d[mask, last];
        }
 
        public static void Main()
        {
            for (var i = 0; i != pow; i++)
            {
                for (var j = 0; j != dim; j++)
                    d[i, j] = -1;
            }
            n = long.Parse(Console.ReadLine());
            for (var i = 1; i <= n; i++)
            {
                var line = Console.ReadLine().Split();
                for (var j = 1; j <= n; j++)
                {
                    a[i, j] = long.Parse(line[j - 1]);
                }
                Console.WriteLine();
            }
            var MaxMaskValue = (1 << (int) n) - 1;
 
            long res = Infinity;
            var LastResult = -1;
 
            for (var i = 1; i <= n; i++)
                if (FindWay(MaxMaskValue, i) < res)
                {
                    res = FindWay(MaxMaskValue, i);
                    LastResult = i;
                }
 
 
            for (var v = 1; v <= n; v++)
                if (d[MaxMaskValue, v] < res)
                {
                    res = d[MaxMaskValue, v];
                    LastResult = v;
                }
 
            Console.WriteLine(res);
            Print(MaxMaskValue, LastResult);
        }
    }
}