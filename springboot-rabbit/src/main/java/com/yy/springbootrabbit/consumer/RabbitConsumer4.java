package com.yy.springbootrabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "springboot_queue4")
public class RabbitConsumer4 {
    @RabbitHandler
    public void handle(String msg) {
        System.out.println("queue4 received msg :" + msg);
    }

}
