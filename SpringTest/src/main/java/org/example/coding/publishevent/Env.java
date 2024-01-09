//package org.example.coding.publishevent;
//
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//
//@RestController
//public class Env {
//    @Resource
//    public AbstractApplicationContext abstractApplicationContext;
//
//    @GetMapping("/pubEvent")
//    public String pub(Payload payload) {
//        MyEvent myEvent = new MyEvent(abstractApplicationContext, payload);
//        abstractApplicationContext.publishEvent(myEvent);
//        return payload.info;
//    }
//
//}
