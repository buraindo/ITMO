/*Около Петиного университета недавно открылось новое кафе, в котором действует следующая
система скидок: при каждой покупке более чем на 100 рублей покупатель получает купон, дающий
право на один бесплатный обед (при покупке на сумму 100 рублей и меньше такой купон покупатель
не получает).
Однажды Пете на глаза попался прейскурант на ближайшие n дней. Внимательно его изучив,
он решил, что будет обедать в этом кафе все n дней, причем каждый день он будет покупать в
кафе ровно один обед. Однако стипендия у Пети небольшая, и поэтому он хочет по максимуму ис-
пользовать предоставляемую систему скидок так, чтобы его суммарные затраты были минимальны.
Требуется найти минимально возможную суммарную стоимость обедов и номера дней, в которые
Пете следует воспользоваться купонами.*/
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Configuration;
using System.Reflection;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        static void Main()
        {
            var n = int.Parse(Console.ReadLine());
            if (n == 0)
            {
                Console.WriteLine("0\n0 0");
                return;
            }
            if (n == 1)
            {
                var price = int.Parse(Console.ReadLine());
                Console.WriteLine(price);
                if (price > 100)
                    Console.WriteLine("1 0");
                else Console.WriteLine("0 0");
                return;
            }
            var index = 0;
            var prices = new List<int>();
            var days = new List<int>();
            var spentMoney = 0;
            var spentCoupons = 0;
            var nonSpentCoupons = 0;
            var dynamics = new int[n + 2, n + 2];
            var spendingDynamics = new int[n + 2, n + 2];
            prices.Add(1707);
            for (var i = 0; i != n; i++)
            {
                prices.Add(int.Parse(Console.ReadLine()));
            }
            dynamics[0, 0] = 0;
            spendingDynamics[0, 0] = 0;
            for (var i = 1; i != n + 1; i++) dynamics[0, i] = int.MaxValue / 24;
            for (var i = 1; i != n + 1; i++)
            {
                if (dynamics[i - 1, 0] + prices[i] < dynamics[i - 1, 1])
                {
                    dynamics[i, 0] = dynamics[i - 1, 0] + prices[i];
                    spendingDynamics[i, 0] = spendingDynamics[i - 1, 0];
                }
                else
                {
                    dynamics[i, 0] = dynamics[i - 1, 1];
                    spendingDynamics[i, 0] = spendingDynamics[i - 1, 1] + 1;
                }
                for (var j = 1; j != n + 1; j++)
                {
                    if (j > i)
                    {
                        dynamics[i, j] = int.MaxValue / 24;
                        continue;
                    }
                    if (prices[i] > 100)
                    {
                        if (dynamics[i - 1, j - 1] + prices[i] < dynamics[i - 1, j + 1])
                        {
                            dynamics[i, j] = dynamics[i - 1, j - 1] + prices[i];
                            spendingDynamics[i, j] = spendingDynamics[i - 1, j - 1];
                        }
                        else
                        {
                            dynamics[i, j] = dynamics[i - 1, j + 1];
                            spendingDynamics[i, j] = spendingDynamics[i - 1, j + 1] + 1;
                        }
                    }
                    if (prices[i] <= 100)
                    {
                        if (dynamics[i - 1, j] + prices[i] < dynamics[i - 1, j + 1])
                        {
                            dynamics[i, j] = dynamics[i - 1, j] + prices[i];
                            spendingDynamics[i, j] = spendingDynamics[i - 1, j];
                        }
                        else
                        {
                            dynamics[i, j] = dynamics[i - 1, j + 1];
                            spendingDynamics[i, j] = spendingDynamics[i - 1, j + 1] + 1;
                        }
                    }
                }
            }
            spentMoney = dynamics[n, index];
            if (dynamics[n, 1] <= spentMoney) index = 1;
            nonSpentCoupons = index;
            spentCoupons = spendingDynamics[n, index];
            var pos = index;
            for (var i = n; i != 0; i--)
            {
                if (prices[i] != 0)
                {
                    if (pos == 0)
                    {
                        if (dynamics[i, pos] - dynamics[i - 1, pos] == prices[i])
                        {
                            pos = pos;
                            //Console.Write("f " + dynamics[i, pos] + " ");
                        }
                        else if (dynamics[i - 1, pos + 1] == dynamics[i, pos])
                        {
                            days.Insert(0, i);
                            pos++;
                        }
                    }
                    else
                    {
                        var newPos = -1;
                        for (var j = pos - 1; j != pos + 2; j++)
                        {
                            if (dynamics[i, pos] - dynamics[i - 1, j] == prices[i])
                            {
                                newPos = j;
                                break;
                            }
                        }
                        if (newPos == -1)
                        {
                            for (var j = pos - 1; j != pos + 2; j++)
                            {
                                if (dynamics[i, pos] == dynamics[i - 1, j])
                                {
                                    newPos = j;
                                    days.Insert(0, i);
                                    break;
                                }
                            }
                        }
                        pos = newPos;
                    }
                }
            }
            Console.WriteLine(spentMoney);
            Console.WriteLine(nonSpentCoupons + " " + spentCoupons);
            foreach (var day in days)
            {
                Console.WriteLine(day);
            }
        }
    }
}