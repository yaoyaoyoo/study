package com.yy.springbootrabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "springboot_queue2")
public class RabbitConsumer2 {

    @RabbitHandler
    public void handle(String msg) {
        System.out.println("queue2 received msg :" + msg);
    }

}
