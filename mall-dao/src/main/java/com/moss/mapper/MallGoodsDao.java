package com.moss.mapper;

import com.moss.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallGoodsDao {

    @Select("select b.*, a.stock_count stockCount, a.start_date startDate, a.end_date endDate, a.seckill_price seckillPrice " +
            "from mall_seckill_goods a left join mall_goods b on a.goods_id = b.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select b.*, a.stock_count stockCount, a.start_date startDate, a.end_date endDate, a.seckill_price seckillPrice " +
            "from mall_seckill_goods a left join mall_goods b on a.goods_id = b.id where b.id = #{id}")
    public GoodsVo getById(long id);
}
