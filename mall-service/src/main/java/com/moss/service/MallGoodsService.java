package com.moss.service;

import com.moss.bean.MallSeckillGoods;
import com.moss.vo.GoodsVo;

import java.util.List;

public interface MallGoodsService {

    public List<GoodsVo> listGoodsVo();

    public GoodsVo getById(long id);

    public int reduceStock(MallSeckillGoods seckillGoods);
}
