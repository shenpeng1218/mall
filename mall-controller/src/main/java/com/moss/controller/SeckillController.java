package com.moss.controller;

import com.moss.bean.MallSeckillOrder;
import com.moss.bean.MallUser;
import com.moss.rabbitmq.MQSender;
import com.moss.rabbitmq.SeckillMessage;
import com.moss.redis.GoodsKey;
import com.moss.redis.OrderKey;
import com.moss.redis.RedisService;
import com.moss.result.CodeMessage;
import com.moss.result.Result;
import com.moss.service.MallGoodsService;
import com.moss.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SeckillController implements InitializingBean{

    @Autowired
    private MallGoodsService goodsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MQSender mqSender;

    private Map<Long, Boolean> localMap = new HashMap<Long, Boolean>();

    @RequestMapping("/seckill")
    public String seckill(Model model, MallUser user, long goodsId){
        if(user == null){
            return "login";
        }

        //判断是否已经买过  虽然数据库做了唯一处理，但是这里判断一下减轻数据库的压力
        MallSeckillOrder seckillOrder =
                redisService.get(OrderKey.getSeckillOrderByUidGid, user.getId()+"_"+goodsId, MallSeckillOrder.class);
        if(seckillOrder != null){
            model.addAttribute("errorMsg", CodeMessage.CAN_NOT_REBUY.getMessage());
            return "seckillFailure";
        }

        boolean over = localMap.get(goodsId);//判断是否已卖光
        if(over){
            model.addAttribute("errorMsg", CodeMessage.STOCK_EMPTY.getMessage());
            return "seckillFailure";
        }

        //预减库存
        long stock = redisService.decr(GoodsKey.getSeckillGoodsStock, goodsId+"");
        if(stock < 0){
            model.addAttribute("errorMsg", CodeMessage.STOCK_EMPTY.getMessage());
            localMap.put(goodsId, true);
            return "seckillFailure";
        }

        //入队
        SeckillMessage seckillMessage = new SeckillMessage();
        seckillMessage.setUser(user);
        seckillMessage.setGoodsId(goodsId);
        mqSender.sendSeckillMessage(seckillMessage);

        model.addAttribute("goodsId", goodsId);
        return "seckillWaiting";

        /*GoodsVo goodsVo = goodsService.getById(goodsId);
        //减库存 下订单 添加秒杀订单
        MallOrder order = seckillService.buy(user, goodsVo);
        model.addAttribute("order", order);
        return "orderDetail";*/
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVos = goodsService.listGoodsVo();
        if(goodsVos == null){
            return;
        }
        for(GoodsVo goodsVo : goodsVos){
            redisService.set(GoodsKey.getSeckillGoodsStock, goodsVo.getId() + "", goodsVo.getStockCount());
            localMap.put(goodsVo.getId(), false);
        }
    }
}
