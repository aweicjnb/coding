package org.coding.rabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static org.coding.rabbitMQ.ConnectionUtil.*;

public class MQServer {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                MQServer.server();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    public static void server() throws IOException, TimeoutException {
        //获取连接通道
        Channel channel = ConnectionUtil.createChannel();

        //设置队列
//        String MY_QUEUE_NAME = "my_queue_name3";
        //开启队列持久化(如果未开启，连接关闭，队列就没了)
        boolean durable = true;
        //队列名称，持久化，多个客户端共享，自动删除,其他参数
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);


        //消息发送确认（确保消息一定发送到消息队列，这个一般结合消息的持久化）
        channel.confirmSelect();
        //成功回调
        ConfirmCallback ackCallBack = (deliveryTag, multiple) -> {
            System.out.println("ack doSome");
        };
        //失败回调
        ConfirmCallback nackCallBack = (deliveryTag, multiple) -> {
            System.out.println("nack doSome");
        };
        channel.addConfirmListener(ackCallBack, nackCallBack);


        //设置不公平分发(允许改通道最大未确认消息总数)
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);


        //发送消息
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.next();
            //message消息持久化
            AMQP.BasicProperties properties = MessageProperties.PERSISTENT_TEXT_PLAIN;
            //交换机，路由key, 配置，消息体
//            channel.basicPublish("", MY_QUEUE_NAME, properties, message.getBytes());

            channel.basicPublish(EXCHANGE_NAME, routeKey, properties, message.getBytes());

            //确认消息被发送到消息队列(同步响应，效率不行🙅，还得是异步)
//            try {
//                boolean b = channel.waitForConfirms();
//                System.out.println(b);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }


    }
}
