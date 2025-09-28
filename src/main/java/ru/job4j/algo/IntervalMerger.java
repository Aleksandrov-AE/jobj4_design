package ru.job4j.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        int[][] result;
        List<int[]> mergedIntervals = new ArrayList<>();
        if (intervals.length == 0) {
            result = intervals;
        } else {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            for (int[] currentInterval : intervals) {
                if (mergedIntervals.isEmpty()) {
                    mergedIntervals.add(new int[]{currentInterval[0], currentInterval[1]});
                } else {
                    int[] lastInterval = mergedIntervals.get(mergedIntervals.size() - 1);
                    if (currentInterval[0] <= lastInterval[1]) {
                        lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
                    } else {
                        mergedIntervals.add(new int[]{currentInterval[0], currentInterval[1]});
                    }
                }
            }
            result = mergedIntervals.toArray(new int[mergedIntervals.size()][]);
        }
        return result;
    }
}
//вычислительная сложность n*log(n) объем используемой памяти зависит от размера входного массива O(n)