package org.coding.annoattion;

@MyAnnotationTest("this is 注解value")
public class MyTest {
    public static void main(String[] args) {
        MyAnnotationTest annotation = MyTest.class.getDeclaredAnnotation(MyAnnotationTest.class);
        System.out.println(annotation.value());

    }
}
