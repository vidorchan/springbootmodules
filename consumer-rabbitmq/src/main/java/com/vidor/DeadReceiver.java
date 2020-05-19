package com.vidor;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "DeadQueue")
public class DeadReceiver {

    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws IOException {
        System.out.println("在死信队列处理："+message);
        try {
           int i = 1/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (Exception e) {
            System.out.println("处理失败,重新加入出票队列："+message);
            channel.basicPublish("TicketExchange","ticketing",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getMessageProperties().getCorrelationId().getBytes());
        }
    }
}