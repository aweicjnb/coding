package org.coding.proxy;

public class MyServiceImpl implements MyService{

    @Override
    public String doSome() {
        System.out.println("MyService demo doSome");
        return "ok";
    }
}
