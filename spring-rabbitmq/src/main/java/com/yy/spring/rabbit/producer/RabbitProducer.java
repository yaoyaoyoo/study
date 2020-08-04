package com.yy.spring.rabbit.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yy
 * @create 2020/8/4 23:32
 * @desc RabbitProducer
 */
public class RabbitProducer {
    private Logger logger = LoggerFactory.getLogger(RabbitProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Object message) {
        logger.info("SendMsg");
        rabbitTemplate.convertAndSend("spring", message);
    }
}
