package com.yy.springbootrabbit.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        rabbitTemplate.convertAndSend("springboot_direct_exchange",
                "springboot_queue1", "direct msg test");
        rabbitTemplate.convertAndSend("springboot_topic_exchange",
                "springboot_queue2.queue2.test", "topic msg test1");
        rabbitTemplate.convertAndSend("springboot_topic_exchange",
                "springboot_queue2.queue2.test.test", "topic msg test2");
        rabbitTemplate.convertAndSend("springboot_fanout_exchange", "",
                "fanout msg test");
    }
}
