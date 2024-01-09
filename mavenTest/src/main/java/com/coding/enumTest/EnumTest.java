package com.coding.enumTest;

enum EnumTest {
    A("name", 1),
    B("key",2 );

    public String name;
    public Integer age;

    EnumTest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
