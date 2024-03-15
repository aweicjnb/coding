package org.coding.test;

public class jz42 {
/*
    输入一个整形数组（可能有正数和负数），求数组中连续子数组（最少有一个元素）的最大和。要求时间复杂度为O(n)。
*/
    public int solution(int[] arr) {
        int sum = arr[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; ++i) {
            sum += arr[i];
            if (sum < arr[i]) {
                sum = arr[i];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

}
