package com.yy.rabbitmq.quickstart.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.yy.rabbitmq.quickstart.utils.RabbitConfirmListener;
import com.yy.rabbitmq.quickstart.utils.RabbitReturnListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

        String exchangeName = "exchange_yy";
        String exchangeType = "direct";
        String queueName = "queue_yy";
        String routeKey = "route.yy";

        testDLX(channel, exchangeName, queueName, routeKey);
        //交换机和队列可以在消费端或生产端进行声明，绑定。
        //交换机是否持久化，持久化才会存磁盘，否则重启丢失
        boolean isDurable = true;
        //是否自动删除，如果配置成是，当最少一个消费端进行绑定连接后，消费端全部断连后会自动删除
        boolean isAutoDel = false;
        //创建交换机
//        channel.exchangeDeclare(exchangeName, exchangeType, isDurable, isAutoDel, null);
        //创建队列
        //队列是否为独占模式， 独占模式下只有一个连接可以连接
        boolean isExclusive = false;

//        channel.queueDeclare(queueName, isDurable, isExclusive, isAutoDel, null);
        //根据路由key，将队列与交换机进行绑定
//        channel.queueBind(queueName, exchangeName , routeKey);

//        testBasciDirectExchange(channel, exchangeName, routeKey);
//        testMsgReturn(channel, exchangeName);
//        testConfirm(channel, exchangeName);
//        testQoS(channel, exchangeName, routeKey);

        //关闭连接
//        channel.close();
//        connection.close();
    }

    /**
     * 基本的直连交换机测试
     * @param channel  channel
     * @param exchangeName exchangeName
     * @param routeKey routeKey
     * @throws IOException e
     */
    private static void testBasicDirectExchange(Channel channel, String exchangeName,
            String routeKey) throws IOException {

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
    }


    /**
     * 基本的直连交换机测试
     * @param channel channel
     * @throws IOException e
     */
    private static void testMsgReturn(Channel channel, String exchangeName) throws IOException {
        String routeKey = "route.yy";
        //添加returnListener  使用ReturnCallback配合lambda效果更佳
        channel.addReturnListener(new RabbitReturnListener());


        channel.confirmSelect();
        channel.addConfirmListener(new RabbitConfirmListener());

        channel.basicPublish(exchangeName, routeKey, false, null, "正确的路由".getBytes());

        //消息传递使用错误的routeKey 不托管消息，路由失败后自动被删除 交换机正确此消息会触发confirmListener
        channel.basicPublish(exchangeName, "123.456", false, null,
                "错误的路由,mandatory = false".getBytes());

        //消息传递使用错误的routeKey 托管消息，路由失败后触发ReturnListener 此消息也会触发confirmListener
        channel.basicPublish(exchangeName, "123.456", true, null,
                "错误的路由,mandatory = true".getBytes());


        //消息传递使用错误的交换机 托管消息，并未触发ReturnListener 此消息也不会触发confirmListener
        channel.basicPublish("1asda", routeKey, true, null,
                "错误的交换机, mandatory = true".getBytes());
    }

    private static void testConfirm(Channel channel, String exchangeName) throws IOException {
        //Enables publisher acknowledgements on this channel.在本channel开启消息发布确认
        channel.confirmSelect();
        channel.addConfirmListener(new RabbitConfirmListener());
        String routeKey = "route.yy";
        channel.basicPublish(exchangeName, routeKey, false, null, "ACK 测试".getBytes());

    }

    private static void testQoS(Channel channel, String exchangeName, String routeKey) throws IOException {
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(exchangeName, routeKey, false, null, ("QoS测试消息" + i).getBytes());
        }
    }

    /**
     * 消息被拒绝：（basic.reject/basic.nack）并且requeue(重回队列)的属性设置为 false 表示不需要重回队列，那么该消息就是一个死信消息
     * 消息TTL过期
     * 消息本身设置了过期时间，或者队列设置了消息过期时间x-message-ttl
     * 队列达到最大长度:比如队列最大长度是3000 ,那么3001消息就会被送到死信队列上.
     * @param channel
     * @param exchangeName
     * @param queueName queueName
     * @throws IOException
     */
    private static void testDLX(Channel channel, String exchangeName, String queueName, String routeKey)
            throws IOException {
        //创建死信交换机 可以考虑使用扇形交换机fanout
        String deadMsgExchangeName = "exchange.yy.deadmsg";
        String deadMsgQueueName = "queue.yy.deadmsg";

        channel.exchangeDeclare(deadMsgExchangeName, "fanout", true, false, null);
        //创建死信队列
        channel.queueDeclare(deadMsgQueueName, true, false, false, null);
        //绑定 所有死信交换机的消息传到死信队列，
        channel.queueBind(deadMsgQueueName, deadMsgExchangeName, "");


        //RabbitMq不支持修改已经存在的队列和交换机参数，连接时会检查，若是此次声明的参数与已存在的不一致，会抛出shutdown异常
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        Map<String,Object> argurments= new HashMap<>() ;
        //设置正常队列中的死信发往哪个队列
        argurments.put("x-dead-letter-exchange", deadMsgExchangeName);
        //设置队列消息过期时间
        argurments.put("x-message-ttl", 20000);
        //设置队列最大长度
        argurments.put("x-max-length", 5);

        //创建队列指定死信队列
        channel.queueDeclare(queueName, true, false, false, argurments);
        channel.queueBind(queueName, exchangeName, routeKey);
//        for (int i = 0; i < 10; i++) {
//            channel.basicPublish(exchangeName, routeKey, true, null, ("死信测试" + i).getBytes());
//        }
//        for (int i = 0; i < 5; i++) {
//            channel.basicPublish(exchangeName, routeKey, true,
//                    new AMQP.BasicProperties.Builder().expiration("1000").build(), ("消息过期死信测试" + i).getBytes());
//        }
        for (int i = 0; i < 3; i++) {
            //不管mandatory设置是否为true 被拒绝的消息都会进入死信队列
            channel.basicPublish(exchangeName, routeKey, true, null,
                    ("消息Nack、reject死信测试" + i).getBytes());
        }
    }
}
