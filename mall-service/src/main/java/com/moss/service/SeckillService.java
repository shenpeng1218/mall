package com.moss.service;

import com.moss.bean.MallOrder;
import com.moss.bean.MallUser;
import com.moss.vo.GoodsVo;

public interface SeckillService {

    public MallOrder buy(MallUser user, GoodsVo goods);
}
