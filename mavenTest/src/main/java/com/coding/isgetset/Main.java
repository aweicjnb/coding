package com.coding.isgetset;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
/*        String path = "/Users/duitang/IdeaProjects/uCreateTest/mavenTest/src/main/resources/file/serialTest.txt";
        IsGetSetTest isGetSetTest = new IsGetSetTest();
        try (FileOutputStream out = new FileOutputStream(path);
                ObjectOutputStream oobj = new ObjectOutputStream(out);
                ) {
            oobj.writeObject(isGetSetTest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        FileInputStream in = new FileInputStream(path);
        ObjectInputStream inn = new ObjectInputStream(in);
        Object count = 0;
        Object o = inn.readObject();
        System.out.println("-------");
        System.out.println(o);*/



IsGetSetTest isGetSetTest = new IsGetSetTest();
        Class clazz = isGetSetTest.getClass();
        Field name = clazz.getDeclaredField("username");
        name.setAccessible(true);
        System.out.println(name.get(isGetSetTest));
        Method getName = clazz.getDeclaredMethod("getUserName");
        System.out.println(getName.invoke(isGetSetTest));


    }
}

