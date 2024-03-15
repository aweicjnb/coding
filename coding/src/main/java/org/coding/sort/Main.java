package org.coding.sort;

import org.coding.util.Tools;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,7,4,2,0,8,3,8,0,1,6,3,58,4,3,2,34,6,7,8,9};
//        MergeSort.merge(arr, 0, arr.length - 1, new int[arr.length]);
//        QuickSort.quickSort(arr, 0, arr.length - 1);
        Oo0oSort.sort(arr);
        Tools.print(arr);
    }
}
