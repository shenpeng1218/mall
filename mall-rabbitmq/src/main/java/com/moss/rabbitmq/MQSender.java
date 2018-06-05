package com.moss.rabbitmq;

import com.moss.redis.RedisService;
import org.springframework.amqp.core.AmqpTemplate;
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
        System.out.println("send message:" + message);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, message);
    }
}
