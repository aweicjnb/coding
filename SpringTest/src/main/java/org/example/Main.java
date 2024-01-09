package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Iterator;

@SpringBootApplication
public class Main {
    public static void main(String[] aaa) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, aaa);
        Iterator<String> beanNamesIterator = run.getBeanFactory().getBeanNamesIterator();
        while (beanNamesIterator.hasNext()) {
            System.out.println(beanNamesIterator.next());
        }

    }

}