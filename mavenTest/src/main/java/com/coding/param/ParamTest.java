package com.coding.param;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParamTest {

    @PostMapping("/test/test")
    public String test(@RequestParam String id) {
        System.out.println("-----");
        System.out.println(id);
        System.out.println("----");
        return id;
    }
}
