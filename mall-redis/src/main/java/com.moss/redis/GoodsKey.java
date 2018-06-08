package com.moss.redis;

public class GoodsKey extends BasePrefix{

    public GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60, "goodsListKey");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "goodsDetailKey");
    public static GoodsKey getSeckillGoodsStock = new GoodsKey(0, "seckillGoodsStock");
}
