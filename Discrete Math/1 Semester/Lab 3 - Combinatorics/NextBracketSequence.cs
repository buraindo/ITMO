/*Во входном файле задана правильная скобочная последовательность. Выведите в выходной сле-
дующую за ней в лексикографическом порядке среди всех правильных скобочных последователь-
ностей с таким же количеством открывающихся скобок, «(» < «)». Если такой нет, выведите «-».
Количество открывающихся скобок в последовательности — от 1 до 100 000.*/
using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net.Configuration;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
using System.Text;
 
namespace LabProblem.Properties
{
    public class Class13
    {
        static string findNextBracket(string init)
        {
            var op = SearchFwd(init.Length);
            var certainIndex = -1;
            var open = 0;
            var close = 0;
            op = SearchFwd(open + close + op);
            for (var i = init.Length - 1; i != -1; i--)
                if (init[i] == '(')
                {
                    open++;
                    if (open < close)
                        break;
                }
                else
                {
                    close++;
                }
            op = SearchFwd(open + close + op);
            if (open + close == init.Length)
                return string.Empty;
            var result = init.Substring(0, init.Length - open - close);
            result += ')';
            close--;
            result += new string('(', open);
            result += new string(')', close);
            return result;
        }
        public static void Main()
        {
            var reader = new StreamReader("nextbrackets.in");
            var writer = new StreamWriter("nextbrackets.out");
            var init = reader.ReadLine();
            var next = findNextBracket(init);
            if (next == string.Empty)
            {
                writer.Write("-");
                writer.Close();
                return;
            }
            writer.Write(next);
            writer.Close();
        }
    }
}