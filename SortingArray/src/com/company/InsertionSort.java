package com.company;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {8, 3, 5, 6, 12, 5, 4, 3, 11};
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j - 1] > array[j]) {
                int swap = array[j - 1];
                array[j - 1] = array[j];
                array[j] = swap;
                j = j - 1;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
