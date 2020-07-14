package com.example.rabbitmq.controller;

import com.example.rabbitmq.config.DelayRabbitConfig;
import com.example.rabbitmq.domain.Order;
import com.example.rabbitmq.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RabbitMQController {
    private static final Logger log = LoggerFactory.getLogger(RabbitMQController.class);

    private static final String SUCCESS = "success";

    @Autowired
    private ProducerService producerService;

    /**
     * 单次发送
     */
    @GetMapping("sendOne")
    public String sendOne() {
        producerService.sendMessage("exchange-A", "routing-key-A", "hello 你好！！！");
        return SUCCESS;
    }

    /**
     * 循环发送
     */
    @GetMapping("send")
    public String send() {
        for (int i = 1; i <= 10; i++) {
            producerService.sendMessage("exchange-A", "routing-key-A", "这是发送的第" + i + "条消息");
        }
        return SUCCESS;
    }

    /**
     * 延迟队列发送
     */
    @GetMapping("sendDelay")
    public String sendDelay() {
        Order order = new Order();
        order.setOrderId("123456");
        order.setOrderName("一加9");
        order.setOrderStatus(1);
        log.info("【订单生成时间】" + new Date().toString() + "【1分钟后检查订单是否已经支付】" + order.toString());
        producerService.sendMessage(DelayRabbitConfig.ORDER_DELAY_EXCHANGE, DelayRabbitConfig.ORDER_DELAY_ROUTING_KEY, order);
        return SUCCESS;
    }
}
