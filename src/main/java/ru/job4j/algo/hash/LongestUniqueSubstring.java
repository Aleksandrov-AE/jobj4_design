package ru.job4j.algo.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        String result = "";
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int bestStart = 0;
        int bestLen = 0;
        for (int right = 0; right < str.length(); right++) {
            char ch = str.charAt(right);
            if (map.containsKey(ch)) {
                left = Math.max(left, map.get(ch) + 1);
            }
                map.put(ch, right);
            int currentLen = right - left + 1;
            if (currentLen > bestLen) {
                bestLen = currentLen;
                bestStart = left;
            }
        }

        return str.substring(bestStart, bestStart + bestLen);

    }
    public static void main(String[] args) {
        System.out.println(longestUniqueSubstring("aaaaa"));
    }
}