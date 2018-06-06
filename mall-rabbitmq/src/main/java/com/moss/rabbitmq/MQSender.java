package com.moss.rabbitmq;

import com.moss.redis.RedisService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    RedisService redisService;

    public void send(Object message){
        String msg = redisService.beanToString(message);
        System.out.println("send message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, message);
    }

    public void sendTopic(Object message){
        String msg = redisService.beanToString(message);
        System.out.println("send topic message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.master", msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.masterslave", msg);
    }

    public void sendFanout(Object message){
        String msg = redisService.beanToString(message);
        System.out.println("send fanout message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);
    }

    public void sendHeaders(Object message){
        String msg = redisService.beanToString(message);
        System.out.println("send headers message:" + msg);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("key1", "value1");
        properties.setHeader("key2", "value2");
        Message obj = new Message(msg.getBytes(), properties);

        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
    }
}
