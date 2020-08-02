package com.yy.rabbitmq.quickstart.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接属性

        //主机IP或域名
        connectionFactory.setHost("localhost");
        //Java客户端端口5672，页面管理端口15672
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("yy");
        connectionFactory.setPassword("yy");
        //使用的broker
        connectionFactory.setVirtualHost("vm_yy");
        connectionFactory.setConnectionTimeout(10000);

        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建channel
        Channel channel = connection.createChannel();

        //交换机和队列可以在消费端或生产端进行声明，绑定。
        String exchangeName = "exchange_yy";
        String queueName = "queue_yy";
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                System.out.println("消费消息" + new String(body));
            }
        };
        boolean isAutoAck = true;
        channel.basicConsume(queueName, true, consumer);
    }
}
