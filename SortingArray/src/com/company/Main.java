package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = {8, 3, 5, 6, 12, 5, 4, 3, 11};
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j - 1] > array[j]) {
                    // swap the elements!
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
