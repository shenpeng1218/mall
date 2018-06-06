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

    /**
     * rabbitmq topic模式
     * @param message
     */
    @RabbitListener(queues = MQConfig.TOPIC_QUEUE_MASTER)
    public void receiveMasterTopic(String message){
        System.out.println("receive master message:" + message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE_SLAVE)
    public void receiveSlaveTopic(String message){
        System.out.println("receive slave message:" + message);
    }

    @RabbitListener(queues = MQConfig.HEADERS_QUEUE)
    public void receiveHeaders(byte[] message){
        System.out.println("receive headers message:" + new String(message));
    }
}
