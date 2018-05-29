package com.moss.controller;

import com.moss.bean.MallGoods;
import com.moss.bean.MallOrder;
import com.moss.bean.MallSeckillOrder;
import com.moss.bean.MallUser;
import com.moss.result.CodeMessage;
import com.moss.service.MallGoodsService;
import com.moss.service.MallOrderService;
import com.moss.service.SeckillService;
import com.moss.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SeckillController {

    @Autowired
    private MallGoodsService goodsService;

    @Autowired
    private MallOrderService orderService;

    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/seckill")
    public String seckill(Model model, MallUser user, long goodsId){
        if(user == null){
            return "login";
        }

        //判断库存
        GoodsVo goodsVo = goodsService.getById(goodsId);
        if(goodsVo.getGoodsStock() < 0){
            model.addAttribute("errorMsg", CodeMessage.STOCK_EMPTY.getMessage());
            return "seckillFailure";
        }

        //判断是否已经秒杀到了
        MallSeckillOrder seckillOrder = orderService.getSeckillOrderByUserIdAndGoodsId(user.getId(), goodsId);
        if (seckillOrder != null){
            model.addAttribute("errorMsg", CodeMessage.CAN_NOT_REBUY.getMessage());
            return "seckillFailure";
        }

        //减库存 下订单 添加秒杀订单
        MallOrder order = seckillService.buy(user, goodsVo);
        model.addAttribute("order", order);
        return "orderDetail";
    }
}
