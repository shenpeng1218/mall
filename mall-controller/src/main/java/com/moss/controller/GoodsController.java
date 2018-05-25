package com.moss.controller;

import com.moss.bean.MallUser;
import com.moss.service.MallGoodsService;
import com.moss.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
