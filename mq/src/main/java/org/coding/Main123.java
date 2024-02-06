package org.coding;

import java.util.ArrayList;
import java.util.List;

public class Main123 {
    public static void main(String[] args) {
        System.out.println(1<<7);
        System.out.println("Hello world!");

        List<Object> oomList = new ArrayList<>();
        // 无限循环创建对象
        while (true) {
            oomList.add(new Object());
        }


    }
}