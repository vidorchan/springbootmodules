package com.vidor.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicsRabbitConfig {

    @Bean
    public Queue TestTopicsQueue1(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("TestTopicsQueue1",true,false,false);
    }

    @Bean
    public Queue TestTopicsQueue2(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("TestTopicsQueue2",true,false,false);
    }

    @Bean
    public Exchange TestTopicExchange(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new TopicExchange("TestTopicExchange",true,false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：#一个或多个;*一个
    @Bean
    Binding bindingTopics1(){
        return BindingBuilder.bind(TestTopicsQueue1()).to(TestTopicExchange()).with("Topic.One").noargs();
    }

    @Bean
    Binding bindingTopics2(){
        return BindingBuilder.bind(TestTopicsQueue2()).to(TestTopicExchange()).with("Topic.#").noargs();
    }
}
