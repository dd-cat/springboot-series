package com.example.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 通用发送消息
     *
     * @param exchange   交换机
     * @param routingKey 路由key
     * @param msg        消息
     */
    public void sendMessage(String exchange, String routingKey, Object msg) {

        // 确保消息发送失败后可以重新返回到队列中 注意：yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);

        // 消费者确认收到消息后，手动ack回执回调处理
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("消息发送异常!");
            } else {
                log.info("发送者已经收到确认，correlationData={} ,ack={}, cause={}", correlationData.getId(), ack, cause);
            }
        });

        // 消息投递到队列失败回调处理
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchanges, routingKeys) -> {
            log.info("returnedMessage ===> replyCode={} ,replyText={} ,exchange={} ,routingKey={}", replyCode, replyText, exchanges, routingKeys);
        });

        // 发送消息
        rabbitTemplate.convertAndSend(exchange, routingKey, msg,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
                    message.getMessageProperties().setExpiration(1000 * 60 + "");
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));
    }

}
