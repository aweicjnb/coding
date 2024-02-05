package org.coding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class MyServiceImpl2 {
    @Resource
    MyService myService;
    public MyServiceImpl2(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/aspectTest")
    public String aspectApi() {
        myService.m();
        return "ok";
    }
}
