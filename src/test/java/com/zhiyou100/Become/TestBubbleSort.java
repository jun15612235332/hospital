package com.zhiyou100.Become;

import java.util.Arrays;

public class TestBubbleSort {

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 4, 8, 6};
        int[] ints = bullonSort(arr);
        System.out.println(Arrays.toString(ints));
    }
    public static int[] bullonSort(int[] arr){
        int temp;
        for (int i=0;i<arr.length-1;i++) {
            for (int j=i+1;j<arr.length;j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

        }
        return arr;
    }
}
