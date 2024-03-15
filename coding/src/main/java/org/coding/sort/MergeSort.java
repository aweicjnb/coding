package org.coding.sort;

import org.coding.util.Tools;

public class MergeSort {
    public static void merge(int[] arr, int left, int right, int[] tmp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        merge(arr, left, mid, tmp);
        merge(arr, mid + 1, right, tmp);
        mergeSort(arr, left, right, tmp);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] tmp) {
        int mid = (left + right) / 2;
        int index = left;
        int start = left;
        int end = mid + 1;
        while (start <= mid && end <= right) {
            if (arr[start] < arr[end]) {
                tmp[index++] = arr[start++];
            } else {
                tmp[index++] = arr[end++];
            }
        }
        while (start <= mid) {
            tmp[index++] = arr[start++];
        }
        while (end <= right) {
            tmp[index++] = arr[end++];
        }
        for (int i = left; i <= right; ++i) {
            arr[i] = tmp[i];
        }
    }
}
