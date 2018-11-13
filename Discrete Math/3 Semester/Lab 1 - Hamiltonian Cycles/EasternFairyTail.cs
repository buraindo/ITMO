using System;
using System.Collections.Generic;

namespace LabProblem
{
    public class EasternFairyTail
    {
        private static List<int> result;

        private static void BinarySearch(int left, int right, int v)
        {
            while (right - left > 1)
            {
                var mid = left + (right - left) / 2;
                Console.WriteLine(1 + " " + result[mid] + " " + v);
                Console.Out.Flush();
                var answer = Console.ReadLine();
                if (answer.Equals("YES"))
                {
                    left = mid;
                }
                else
                {
                    right = mid;
                }
            }

            result.Insert(right, v);
        }
        
        public static void Main()
        {
            var inputN = Console.ReadLine();
            int n;
            if (inputN != null)
            {
                n = int.Parse(inputN);
            }
            else n = -1;
            result = new List<int>(n) {1};
            for (var i = 2; i <= n; i++)
            {
                BinarySearch(-1, i - 1, i);
            }

            Console.Write("0 ");
            foreach (var v in result)
            {
                Console.Write(v + " ");
            }
        }
    }
}