package com.moss.rabbitmq;

import com.moss.bean.MallSeckillGoods;
import com.moss.bean.MallUser;
import com.moss.redis.RedisService;
import com.moss.service.MallGoodsService;
import com.moss.service.SeckillService;
import com.moss.vo.GoodsVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private MallGoodsService goodsService;

    @RabbitListener(queues = MQConfig.SECKILL_QUEUE)
    public void receiveSeckillMessage(String message){
        //出队的时候减少库存，并生成订单
        SeckillMessage seckillMessage = RedisService.stringToBean(message, SeckillMessage.class);
        GoodsVo goodsVo = goodsService.getById(seckillMessage.getGoodsId());
        seckillService.buy(seckillMessage.getUser(), goodsVo);
        System.out.println("receive message:" + message);
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(String message){
        System.out.println("receive message:" + message);
    }

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
