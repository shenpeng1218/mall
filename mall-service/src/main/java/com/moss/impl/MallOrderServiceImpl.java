package com.moss.impl;

import com.moss.bean.MallOrder;
import com.moss.bean.MallSeckillOrder;
import com.moss.bean.MallUser;
import com.moss.mapper.MallOrderDao;
import com.moss.redis.OrderKey;
import com.moss.redis.RedisService;
import com.moss.service.MallOrderService;
import com.moss.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MallOrderServiceImpl implements MallOrderService{

    @Autowired
    private MallOrderDao orderDao;

    @Autowired
    private RedisService redisService;

    @Override
    public MallSeckillOrder getSeckillOrderByUserIdAndGoodsId(long userId, long goodsId) {
        MallSeckillOrder seckillOrder =
                redisService.get(OrderKey.getSeckillOrderByUidGid, userId+"_"+goodsId, MallSeckillOrder.class);
        if(seckillOrder != null)
            return seckillOrder;
            return orderDao.getSeckillOrderByUserIdAndGoodsId(userId, goodsId);
    }

    @Override
    @Transactional
    public MallOrder createOrder(MallUser user, GoodsVo goodsVo) {
        MallOrder mallOrder = new MallOrder();
        mallOrder.setCreateDate(new Date());
        mallOrder.setDeliveryAddrId(0L);
        mallOrder.setGoodsCount(1);
        mallOrder.setGoodsId(goodsVo.getId());
        mallOrder.setGoodsName(goodsVo.getGoodsName());
        mallOrder.setGoodsPrice(goodsVo.getSeckillPrice());//下订单时的价格
        mallOrder.setOrderChannel(1);//最好用枚举
        mallOrder.setStatus(0);//最好用枚举
        mallOrder.setUserId(user.getId());
        orderDao.insertOrder(mallOrder);

        MallSeckillOrder seckillOrder = new MallSeckillOrder();
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrder.setOrderId(mallOrder.getId());
        seckillOrder.setUserId(user.getId());
        orderDao.insertSeckillOrder(seckillOrder);

        redisService.set(OrderKey.getSeckillOrderByUidGid, user.getId()+"_"+goodsVo.getId(), seckillOrder);

        return mallOrder;
    }
}
