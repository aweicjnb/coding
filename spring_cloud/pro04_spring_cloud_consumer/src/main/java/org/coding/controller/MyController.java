package org.coding.controller;

import org.coding.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController("/app4")
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getUser")
    public User employee(){
        // 1.远程调用方法的主机
        String host = "http://localhost:8001";
        // 2.远程调用方法的具体URL地址
        String url = "/app3/getUser";
        User forObject = restTemplate.getForObject(host + url, User.class);
        return forObject;
    }


}
