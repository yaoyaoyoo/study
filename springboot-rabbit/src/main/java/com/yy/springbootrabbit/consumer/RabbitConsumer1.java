package com.yy.springbootrabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//声明此类为spring管理，在容器启动时进行类的加载初始化
@Component
//声明类为rabbit队列的监听者，并指定监听队列
@RabbitListener(queues = "springboot_queue1")
public class RabbitConsumer1 {

    //指定消息接收到的处理方法
    @RabbitHandler(isDefault = true)
    public void handle(String msg) {
        System.out.println("queue1 received msg: " + msg);
    }

}
