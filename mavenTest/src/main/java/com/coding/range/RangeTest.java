package com.coding.range;

import com.alibaba.fastjson2.JSON;
import com.coding.test2.A;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.Range;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class RangeTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Range between = Range.between(1, 5);
        System.out.println(between.contains(0));
        System.out.println(between.contains(3));
        System.out.println(between.getMaximum());
        System.out.println(between.getMinimum());
        System.out.println(between.isAfter(2));
        System.out.println(between.isAfter(0));
        System.out.println(between.isBefore(2));
        System.out.println(between.isBefore(6));


        System.out.println("-----########-------");
        System.out.println(between);

        String jsonString = JSON.toJSONString(between);
        System.out.println(jsonString);
/*        Range range = JSON.parseObject(jsonString, Range.class);
        System.out.println(range);*/
        String jsonString1 = JSON.toJSONString(List.of(between.getMinimum(), between.getMinimum()));
        new ObjectMapper();
        System.out.println(jsonString1);
        List list = JSON.parseObject(jsonString1, List.class);


        System.out.println(list);


        ArrayList list1 = new ArrayList<String>();
        list1.add(1);
//        list1.add();
        list1.getClass().getMethod("add", Object.class).invoke(list1, "aaa");
        System.out.println(list1);

        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 100000; ++i) {
            list2.add(1);
        }

//        Long start = System.currentTimeMillis();
//        for (int i = 0; i < 100000; ++i) {
//            System.out.println(list2.get(i));
//        }
//        System.out.println(System.currentTimeMillis() - start);

        Long start = System.currentTimeMillis();
        list2.parallelStream().forEach(item -> {
            System.out.println(item);
        });
        System.out.println(System.currentTimeMillis() - start);




    }
}
