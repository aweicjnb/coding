package org.coding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyServiceB {
    MyService myService;
    public MyServiceB(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/aspectTest")
    public String aspectApi() {
        myService.m();
        return "ok";
    }
}
