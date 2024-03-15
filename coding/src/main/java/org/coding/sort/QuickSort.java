package org.coding.sort;

import org.coding.util.Tools;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,7,4,2,0,8,3,8,0,1,6,3,58,4,3,2,34,6,7,8,9};
        quickSort(arr, 0, arr.length - 1);
        Tools.print(arr);
    }
    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int flag = left;
        int start = left;
        int end = right;
        while (start < end) {
            /**
             * 为什么右哨兵💂先走，因为右哨兵停下来只有两个可能，一个是遇到了小于flag的数，还有一个是遇到了左哨兵（也
             * 是小于flag的数，这个时候再因为还会把arr[start]于第一个flag结点交换一次，所以arr[start]，必须小于等于
             * arr[flag]结点，如果左先走，那么arr[start]就一定大于arr[flag]）
             */
            while (start < end && arr[end] >= arr[flag]) end--;
            while (start < end && arr[start] <= arr[flag]) start++;
            Tools.swap(arr, start, end);
        }
        Tools.swap(arr, flag, start);
        quickSort(arr, left, start - 1);
        quickSort(arr, start + 1, right);
    }
}


