package com.yy.rabbitmq.quickstart.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitProducer {

    //根据经验，在线程之间共享通道实例是要避免的。应用程序应该更喜欢为每个线程使用一个通道，而不是跨多个线程共享同一个通道。
    //可以使用通道池来避免在共享通道上并发发布:一旦一个线程处理完一个通道，它就将它返回到池，使该通道对另一个线程可用。
    //通道池可以看作是一种特定的同步解决方案。建议使用现有的池库，而不是自己开发的解决方案。例如，Spring AMQP就带有现成的通道池特性。
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
        String exchangeType = "direct";
        //交换机是否持久化，持久化才会存磁盘，否则重启丢失
        boolean isDurable = true;
        //是否自动删除，如果配置成是，当最少一个消费端进行绑定连接后，消费端全部断连后会自动删除
        boolean isAutoDel = false;

        //创建交换机
        channel.exchangeDeclare(exchangeName, exchangeType, isDurable, isAutoDel, null);

        //创建队列

        //队列是否为独占模式， 独占模式下只有一个连接可以连接
        boolean isExclusive = false;
        String queueName = "queue_yy";
        channel.queueDeclare(queueName, isDurable, isExclusive, isAutoDel, null);

        String routeKey = "route.yy";
        //根据路由key，将队列与交换机进行绑定
        channel.queueBind(queueName, exchangeName , routeKey);
//        channel.exchangeBind();  交换机之间也可以进行绑定

        //默认情况下,该参数为false.该参数与回调ReturnListener有关.
        //若是消息到达Exchange,但是Exchange没有与其他Queue绑定，若没配置Mandatory，消息丢弃，若是配置了则会执行回调
        boolean isMandatory = false;
//        AMQP.BasicProperties msgPros = new AMQP.BasicProperties.Builder()
//                .contentType("text/plain")
//                //消息类型 2 代表会持久化
//                .deliveryMode(2)
//                //消息过期时间
//                .expiration("")
//                .build();
        channel.basicPublish(exchangeName, routeKey, isMandatory, null, "Hello Rabbit".getBytes());

        //关闭连接
        channel.close();
        connection.close();
    }
}
