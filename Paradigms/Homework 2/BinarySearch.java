package search;

import java.util.Arrays;

public class BinarySearch {
    //let l = leftBorder, r = rightBorder, m = midBorder, n = a.length, mv = midValue, a = array; t = target;
    //PRE: COND A: for all i = 0..n-1 a[i+1]<=a[i];
    private static int BinSearchIterative(int[] array, int target) {

        if (array.length == 0) {
            return 0;
        }
        int leftBorder = 0;
        int rightBorder = array.length - 1;
        //PRE: l = 0, r = n - 1;
        while (leftBorder <= rightBorder) {
            //INV: r >= l && r - l > r' - l' && l <= l' <= r' <= r && |(r - l) - 2 * (r' - l')| <= 1;
            int midBorder = (leftBorder + rightBorder) / 2;
            int midValue = array[midBorder];
            if (midValue > target) {
                leftBorder = midBorder + 1;
                //l' = m+1; r' = r; mv > t => a[l'] > t;
                //SINCE A IF ∃i: a[i] == t, => i ∈ l'..r';
            } else {
                rightBorder = midBorder - 1;
                //l' = l; r' = m-1; mv <= t => a[r'] <= t;
                //SINCE A IF ∃i: a[i] == t, THEN i ∈ l'..r';
            }
        }
        //POST:
        //for all i = 0..n-1 a[i] = a'[i]; a[l] == t;
        //let EVENT B:
        //a[m] == t, THEN:
        //r' = m - 1; ...
        //l' == r', mv > t, => m' = (l'+r')/2; l' = m' + 1 == (l+r)/2;
        //IF ∃i : a[i] == t,
        //  IF a[m] != t, THEN ∃iteration : a[m'] == t, => B';
        //ELSE IF t < a[n - 1] THEN l' = r + 1;
        //ELSE IF t > a[0] THEN l' = 0 & a[l'] <= t;
        return leftBorder;
    }

    //let l = leftBorder, r = rightBorder, m = midBorder, n = a.length, mv = midValue, a = array; t = target;
    //PRE: COND A: for all i = 0..n-1 a[i+1]<=a[i];
    //r >= l && r - l > r' - l' && l <= l' <= r' <= r && |(r - l) - 2 * (r' - l')| <= 1; 0 <= l <= r < n;
    private static int BinSearchRecursive(int[] array, int leftBorder, int rightBorder, int target) {

        if (array.length == 0) {
            return 0;
        }
        if (leftBorder == array.length || leftBorder == rightBorder)
            return leftBorder;
        //l == n => a[n - 1] > t;
        //l == r => a[l] = t;
        int midBorder = (leftBorder + rightBorder) / 2;
        int midValue = array[midBorder];
        if (target < midValue) {
            //l' = m+1; r' = r; mv > t => a[l'] > t;
            //SINCE A IF ∃i: a[i] == t, => i ∈ l'..r';
            return BinSearchRecursive(array, midBorder + 1, rightBorder, target);
        }
        //l' = l; r' = m; mv <= t => a[r'] <= t;
        //SINCE A IF ∃i: a[i] == t, THEN i ∈ l'..r';
        return BinSearchRecursive(array, leftBorder, midBorder, target);
        //POST:
        //for all i = 0..n-1 a[i] = a'[i];
        //(l' == n || l' == r');
        //let EVENT C:
        //a[m] == t, THEN:
        //r' = m; ...
        //l' == r' - 1, => m' = (l'+r'-1)/2; mv > t, => l' = m' + 1 == r' == m;
        //IF ∃i : a[i] == t,
        //  IF a[m] != t, THEN ∃iteration : a[m'] == t, => C';
        //ELSE IF t < a[n - 1] THEN l' = r + 1;
        //ELSE IF t > a[0] THEN l' = 0 & a[l'] <= t;
    }

    public static void main(String[] args) {
        System.out.println(BinSearchRecursive(Arrays.copyOfRange(Arrays.stream(args).mapToInt(Integer::parseInt).toArray(), 1, args.length), 0, args.length - 1, Integer.parseInt(args[0])));
        System.out.println(BinSearchIterative(Arrays.copyOfRange(Arrays.stream(args).mapToInt(Integer::parseInt).toArray(), 1, args.length), Integer.parseInt(args[0])));
    }
}
