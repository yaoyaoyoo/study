package com.yy.springbootrabbit;

import com.yy.springbootrabbit.producer.RabbitProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest class SpringbootRabbitApplicationTests {

    @Autowired private RabbitProducer rabbitProducer;
    @Test void contextLoads() {
        rabbitProducer.sendMsg();
    }
}
