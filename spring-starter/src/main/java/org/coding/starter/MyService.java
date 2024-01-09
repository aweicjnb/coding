package org.coding.starter;

public class MyService {
    private String name;
    private Integer age;

    public MyService(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "MyService{name='" + this.name + '\'' + ", age=" + this.age + '}';
    }
}