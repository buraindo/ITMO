/*Шахматная ассоциация решила оснастить всех своих сотрудников такими телефонными номера-
ми, которые бы набирались на кнопочном телефоне ходом коня. Например, ходом коня набирается
телефон 340-49-27. При этом телефонный номер не может начинаться ни с цифры 0, ни с цифры 8.
Напишите программу, определяющую количество телефонных номеров длины N, набираемых
ходом коня. Поскольку таких номеров может быть очень много, выведите ответ по модулю 10^9.*/
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Configuration;
using System.Runtime.CompilerServices;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        static System.Numerics.BigInteger[,] Initialize(System.Numerics.BigInteger[,] list, int length)
        {
            for (var i = 0; i != 10; i++)
                if (i != 0 && i != 8)
                    list[i, 1] = System.Numerics.BigInteger.One;
                else list[i, 1] = System.Numerics.BigInteger.Zero;
            for (var i = 2; i != length; i++)
                list[5, i] = System.Numerics.BigInteger.Zero;
            var k = 2;
            while (k != length)
            {
                for (var i = 0; i != 10; i++)
                {
                    switch (i)
                    {
                        case 0:
                            list[i, k] = System.Numerics.BigInteger.Add(list[4, k - 1],list[6, k - 1]);
                            break;
                        case 1:
                            list[i, k] = System.Numerics.BigInteger.Add(list[6, k - 1],list[8, k - 1]);
                            break;
                        case 2:
                            list[i, k] = System.Numerics.BigInteger.Add(list[7, k - 1],list[9, k - 1]);
                            break;
                        case 3:
                            list[i, k] = System.Numerics.BigInteger.Add(list[4, k - 1],list[8, k - 1]);
                            break;
                        case 4:
                            list[i, k] = System.Numerics.BigInteger.Add(System.Numerics.BigInteger.Add(list[0, k - 1], list[3, k - 1]),list[9, k - 1]);
                            break;
                        case 6:
                            list[i, k] = System.Numerics.BigInteger.Add(System.Numerics.BigInteger.Add(list[1, k - 1], list[7, k - 1]), list[0, k - 1]);
                            break;
                        case 7:
                            list[i, k] = System.Numerics.BigInteger.Add(list[2, k - 1],list[6, k - 1]);
                            break;
                        case 8:
                            list[i, k] = System.Numerics.BigInteger.Add(list[1, k - 1], list[3, k - 1]);
                            break;
                        case 9:
                            list[i, k] = System.Numerics.BigInteger.Add(list[2, k - 1],list[4, k - 1]);
                            break;
                    }
                }
                k++;
            }
            return list;
        }
 
        static void Main()
        {
            var n = int.Parse(Console.ReadLine());
            var protoDynamics = new System.Numerics.BigInteger [10, n + 1];
            protoDynamics = Initialize(protoDynamics, n + 1);
            var amount = System.Numerics.BigInteger.Zero;
            for (var i = 0; i != 10; i++)
            {
                amount = System.Numerics.BigInteger.Add(protoDynamics[i, n],amount);
            }
            amount = System.Numerics.BigInteger.Remainder(amount, new System.Numerics.BigInteger(1000000000));
            Console.WriteLine(amount);
        }
    }
}