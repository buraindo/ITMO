/*Дана строка, составленная из круглых, квадратных и фигурных скобок. Определите, какое наи-
меньшее количество символов необходимо удалить из этой строки, чтобы оставшиеся символы об-
разовывали правильную скобочную последовательность.*/
using System;
using System.Diagnostics;
using System.Collections.Generic;
using System.IO;
using System.Runtime.InteropServices;
 
namespace LabProblem.Properties
{
    public class Class2
    {
        public static void Main()
        {
            var str = Console.ReadLine();
            if (str.Length == 0)
            {
                Console.WriteLine(0);
                return;
            }
            var dynamics = new int[str.Length + 1, str.Length + 1];
            var stringDynamics = new string[str.Length + 1, str.Length + 1];
            for (var i = 0; i != str.Length + 1; i++)
            {
                for (var j = 0; j != str.Length + 1; j++)
                {
                    if (i == j)
                    {
                        dynamics[i, j] = 1;
                        stringDynamics[i, j] = string.Empty;
                    }
                }
            }
            for (var i = 2; i <= str.Length; i++)
            {
                for (var j = 0; j <= str.Length - i; j++)
                {
                    var substr = str.Substring(j, i);
                    if (i == 2)
                    {
                        if (substr[0] == '(' && substr[substr.Length - 1] == ')' ||
                            substr[0] == '[' && substr[substr.Length - 1] == ']' ||
                            substr[0] == '{' && substr[substr.Length - 1] == '}')
                        {
                            dynamics[j, j + i - 1] = 0;
                            stringDynamics[j, j + i - 1] = substr[0] == '(' ? "()" :
                                substr[0] == '[' ? "[]" : "{}";
                        }
                        else
                        {
                            dynamics[j, j + i - 1] = 2;
                            stringDynamics[j, j + i - 1] = string.Empty;
                        }
                    }
                    else
                    {
                        var min = 150;
                        var strMin = string.Empty;
                        if (substr[0] == '(' && substr[substr.Length - 1] == ')' ||
                            substr[0] == '[' && substr[substr.Length - 1] == ']' ||
                            substr[0] == '{' && substr[substr.Length - 1] == '}')
                            if (dynamics[j + 1, j + i - 2] < min)
                            {
                                min = dynamics[j + 1, j + i - 2];
                                strMin = substr[0] == '(' ? "(" + stringDynamics[j + 1, j + i - 2] + ")" :
                                    substr[0] == '[' ? "[" + stringDynamics[j + 1, j + i - 2] + "]" :
                                    "{" + stringDynamics[j + 1, j + i - 2] + "}";
                            }
                        for (var t = j; t != j + i - 1; t++)
                        {
                            if (dynamics[j, t] + dynamics[t + 1, j + i - 1] < min)
                            {
                                min = dynamics[j, t] + dynamics[t + 1, j + i - 1];
                                strMin = stringDynamics[j, t] + stringDynamics[t + 1, j + i - 1];
                            }
                        }
                        dynamics[j, j + i - 1] = min;
                        stringDynamics[j, j + i - 1] = strMin;
                    }
                }
            }
            Console.WriteLine(stringDynamics[0, str.Length - 1]);
        }
    }
}