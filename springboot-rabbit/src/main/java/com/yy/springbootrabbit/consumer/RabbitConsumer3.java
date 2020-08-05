package com.yy.springbootrabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "springboot_queue3")
public class RabbitConsumer3 {
    @RabbitHandler
    public void handle(String msg) {
        System.out.println("queue3 received msg :" + msg);
    }

}
