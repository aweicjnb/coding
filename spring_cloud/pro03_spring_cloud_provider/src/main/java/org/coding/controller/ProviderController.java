package org.coding.controller;


import org.coding.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/app3")
public class ProviderController {

    @GetMapping("/getUser")
    public String getUser() {
        User user = new User();
        user.setAge(22);
        user.setName("哈哈😄");
        return user.toString();
    }


}
