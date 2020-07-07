package com.example.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

//import org.springframework.messaging.Message;

@Component
public class ReceiverMessage {
    private static final Logger log = LoggerFactory.getLogger(ReceiverMessage.class);

    @RabbitListener(queues = "queue-a")
    public void processHandler(Message message, Channel channel) throws Exception {
        log.info("消费者A收到消息：{}", message.toString());
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


    //是否重复消费失败
    private void isFlag(MessageHeaders headers, Channel channel, Long tag) throws IOException {

    }

}
