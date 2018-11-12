/*Компания BrokenTiles планирует заняться выкладыванием во дворах у состоятельных клиентов
узор из черных и белых плиток, каждая из которых имеет размер 1 × 1 метр. Известно, что дворы
всех состоятельных людей имеют наиболее модную на сегодня форму прямоугольника n×m метров.
Однако при составлении финансового плана у директора этой организации появилось целых две
серьезных проблемы: во первых, каждый новый клиент очевидно захочет, чтобы узор, выложенный
у него во дворе, отличался от узоров всех остальных клиентов этой фирмы, а во вторых, этот узор
должен быть симпатичным.
Как показало исследование, узор является симпатичным, если в нем нигде не встречается квад-
рата 2 × 2 метра, полностью покрытого плитками одного цвета.
Для составления финансового плана директору необходимо узнать, сколько клиентов он сможет
обслужить, прежде чем симпатичные узоры данного размера закончатся. Помогите ему.*/
using System;
using System.IO;
  
namespace LabProblem.Properties
{
    public class Class1
    {
        private static int n;
        static bool CanGo(int firstProfile, int secondProfile)
        {
            var strProf1 = Convert.ToString(firstProfile, 2).PadLeft(n, '0');
            var strProf2 = Convert.ToString(secondProfile, 2).PadLeft(n, '0');
            for (var i = 0; i != n - 1; i++)
            {
                if (strProf1[i + 1] == '1' && strProf1[i] == '1' && strProf2[i + 1] == '1' && strProf2[i] == '1')
                    return false;
                if (strProf1[i + 1] == '0' && strProf1[i] == '0' && strProf2[i + 1] == '0' && strProf2[i] == '0')
                    return false;
            }
            return true;
        }
  
        static void Main()
        {
            var reader = new StreamReader("nice.in");
            var writer = new StreamWriter("nice.out");
            var init = reader.ReadLine().Split();
            n = int.Parse(init[0]);
            var m = int.Parse(init[1]);
            if (n > m)
            {
                var temp = n;
                n = m;
                m = temp;
            }
            var dynamics = new int[1 << n, 1 << n];
            var tableOfContents = new int[m, 1 << n];
            for (var i = 0; i != 1 << n; i++)
            {
                for (var j = 0; j != 1 << n; j++)
                    dynamics[i, j] = CanGo(i, j) ? 1 : 0;
            }
            for (var i = 0; i != 1 << n; i++)
                tableOfContents[0, i] = 1;
            for (var k = 1; k != m; k++)
            {
                for (var i = 0; i != 1 << n; i++)
                {
                    for (var j = 0; j != 1 << n; j++)
                        tableOfContents[k, i] += tableOfContents[k - 1, j] * dynamics[j, i];
                }
            }
            var result = 0;
            for (var i = 0; i != 1 << n; i++)
                result += tableOfContents[m - 1, i];
            writer.WriteLine(result);
            writer.Close();
        }
    }
}