package com.moss.rabbitmq;

import com.moss.bean.MallUser;

public class SeckillMessage {

    private MallUser user;

    private long goodsId;

    public MallUser getUser() {
        return user;
    }

    public void setUser(MallUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
