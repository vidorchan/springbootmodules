package com.vidor;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@RabbitListener(queues = "TestTopicsQueue1")
public class Topic1Receiver {

//    @RabbitHandler
    public void process(String message){
        System.out.println("消费者消费消息TopicOnly1："+message);
    }
}
