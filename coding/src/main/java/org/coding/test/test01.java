package org.coding.test;
//
//import java.util.*;
//
//// 注意类名必须为 Main, 不要有任何 package xxx 信息
//public class test01 {
//    public static void main(String[] args) {
//        System.out.println(new Random().nextInt(2));
//    }
////    public static void main(String[] args) {
////        Scanner sc = new Scanner(System.in);
////        // 注意 hasNext 和 hasNextLine 的区别
////        int len = sc.nextInt();
////        int k = sc.nextInt();
////        double random = Math.random();
////        int[] arr = new int[len];
////        for (int i = 0; i < len; ++i) {
////            arr[i] = sc.nextInt();
////        }
////        int ans = 0;
////        int[] total = getTotalZero(0, len - 1, arr);
//////        System.out.println(total);
////        for (int i = 0; i < len; ++i) {
////            for (int j = i; j < len; ++j) {
////                int[] count = getTotalZero(i, j, arr);
////                if (Math.min(total[0] - count[0], total[1] - count[1]) >= k) {
//////                    System.out.println(i + "--"+ j);
//////                    System.out.println(count);
////                    ans++;
////                }
////            }
////        }
////        System.out.print(ans);
////
////    }
//
//    public static int[] getTotalZero(int left, int right, int[] arr) {
//        int a = 0;
//        int b = 0;
//        for (int i = left; i <= right; ++i) {
//            int num = arr[i];
//            a += getNum(num, 5);
//            b += getNum(num, 2);
//        }
//        return new int[]{a, b};
//    }
//
//    public static int getNum(int num, int d) {
//        int count = 0;
//        while (num != 0 && num % d == 0) {
//            count++;
//            num /= d;
//        }
//        return count;
//    }
//}



import java.time.LocalDate;
import java.util.*;


public class test01 {
    public static void main(String[] args) {
//        test01 test01 = new test01();
//        System.out.println(test01.GetNumberOfK(new int[]{1, 2, 3, 3, 3, 3, 4, 5}, 3));

        getTodayNum("2023,9,1");
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @param k int整型
     * @return int整型
     */
    public int GetNumberOfK (int[] nums, int k) {
        // write code here
        // [1,2,3,3,3,3,4,5],3  4
        return binSearch(nums, k) - binSearch(nums, k - 1);
    }

    public int binSearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
        return left;
    }


    public static void getTodayNum(String str) {
        String[] split = str.split(",");
        LocalDate localDate = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        System.out.println(localDate.getDayOfYear());
    }

}