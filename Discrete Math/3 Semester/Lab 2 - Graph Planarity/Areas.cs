using System;
using System.Collections.Generic;
using System.Linq;

namespace LabProblem
{
    public static class Util
    {
        public const double EPS = 1e-9;
        public static Dictionary<Point, int> PointToId;
        public static List<Point> Points;
        public static List<List<int>> Graph;
    }

    public class Point : IComparable<Point>
    {
        public double X { get; }
        public double Y { get; }

        public Point(double x, double y)
        {
            X = x;
            Y = y;
        }

        public double AngleTo(Point other)
        {
            var theta = Math.Atan2(other.Y - Y, other.X - X) * (180 / Math.PI);
            return theta < 0 ? theta + 360 : theta;
        }

        public override bool Equals(object obj)
        {
            if (obj is Point other)
            {
                return other.X.Equals(X) && other.Y.Equals(Y);
            }
            return false;
        }

        public override int GetHashCode()
        {
            return X.GetHashCode() + Y.GetHashCode();
        }

        public int CompareTo(Point other)
        {
            if (Math.Abs(other.X - X) < Util.EPS && Math.Abs(other.Y - Y) < Util.EPS) return 0;
            if (X < other.X - Util.EPS || Math.Abs(X - other.X) < Util.EPS && Y < other.Y - Util.EPS) return -1;
            return 1;
        }
    }

    public class Areas
    {
        private static int GetId(Point point)
        {
            if (!Util.PointToId.ContainsKey(point))
            {
                Util.PointToId[point] = Util.Points.Count;
                Util.Points.Add(point);
                Util.Graph.Add(new List<int>());
            }

            return Util.PointToId[point];
        }

        private static Point Intersect(KeyValuePair<Point, Point> lhs, KeyValuePair<Point, Point> rhs)
        {
            var x1 = lhs.Key.X;
            var x2 = lhs.Value.X;
            var x3 = rhs.Key.X;
            var x4 = rhs.Value.X;
            var y1 = lhs.Key.Y;
            var y2 = lhs.Value.Y;
            var y3 = rhs.Key.Y;
            var y4 = rhs.Value.Y;
            var denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

            if (Math.Abs(denominator) < Util.EPS)
            {
                return null;
            }

            return new Point(
                ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator,
                ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator
            );
        }

        private class PointComp : IComparer<int>
        {
            private readonly int i;
            public PointComp(int i)
            {
                this.i = i;
            }
            public int Compare(int x, int y)
            {
                return Util.Points[x].AngleTo(Util.Points[i]).CompareTo(Util.Points[y].AngleTo(Util.Points[i]));
            }
        }
        
        public static void Main()
        {
            Util.PointToId = new Dictionary<Point, int>();
            Util.Points = new List<Point>();
            Util.Graph = new List<List<int>>();
            var areas = new List<double>();
            var n = Convert.ToInt32(Console.ReadLine());
            var a = new List<KeyValuePair<Point, Point>>();
            for (var i = 0; i < n; i++)
            {
                var line = Console.ReadLine().Split(' ');
                var x1 = Convert.ToInt32(line[0]);
                var y1 = Convert.ToInt32(line[1]);
                var x2 = Convert.ToInt32(line[2]);
                var y2 = Convert.ToInt32(line[3]);
                a.Add(new KeyValuePair<Point, Point>(new Point(x1, y1), new Point(x2, y2)));
            }

            for (var i = 0; i < n; i++)
            {
                var cur = new List<Point>();
                for (var j = 0; j < n; j++)
                {
                    var intersectionPoint = Intersect(a[i], a[j]);
                    if (intersectionPoint != null)
                    {
                        cur.Add(intersectionPoint);
                    }
                }

                cur.Sort();
                for (var j = 0; j < cur.Count - 1; j++)
                {
                    var x = GetId(cur[j]);
                    var y = GetId(cur[j + 1]);
                    if (x == y) continue;
                    Util.Graph[x].Add(y);
                    Util.Graph[y].Add(x);
                }
            }
            for (var i = 0; i < Util.Graph.Count; i++)
            {
                Util.Graph[i].Sort((lhs, rhs) => Util.Points[lhs].AngleTo(Util.Points[i]).CompareTo(Util.Points[rhs].AngleTo(Util.Points[i])));
                Util.Graph[i] = new List<int>(Util.Graph[i].Distinct());
            }
            var used = new bool[Util.Graph.Count][];
            for (var i = 0; i < Util.Graph.Count; i++)
            {
                used[i] = new bool[Util.Graph[i].Count];
            }

            for (var i = 0; i < Util.Graph.Count; i++)
            {
                for (var j = 0; j < Util.Graph[i].Count; j++)
                {
                    if (used[i][j]) continue;
                    used[i][j] = true;
                    var current = Util.Graph[i][j];
                    var next = i;
                    var facet = new List<int>();
                    while (true)
                    {
                        facet.Add(current);
                        var index = Util.Graph[current].BinarySearch(next, new PointComp(current));
                        if (++index == Util.Graph[current].Count)
                        {
                            index = 0;
                        }
                        if (used[current][index]) break;
                        used[current][index] = true;
                        next = current;
                        current = Util.Graph[current][index];
                    }
                    
                    var area = 0.0;
                    facet.Add(facet[0]);
                    for (var k = 0; k < facet.Count - 1; k++)
                    {
                        area += (Util.Points[facet[k]].X + Util.Points[facet[k + 1]].X) *
                                (Util.Points[facet[k]].Y - Util.Points[facet[k + 1]].Y);
                    }
                    if (area >= Util.EPS)
                        areas.Add(area / 2);
                }
            }

            Console.WriteLine(areas.Count);
            areas.Sort();
            foreach (var area in areas)
            {
                Console.WriteLine(area);
            }
        }
    }
}