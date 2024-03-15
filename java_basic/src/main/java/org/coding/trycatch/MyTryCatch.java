package org.coding.trycatch;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class MyTryCatch {
    public static void main(String[] args) {
        System.out.println(a(0));

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(2, 22);
        map.put(1, 11);
        map.put(3, 33);
        System.out.println(map.firstKey());
//        PriorityQueue<>
    }

    public static int a(int a) {
        try {
            a++;
            a /= 0;
            return a;
        } catch (Exception e) {
            a++;
            return a;
        } finally {
            a++;
            return a;
        }
//        return a;
    }
}
