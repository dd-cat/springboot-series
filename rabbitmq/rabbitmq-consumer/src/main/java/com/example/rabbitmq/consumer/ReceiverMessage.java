package com.example.rabbitmq.consumer;

import com.example.rabbitmq.domain.Order;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

//import org.springframework.messaging.Message;

@Component
public class ReceiverMessage {
    private static final Logger log = LoggerFactory.getLogger(ReceiverMessage.class);

    @RabbitListener(queues = "queue-a")
    public void processHandler1(Message message, Channel channel) throws Exception {
        log.info("消费者A收到消息1：{}", message.toString());
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            //TODO 具体业务

            //手动确认消息
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("Exception：" + e.getMessage(), e);
            boolean flag = (boolean) headers.get(AmqpHeaders.REDELIVERED);
            if (flag) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicAck(tag, false);
            } else {
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(tag, false, true);
            }
        }
    }

    @RabbitListener(queues = "queue-a")
    public void processHandler2(Message message, Channel channel) throws Exception {
        log.info("消费者A收到消息2：{}", message.toString());
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            //TODO 具体业务

            //手动确认消息
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("Exception：" + e.getMessage(), e);
            boolean flag = (boolean) headers.get(AmqpHeaders.REDELIVERED);
            if (flag) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicAck(tag, false);
            } else {
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(tag, false, true);
            }
        }
    }


    @RabbitListener(queues = {"user.order.queue"})
    public void orderDelayQueue(Order order, Message message, Channel channel) throws IOException {
        log.info("###########################################");
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        log.info("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{}]- 【订单内容】 - [{}]", new Date(), order.toString());
        if (order.getOrderStatus() == 0) {
            order.setOrderStatus(2);
            log.info("【该订单未支付，取消订单】" + order.toString());
        } else if (order.getOrderStatus() == 1) {
            log.info("【该订单已完成支付】");
        } else if (order.getOrderStatus() == 2) {
            log.info("【该订单已取消】");
        }
        log.info("###########################################");
        channel.basicAck(tag, false);
    }

}
