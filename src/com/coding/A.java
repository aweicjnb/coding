package com.coding;

public class A<T> {
    public T t;

    public <T> T getT(T t) {
        System.out.println(t);
        return t;
    }

}

class B {
    public static void main(String[] args) {
        A<Integer> a = new A<>();
        System.out.println(a.getT(234));
    }
}
