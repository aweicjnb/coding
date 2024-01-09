package com.coding.test2;

public class A {
    public void m() {}
    protected void m2() {}
    void m3() {}
    private void m4() {}
}

class C {
    public static void main(String[] args) {
        new A();
    }
}
