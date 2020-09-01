package com.example.rabbitmq.consumer;

import com.example.rabbitmq.domain.Order;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class ReceiverMessage {
    private static final Logger log = LoggerFactory.getLogger(ReceiverMessage.class);

    /*@RabbitListener(queues = "queue-a")
    public void processHandler1(String msg, Message message, Channel channel) throws Exception {
        log.info("消费者A收到消息：{}", msg);
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
    }*/


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

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = "queue-a")
    public void processHandler3(String msg, Message message, Channel channel) throws Exception {
        log.info("消费者A收到消息：{}", msg);
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            //TODO 具体业务

            //发送者 需发送一个唯一id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (redisTemplate.opsForHash().entries("test").containsKey(msgId)) {
                //redis 中包含该 key，说明该消息已经被消费过
                log.info(msgId + ":消息已经被消费");
                //确认消息已消费
                channel.basicAck(tag, false);
                return;
            }
            //添加到redis
            redisTemplate.opsForHash().put("test", msgId, "testDelay");

            //走到这里报错
            int i = 1 / 0;

            //手动确认消息
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("Exception：" + e.getMessage());
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
