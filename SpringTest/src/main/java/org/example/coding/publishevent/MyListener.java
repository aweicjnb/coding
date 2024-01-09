//package org.example.coding.publishevent;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class MyListener implements ApplicationListener<MyEvent> {
//
//    /**
//     * 处理监听到的事件
//     * @param event 事件
//     */
//    @Override
//    public void onApplicationEvent(MyEvent event) {
//        try {
//            event.whatHappend();
//            //由于监听到某某事件，所以我我要怎么这么样
//            System.out.println("----------------");
//            Thread.sleep(1000*3);
//            System.out.println("我要回家吃法了");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//}
