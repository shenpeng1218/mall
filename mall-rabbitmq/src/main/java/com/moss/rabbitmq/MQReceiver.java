package com.moss.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

    /**
     * rabbitmq direct模式 最简单的模式
     * @param message
     */
    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(String message){
        System.out.println("receive message:" + message);
    }
}
