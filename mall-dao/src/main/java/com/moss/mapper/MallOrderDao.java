package com.moss.mapper;

import com.moss.bean.MallOrder;
import com.moss.bean.MallSeckillOrder;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MallOrderDao {

    @Select("select * from mall_seckill_order where user_id=#{userId} and goods_id=#{goodsId}")
    public MallSeckillOrder getSeckillOrderByUserIdAndGoodsId(@Param("userId") long userId, @Param("goodsId")long goodsId);

    @Insert("insert into mall_order (user_id, goods_id, delivery_addr_id, goods_name, goods_count, goods_price, order_channel, status, create_date)" +
            "values (#{userId}, #{goodsId}, #{deliveryAddrId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    public long insertOrder(MallOrder order);

    @Insert("insert into mall_seckill_order (user_id, goods_id, order_id)" +
            "values (#{userId}, #{goodsId}, #{orderId})")
    public long insertSeckillOrder(MallSeckillOrder seckillOrder);
}
