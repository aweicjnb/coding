package org.coding.collection;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MyTreeMap {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("2", "1");
        treeMap.put("1", "3");
        treeMap.put("3", "2");
        for (Object entry: treeMap.entrySet()) {
            System.out.println(entry);

        }
    }
}
