package org.coding.common;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "app3")
public interface Service {
    @GetMapping("app3/getUser")
     public String getUser();

}
