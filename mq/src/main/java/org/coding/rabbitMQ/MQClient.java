package org.coding.rabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import static org.coding.rabbitMQ.ConnectionUtil.QUEUE_NAME;

public class MQClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                MQClient.client();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    public static void client() throws IOException, TimeoutException {
        Channel channel = ConnectionUtil.createChannel();

        ExecutorService executorService = Executors.newFixedThreadPool(5);


        //成功应答
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            //打印信息

            System.out.println("受到消息，正在处理。。。");

            try {
                System.out.println("IO start");
                Thread.sleep(1000*10);
                System.out.println("IO finish");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            System.out.println("消费成功");
            System.out.printf("客户端标识：%s%n", consumerTag);
            System.out.printf("消息唯一标识：%s%n", message.getEnvelope().getDeliveryTag());
            System.out.printf("消息：%s%n", new String(message.getBody()));
            System.out.println("###########################***############################");
            //自动应答
            channel.basicAck(message.getEnvelope().getDeliveryTag()/*消息的标记*/, false/*批量应答*/);
        };

        //失败应答
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消费中断");
            System.out.println(consumerTag);
            //如果消息处理失败就会执行这个失败回调函数 把消息重写放回到消息队列
            channel.basicRecover(true);
        };


        //是否自动应答
        Boolean autoAck = false;
        //消费队列，自动应答，成功回调，失败回调

        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, cancelCallback);
    }
}