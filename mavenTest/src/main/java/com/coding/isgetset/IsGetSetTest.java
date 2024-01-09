package com.coding.isgetset;

import lombok.Data;

import java.io.Serializable;

@Data
public class IsGetSetTest implements Serializable {
    private String username;
    private Integer age;
    public Boolean flag;

    public String getUserName() {
        System.out.println("---m1Test----");
        return "ok";
    }

    public void setAge(Integer age) {
        System.out.println("---ageTest---");
    }

    public Boolean isFlag() {
        System.out.println("--flag---");
        return true;
    }
}
