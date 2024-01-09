package com.coding;

public enum EnumTest {
    A("daga大海", 1),
    B("小海", 2);


    public String name;
    public Integer age;

    EnumTest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }
}
