package vigenere.util;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class StringUtils {

    private static final int INDEX_NOT_FOUND = -1;

    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    private static int indexOf(final CharSequence cs, final CharSequence searchChar, final int start) {
        return cs.toString().indexOf(searchChar.toString(), start);
    }

    public static int countMatches(final CharSequence str, final CharSequence sub, final SortedSet<Integer> distances) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        List<Integer> indexes = new ArrayList<>();
        while ((idx = indexOf(str, sub, idx)) != INDEX_NOT_FOUND) {
            count++;
            final var i = idx;
            distances.addAll(indexes.stream().map(d -> i - d).collect(Collectors.toList()));
            indexes.add(idx);
            idx += sub.length();
        }
        return count;
    }

}
