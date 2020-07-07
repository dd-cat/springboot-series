package com.example.rabbitmq.controller;

import com.example.rabbitmq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {
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
}
