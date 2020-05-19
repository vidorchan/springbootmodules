package com.vidor.Controller;

import com.vidor.Sevice.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v2")
public class SendMessageControllerOps {

    @Autowired
    private SendMessageService sendMessageService;

    @PostMapping("/send")
    public void sendMsg(@RequestBody String message){
        sendMessageService.send("TestDirectExchange","TestDirectRouting", message);
    }

    @PostMapping("/send1")
    public void sendMsg1(@RequestBody String message){
        sendMessageService.send("TestTopicExchange","Topic.One", message);
    }

    @PostMapping("/send2")
    public void sendMsg2(@RequestBody String message){
        sendMessageService.send("TestTopicExchange","Topic.Two", message);
    }

    @PostMapping("/testing1")
    //测试交换机不可达confirmCallBack
    public void testingConfirmCallback(@RequestBody String message){
        sendMessageService.send("TestExchangeTesting","testing", message);
    }

    @PostMapping("/testing2")
    public void testingReturnCallback(@RequestBody String message){
        sendMessageService.send("TestQueueTesting","testing", message);
    }

    @PostMapping("/ticket")
    public void sendTicket(@RequestBody String message){
        sendMessageService.sendTicket("TicketExchange","ticketing", message);
    }

}