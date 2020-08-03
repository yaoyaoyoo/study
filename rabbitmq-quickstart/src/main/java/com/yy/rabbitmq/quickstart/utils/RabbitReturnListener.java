package com.yy.rabbitmq.quickstart.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;

/**
 * @author yy
 * @create 2020/8/3 15:33
 * @desc RabbitReturnListener
 */
public class RabbitReturnListener implements ReturnListener {

    @Override public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
            AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("接收到return的消息");
        System.out.println("replyCode " + replyCode);
        System.out.println("replyText " + replyText);
        System.out.println("exchange " + exchange);
        System.out.println("routingKey " + routingKey);
        System.out.println("properties " + properties);
        System.out.println("bodyStr " + new String(body));

    }
}
