package com.moss.impl;

import com.moss.bean.MallOrder;
import com.moss.bean.MallSeckillGoods;
import com.moss.bean.MallUser;
import com.moss.service.MallGoodsService;
import com.moss.service.MallOrderService;
import com.moss.service.SeckillService;
import com.moss.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeckillServiceImpl implements SeckillService{

    @Autowired
    MallGoodsService goodsService;

    @Autowired
    MallOrderService orderService;

    @Override
    @Transactional
    public MallOrder buy(MallUser user, GoodsVo goodsVo) {
        //减库存
        MallSeckillGoods seckillGoods = new MallSeckillGoods();
        seckillGoods.setGoodsId(goodsVo.getId());
        goodsService.reduceStock(seckillGoods);

        //下订单，添加秒杀订单
        MallOrder order = orderService.createOrder(user, goodsVo);
        return order;
    }
}
