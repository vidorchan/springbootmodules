package com.vidor.Controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public void sendMsg(@RequestBody String message){
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting", message);
    }

    @PostMapping("/send1")
    public void sendMsg1(@RequestBody String message){
        rabbitTemplate.convertAndSend("TestTopicExchange","Topic.One", message);
    }

    @PostMapping("/send2")
    public void sendMsg2(@RequestBody String message){
        rabbitTemplate.convertAndSend("TestTopicExchange","Topic.Two", message);
    }

    @PostMapping("/testing1")
    //测试交换机不可达confirmCallBack
    public void testingConfirmCallback(@RequestBody String message){
        rabbitTemplate.convertAndSend("TestExchangeTesting","testing", message);
    }

    @PostMapping("/testing2")
    public void testingReturnCallback(@RequestBody String message){
        rabbitTemplate.convertAndSend("TestQueueTesting","testing", message);
    }
}