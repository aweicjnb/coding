package org.coding;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        System.out.println(-1L ^ (-1L << 5));
        System.out.println();
        Date date = new Date();
        System.out.println(date);
        System.out.println(System.currentTimeMillis());
        System.out.println(date.getTime());

    }
}