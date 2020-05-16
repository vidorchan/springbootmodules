package com.vidor.Sevice;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SendMessageService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private RabbitTemplate rabbitTemplate;

    public SendMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
    }

    public void send(String exchange, String routing, Object obejct) {
        rabbitTemplate.convertAndSend(exchange, routing, obejct);
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
