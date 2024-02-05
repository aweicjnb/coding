package org.coding.optional;

import java.util.Optional;

public class MyOptionalTest {
    public static void main(String[] args) {
        //不能为空
        Optional<Object> o = Optional.of("abc");
        System.out.println(o.get());


        Optional<String> s = Optional.ofNullable("123");
        //如果存在才会执行 lambda 表达式里面的方法体
        s.ifPresent(consumer -> {
            System.out.println(consumer.length());
        });
        //如果有值 返回true
        if (s.isPresent()) {
            System.out.println(s.get());
        }

        //orElse
        Optional<Object> o1 = Optional.ofNullable(null);
        System.out.println(o1.orElse("没有值 返回 default"));


        //orElseGet
        Optional<String> orElseGet = Optional.ofNullable(null);
        System.out.println(orElseGet.orElseGet(() -> "如果为空 就 返回执行方法体之后的结果"));


        //orElseGet
        Optional<String> orElseThrow = Optional.ofNullable("orElseThrow");
        try {
            orElseThrow.orElseThrow(() -> new Exception());
        } catch (Exception e) {
            System.out.println("有异常 w造的");
            throw new RuntimeException(e);
        }


        Optional<String> s1 = Optional.of("abc");
        Optional<String> s2 = s1.map(item -> item + "buff");
        System.out.println(s2.get());


        Optional<String> abc = Optional.of("abc");
        Optional<String> s3 = abc.flatMap(item -> Optional.ofNullable(item + "+++"));
        System.out.println(s3.orElse("值 为null 走默认值"));


        Optional<String> s4 = abc.filter(item -> item.length() > 3);
        System.out.println(s4.orElse("值 为null 走默认值"));


    }
}
