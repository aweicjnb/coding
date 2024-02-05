package com.coding.common_http;


import com.coding.common_http.from.RequestFrom;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;


@RestController
public class RequestBodyTest {
    @PostMapping("validatedBody/")
    public String validatedBody(@Validated @RequestBody RequestFrom request) {
        System.out.println(request);
        return request.toString();
    }

    @GetMapping("requestParamTest/")
    public String requestParamTest(
            @RequestParam(value = "param", required = false) String param
    ){
        return param;
    }

    @GetMapping("dateTest/")
    public String dateTest(@RequestParam("date") Date date) {
        System.out.println(date);
        System.out.println(date.getTime());
        return date.toString();
    }
}
