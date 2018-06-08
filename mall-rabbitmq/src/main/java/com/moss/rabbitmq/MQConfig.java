package com.moss.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MQConfig {

    public static final String QUEUE = "queue";

    public static final String TOPIC_QUEUE_MASTER = "topic.queueMaster";
    public static final String TOPIC_QUEUE_SLAVE = "topic.queueSlave";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String HEADERS_EXCHANGE = "headersExchange";
    public static final String HEADERS_QUEUE = "headers.queue";

    public static final String SECKILL_QUEUE = "seckill.queue";

    /**
     * rabbitmq direct模式 最简单的模式
     */
    @Bean
    public Queue queue(){
        return new Queue(QUEUE, true);
    }

    /**
     * rabbitmq topic模式
     */
    @Bean
    public Queue topicQueueMaster(){
        return new Queue(TOPIC_QUEUE_MASTER, true);
    }

    @Bean
    public Queue topicQueueSlave(){
        return new Queue(TOPIC_QUEUE_SLAVE, true);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingMasterTopic(){
        return BindingBuilder.bind(topicQueueMaster()).to(topicExchange()).with("topic.master");
    }

    //#表示通配符0到多个单词 *表示一个单词
    @Bean
    public Binding bindingSlaveTopic(){
        return BindingBuilder.bind(topicQueueSlave()).to(topicExchange()).with("topic.#");
    }

    /**
     * Fanout模式
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindingFanout1(){
        return BindingBuilder.bind(topicQueueMaster()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanout2(){
        return BindingBuilder.bind(topicQueueSlave()).to(fanoutExchange());
    }

    /**
     * headers模式
     */
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public Queue headersQueue(){
        return new Queue(HEADERS_QUEUE, true);
    }

    @Bean
    public Binding headersBinding(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
    }

}
