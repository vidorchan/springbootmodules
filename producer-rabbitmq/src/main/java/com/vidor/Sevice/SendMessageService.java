package com.vidor.Sevice;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SendMessageService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Value("${ticketing.expiration}")
    private String TICKETING_EXPIRATION;

    private RabbitTemplate rabbitTemplate;

    public SendMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
    }

    public void send(String exchange, String routing, Object obejct) {
        rabbitTemplate.convertAndSend(exchange, routing, obejct);
    }

    public void sendTicket(String exchange, String routing, Object object) {
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            // 设置编码
            messageProperties.setContentEncoding("utf-8");
            // 设置过期时间10*1000毫秒
            messageProperties.setExpiration("10000");
            return message;
        };
        rabbitTemplate.convertAndSend(exchange, routing, object.toString(), messagePostProcessor);

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("ConfirmCallback1:     "+"相关数据："+correlationData);
        System.out.println("ConfirmCallback1:     "+"确认情况："+ack);
        System.out.println("ConfirmCallback1:     "+"原因："+cause);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("ReturnCallback1:     "+"消息："+message);
        System.out.println("ReturnCallback1:     "+"回应码："+replyCode);
        System.out.println("ReturnCallback1:     "+"回应信息："+replyText);
        System.out.println("ReturnCallback1:     "+"交换机："+exchange);
        System.out.println("ReturnCallback1:     "+"路由键："+routingKey);
    }
}
