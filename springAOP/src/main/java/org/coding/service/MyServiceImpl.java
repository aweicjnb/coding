package org.coding.service;


import org.springframework.stereotype.Component;

@Component
public class MyServiceImpl implements MyService {
    @Override
    public void m() {
        System.out.println("MyService m()");
    }
}
