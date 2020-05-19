package com.vidor.Config;

import javafx.beans.binding.Bindings;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadQueueConfig {
    @Bean
    public Queue TicketQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange","TicketExchange");
        args.put("x-dead-letter-routing-key","ticketing_dead");
        return new Queue("TicketQueue",true,false,false,args);
    }

    @Bean
    public Queue DeadQueue(){
        return new Queue("DeadQueue",true,false,false);
    }

    @Bean
    public Exchange TicketExchange() {
        return new TopicExchange("TicketExchange",true,false);
    }

    @Bean
    public Binding bindingTicket(){
        return BindingBuilder.bind(TicketQueue()).to(TicketExchange()).with("ticketing").noargs();
    }

    @Bean
    public Binding bindingDead(){
        return BindingBuilder.bind(DeadQueue()).to(TicketExchange()).with("ticketing_dead").noargs();
    }
}
