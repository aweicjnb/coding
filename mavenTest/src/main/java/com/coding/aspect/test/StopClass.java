package com.coding.aspect.test;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Deprecated
public class StopClass {

    @GetMapping("/stop/m1")
    public void m1() {
        System.out.println("m1");
    }


    @GetMapping("/stop/m2")
    public static void m2() {
        System.out.println("m2");
    }

    @GetMapping("stop/m3/")
    public void m3() {
        System.out.println("m3");
    }
}
