/*Коровы любят соревноваться в беге по лестницам небоскребов. А вниз потом едут на лифте.
Лифт имеет максимальную вместимость w (1 <= w <= 108) фунтов, а корова номер i весит c_i
(1 <= c_i <= w) фунтов.
Помогите Бесси определить минимальное количество спусков лифта, чтобы переместить вниз
все n (1 <= n <= 18) коров.
Сумма весов коров в каждом спуске не должна превышать W.*/
using System;
using System.IO;
using System.Collections.Generic;
using System.Text;
 
namespace LabProblem.Properties
{
    public class Class1
    {
        private static int n;
        private static int w;
 
        private static void Main()
        {
            var reader = new StreamReader("skyscraper.in");
            var writer = new StreamWriter("skyscraper.out");
 
            var s = reader.ReadLine().Split();
            n = int.Parse(s[0]);
            w = int.Parse(s[1]);
 
            var Result = new StringBuilder();
 
            var cows = new List<Cow>();
            var dc = 0;
            for (var i = 0; i != n; i++)
            {
                var weight = int.Parse(reader.ReadLine());
                if (weight == w)
                {
                    dc++;
                    Result.Append("1 " + (i + 1) + "\n");
                }
                else
                {
                    cows.Add(new Cow(i, weight));
                }
            }
 
            cows.Sort();
 
            for (;;)
            {
                if (cows.Count == 0)
                    break;
                var herd = Get(cows);
                var newCows = new List<Cow>();
                var herdLine = new StringBuilder();
                foreach (var cow in cows)
                {
                    if (!herd.Has(cow))
                    {
                        newCows.Add(cow);
                    }
                    else
                    {
                        herdLine.Append(cow.id + 1 + " ");
                    }
                }
                cows = new List<Cow>(newCows);
                Result.Append(herd.size + " " + herdLine + "\n");
                dc++;
            }
            writer.Write(dc + "\n" + Result);
            writer.Close();
        }
 
        private static Herd Get(List<Cow> cows)
        {
            var cur = cows[0];
            var next = new List<Herd>();
            var Result = new List<Herd>();
 
            var hc = new bool[n];
            hc[cur.id] = true;
            next.Add(new Herd(hc, cur.weight, 1, 0));
 
            while (true)
            {
                var newNext = new List<Herd>();
 
                foreach (var herd in next)
                {
                    var b = true;
                    for (var i = herd.lastId + 1; i != cows.Count; i++)
                    {
                        var cow = cows[i];
                        if (herd.weight + cow.weight <= w && !herd.Has(cow))
                        {
                            newNext.Add(herd.Add(cow, i));
                            b = false;
                        }
                    }
                    if (b)
                    {
                        Result.Add(herd);
                    }
                }
                if (newNext.Count == 0)
                {
                    break;
                }
                next = newNext;
            }
            Result.AddRange(next);
            var Max = Result[0];
            foreach (var herd in Result)
            {
                if (Max.weight < herd.weight)
                {
                    Max = herd;
                }
            }
            return Max;
        }
 
        public class Herd
        {
            private bool[] cows;
            public int weight { get; set; }
            public int size { get; set; }
            public int lastId { get; set; }
 
            public Herd(bool[] cows, int weight, int size, int lastId)
            {
                this.cows = cows;
                this.weight = weight;
                this.size = size;
                this.lastId = lastId;
            }
 
            public Herd Add(Cow cow, int i)
            {
                var hc = (bool[]) cows.Clone();
                hc[cow.id] = true;
                return new Herd(hc, weight + cow.weight, size + 1, i);
            }
 
            public bool Has(Cow cow)
            {
                return cows[cow.id];
            }
        }
 
        public class Cow : IComparable<Cow>
        {
            public int id { get; set; }
            public int weight { get; set; }
 
            public Cow(int id, int weight)
            {
                this.id = id;
                this.weight = weight;
            }
 
            int IComparable<Cow>.CompareTo(Cow o)
            {
                return o.weight - weight;
            }
        }
    }
}