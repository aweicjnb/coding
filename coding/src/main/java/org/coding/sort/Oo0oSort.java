package org.coding.sort;

import org.coding.util.Tools;

public class Oo0oSort {
    public static void sort(int[] arr) {
        for (int i = arr.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    Tools.swap(arr, j, j + 1);
                }
            }
        }
    }
}
