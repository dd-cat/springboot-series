package com.example.rabbitmq.consumer;

import com.example.rabbitmq.domain.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReceiverMessage {

    @RabbitListener(queues = "user.order.queue")
    public void processHandler2(Order order, Message message, Channel channel) throws Exception {
        log.info("消费者A收到消息：{}", order.toString());
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

}
