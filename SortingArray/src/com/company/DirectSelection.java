package com.company;

import java.util.Arrays;

public class DirectSelection {
    public static void main(String[] args) {
        int[] array = {8, 3, 5, 6, 12, 5, 4, 3, 11};

        for (int j = 0; j < array.length; j++) {
            int minIndex = j;
            for (int i = j + 1; i < array.length; i++) {
                if (array[i] < array[minIndex]) {
                    minIndex = i;
                }
            }
            int temp;
            if (minIndex != j) {
                temp = array[minIndex];
                array[minIndex] = array[j];
                array[j] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
