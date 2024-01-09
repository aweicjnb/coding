package com.coding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("");
        classPathXmlApplicationContext.getBean("bean");




        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.getBean("");

        SpringApplication.run(App.class, args);
        System.out.println( "Hello World!" );
    }
}
