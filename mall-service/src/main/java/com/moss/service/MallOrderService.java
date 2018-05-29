package com.moss.service;

import com.moss.bean.MallOrder;
import com.moss.bean.MallSeckillOrder;
import com.moss.bean.MallUser;
import com.moss.vo.GoodsVo;

public interface MallOrderService {

    public MallSeckillOrder getSeckillOrderByUserIdAndGoodsId(long userId, long goodsId);

    public MallOrder createOrder(MallUser user, GoodsVo goodsVo);
}
