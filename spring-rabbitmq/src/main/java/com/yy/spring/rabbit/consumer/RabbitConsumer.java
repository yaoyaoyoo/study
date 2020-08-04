package com.yy.spring.rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yy
 * @create 2020/8/4 23:32
 * @desc RabbitConsumer
 */
public class RabbitConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

    @Autowired RabbitTemplate rabbitTemplate;

    public void onMessage(Message message) {
        logger.info("Received message : " + message);
    }
}
