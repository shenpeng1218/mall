package com.moss.controller;

import com.moss.bean.MallUser;
import com.moss.exception.SeckillStatus;
import com.moss.service.MallGoodsService;
import com.moss.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private MallGoodsService goodsService;

    @RequestMapping("/list")
    public String goodsList(Model model, MallUser mallUser){
        model.addAttribute("user", mallUser);

        List<GoodsVo> goodsVos = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsVos);

        return "goodsList";
    }

    @RequestMapping("/{goodsId}")
    public String goodsDetail(Model model, MallUser mallUser, @PathVariable long goodsId){
        model.addAttribute("user", mallUser);

        GoodsVo goods = goodsService.getById(goodsId);
        model.addAttribute("goods", goods);

        long startDate = goods.getStartDate().getTime();
        long endDate = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        if(now < startDate){//未开始
            model.addAttribute("seckillStatus", SeckillStatus.NOT_STARTED.getStatusDesc());
        }else if(startDate <= now && now <= endDate){//进行中
            model.addAttribute("seckillStatus", SeckillStatus.ON_GOING.getStatusDesc());
        }else{//已结束
            model.addAttribute("seckillStatus", SeckillStatus.COMPLETED.getStatusDesc());
        }

        return "goodsDetail";
    }
}
