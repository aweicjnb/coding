package com.coding.common_http;

import com.coding.aspect.test.StopClass;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    StopClass stopClass;

    @GetMapping("/hello")
    public void m() throws InterruptedException {
        System.out.println("hello world");
/*        return new User("user");*/
        List list = new ArrayList();
        List list2 = new ArrayList();

        list2.add(1);
        list2.add(2);
/*        list.add(new Object());*/
        list.add(new User("111", list2));
        list.add(new User("333", new HashMap()));

//        while (true) {
////            Object arr = new Object[Integer.MAX_VALUE - 3];
////            List list1 = new ArrayList();
////            Thread.sleep(100);
//            list.add(new User("111", list2));
//        }

/*

        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName());
                System.out.println("get lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("get lock2");
                }
            }
        }, "T1").start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName());
                System.out.println("get lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("get lock1");
                }
            }
        }, "T2").start();
*/


        Date data = new Date(1609459200000L);
        System.out.println(data.getYear());
        System.out.println(data.getMonth());
        System.out.println(data.getDay());
        System.out.println(data.getHours());
        System.out.println(data.getMinutes());
        System.out.println(data.getSeconds());

    }

    @GetMapping("/test")
    public Object m2() {
        User user = new User("111", "222");
        User2 user2 = new User2("111", "333");
        BeanUtils.copyProperties(user, user2);
        System.out.println(user);
        return user;
    }


    @GetMapping("/test1")
    public String m3() throws IOException {
        User user = new User("111", "222");
        FileOutputStream out = new FileOutputStream("/Users/duitang/IdeaProjects/uCreateTest/mavenTest/src/main/resources/file/test.txt");
        out.write(user.toString().getBytes());

/*
        验证为什么方法不能使用get/set/is前缀（是因为序列化，还是反射的原因）
        通过其他方式读取文件
*/


/*
        Properties source = new Properties();
*/
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("/file/test.txt");
/*        int n = 0;
        while ((n = in.read()) > 0) {
            n = 0;
            System.out.println(n);
        }*/
        return "ok";
    }


    @GetMapping("/m4/{id}")
    public String m4(@RequestParam("str") String str, Object obj, @PathVariable("id") Integer id) {
        System.out.println(str);
        System.out.println(obj);
        System.out.println(id);
        return "OK";
    }

    @GetMapping("/aspect/m2")
    public String aspectTest() {
//        StopClass stopClass = new StopClass();
//        stopClass.m2();
        stopClass.m2();

        return "aspectM2";
    }

    @GetMapping("/aspect/m1")
    public String aspectM1() {
//        StopClass stopClass = new StopClass();
//        stopClass.m1();
        stopClass.m1();;
        return "aspectM1";
    }



/*-------------------------#################################-----------------------------------*/
    @GetMapping("/createCookie")
    public String createCookieTest(HttpServletResponse response) {
        String name = "myCookie";
        String value = "myCookieValue";
        Cookie cookie = new Cookie(name, value);
//        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return cookie.toString();
    }

    @GetMapping("/queryCookie")
    public String queryCookieTest(@CookieValue("myCookie") Cookie cookie) {
        System.out.println(cookie.getDomain());
        System.out.println(cookie.isHttpOnly());
        return cookie.getValue();
    }



}
