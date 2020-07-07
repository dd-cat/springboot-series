package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_A = "exchange-A";
    public static final String QUEUE_A = "queue-a";
    public static final String ROUTINGKEY_A = "routing-key-A";



    /**
     * 设置交换机
     */
    @Bean
    public DirectExchange exchangeA() {
        return new DirectExchange(EXCHANGE_A);
    }


    /**
     * 设置队列
     */
    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true);
    }

    /**
     * 绑定
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(exchangeA()).with(ROUTINGKEY_A);
    }


    @Bean
    @Scope("prototype")//通知Spring把被注解的Bean变成多例 表示每次获得bean都会生成一个新的对象
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        return template;
    }

}