package com.example.rabbitmq.controller;

import com.example.rabbitmq.config.DelayRabbitConfig;
import com.example.rabbitmq.domain.Order;
import com.example.rabbitmq.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);

        producerService.sendMessage("topicExchange", "topic.man", manMap);
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);

        producerService.sendMessage("topicExchange", "topic.woman", womanMap);
        return "ok";
    }
}
