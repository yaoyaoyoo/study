package com.yy.rabbitmq.quickstart.utils;

import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;

/**
 * @author yy
 * @create 2020/8/3 15:52
 * @desc 消息确认监听器
 */
public class RabbitConfirmListener implements ConfirmListener {

    @Override public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("HandleACK: deliveryTag = " + deliveryTag + " multiple = " + multiple);
    }

    @Override public void handleNack(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("HandleNaCK: deliveryTag = " + deliveryTag + " multiple = " + multiple);

    }
}
