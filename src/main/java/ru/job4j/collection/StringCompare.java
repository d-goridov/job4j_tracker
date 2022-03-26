package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = Integer.compare(left.length(), right.length());
        int length = Math.min(left.length(), right.length());
        for (int index = 0; index < length; index++) {
            if (left.charAt(index) > right.charAt(index)) {
                result = 1;
                break;
            } else if (left.charAt(index) < right.charAt(index)) {
                result = -1;
                break;
            }
        }
        return result;
    }
}
