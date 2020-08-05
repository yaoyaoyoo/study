package com.yy.springbootrabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    //声明交换机

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("springboot_direct_exchange", true, false);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("springboot_fanout_exchange", true, false);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("springboot_topic_exchange", true, false);
    }

    //声明队列

    @Bean
    public Queue queue1() {
        return new Queue("springboot_queue1", true, false, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue("springboot_queue2", true, false, false);
    }

    @Bean
    public Queue queue3() {
        return new Queue("springboot_queue3", true, false, false);
    }

    @Bean
    public Queue queue4() {
        return new Queue("springboot_queue4", true, false, false);
    }

    //声明绑定关系

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(queue1()).to(directExchange()).with("springboot_queue1");
    }

    //这里注入要么名字和定义的bean一致，要么指定的参数类型对应的bean只有一个
//    @Bean public Binding directBinding2(Queue queue1, DirectExchange exchange) {
//        return BindingBuilder.bind(queue1).to(exchange).with("springboot_queue1");
//    }

    //多个实现时可以通过@Qualifier指定要注入的bean
//    @Bean public Binding directBinding3(@Qualifier("queue1") Queue queue,
//            @Qualifier("directExchange") DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("springboot_queue1");
//    }

    @Bean
    public Binding topicBinding(Queue queue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue2).to(topicExchange).with("springboot_queue2.#");
    }

    //扇形交换机绑定两个队列，广播消息
    @Bean
    public Binding fanoutBinding() {
        //默认单例的形式下，这里方法获取的应该是同一个bean
        return BindingBuilder.bind(queue3()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(queue4()).to(fanoutExchange());
    }

}
