package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    /** Добавьте поля класса здесь, если это необходимо */

    public static int[] findSmallestRange(int[] nums, int k) {
        int count = 1;
        boolean found = false;
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count == k) {
                result[0] = i + 2 - k;
                result[1] = i + 1;
                found = true;
                break;
            }
        }


        return found ? result : null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 5, 6, 7};
        int k = 4;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}