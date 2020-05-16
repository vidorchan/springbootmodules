package com.vidor.Config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.CorrelationData;


//@Configuration
public class RabbitConfig {

//    @Bean("rabbitTemplate2")
//    public RabbitTemplate rabbitTemplate2(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConnectionFactory(connectionFactory);
//
//        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
////        rabbitTemplate.setMandatory(true);
//
//        //Exchange不可达(correlationData=null,ack=false,cause=channel error; protocol method ...04...)
//        //消息发送成功(correlationData=null,ack=true,cause=null)
//        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
//            System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
//            System.out.println("ConfirmCallback:     "+"确认情况："+ack);
//            System.out.println("ConfirmCallback:     "+"原因："+cause);
//        });
//
//        //Queue不可达(message="消息")
//        // 此时也会调用confirmCallBack得到(correlationData=null,ack=true,cause=null)
//        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey) -> {
//            System.out.println("ReturnCallback:     "+"消息："+message);
//            System.out.println("ReturnCallback:     "+"回应码："+replyCode);
//            System.out.println("ReturnCallback:     "+"回应信息："+replyText);
//            System.out.println("ReturnCallback:     "+"交换机："+exchange);
//            System.out.println("ReturnCallback:     "+"路由键："+routingKey);
//        });
//
//        return rabbitTemplate;
//    }
}
