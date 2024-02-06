package org.coding.rabbitMQ;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {
    static String EXCHANGE_NAME = "exchange_name1";
    static String routeKey = "quick.orange.rabbit";
    static String QUEUE_NAME = "my_queue_name";

    static String DEAD_EXCHANGE_NAME = "dead_exchange";
    static String DEAD_routeKey = "dead.quick.orange.rabbit";
    static String DEAD_QUEUE_NAME = "dead_my_queue_name";
    public static Channel createChannel() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

        Channel channel;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**
         * 生产者只是将消息发送到交换机，并指定routeKey,交换机根据routeKey和路由规则将message
         * 转发到不同到队列；routeKey和队列是一一对应的
         * 消费者只需要从其中某一个队列中取消息就可以
         *
         * 多个队列可能绑定的同一个交换机；
         * 所以是一个队列可能有多个消费者；
         * 但是没有一个消费者对应多个队列的情况;
         */

        try {
            //声明交换机
            //交换机名称，类型
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            //声明一个队列
            //这些参数还不知道什么含义(但是斗胆猜测类似于生产者发布消息时的参数是类似的)
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            //将队列和交换机绑定
            //队列，交换机，路由key
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, routeKey);


/*            //死信队列,交换机
            channel.exchangeDeclare(DEAD_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            channel.queueDeclare(DEAD_QUEUE_NAME, true, false, false, null);
            channel.queueBind(DEAD_QUEUE_NAME, DEAD_EXCHANGE_NAME, DEAD_routeKey);

            //普通队列，交换机
            Map<String, Object> param = new HashMap<>();
            param.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME);
            param.put("x-dead-letter-routing-key", DEAD_routeKey);
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            channel.queueDeclare(QUEUE_NAME, true, false, false, param);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, routeKey);*/

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return channel;
    }
}
