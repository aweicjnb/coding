//package org.example.coding.publishevent;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.event.ApplicationContextEvent;
//
//public class MyEvent extends ApplicationContextEvent {
//
//    private Payload payload;
//
//    public MyEvent(ApplicationContext source, Payload payload) {
//        super(source);
//        this.payload = payload;
//
//    }
//
//    public void whatHappend() throws InterruptedException {
//        System.out.println("事件发生，具体事件：" + payload.info);
//        Thread.sleep(1000* 3);
//    }
//
//
//}
