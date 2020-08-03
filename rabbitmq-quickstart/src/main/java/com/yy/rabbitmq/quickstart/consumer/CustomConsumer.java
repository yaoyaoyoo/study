package com.yy.rabbitmq.quickstart.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author yy
 * @create 2020/8/3 16:37
 * @desc 自定义消费端 继承DefaultConsumer
 */
public class CustomConsumer extends DefaultConsumer {

    public CustomConsumer(Channel channel) {
        super(channel);
    }

    @Override public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
            byte[] body) throws IOException {
        System.out.println("CustomConsumer ----消费消息：--------");
        System.out.println("consumerTag:" + consumerTag);
        System.out.println("envelope:" + envelope);
        System.out.println("properties:" + properties);
        System.out.println("body:" + new String(body));
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //消费端返回ack确认消息后，才会再次从队列中取消息
//        getChannel().basicAck(envelope.getDeliveryTag(), false);

//        //消息处理失败 发送Nack，设置重回队列之后，将会一直处理这条失败的消息，一般不要设置回队列
//        getChannel().basicNack(envelope.getDeliveryTag(), false, true);

        //死信队列测试，不重新入队列
//        getChannel().basicNack(envelope.getDeliveryTag(), false, false);
        getChannel().basicReject(envelope.getDeliveryTag(), false);
    }
}
