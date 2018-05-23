package com.moss.controller;

import com.moss.bean.MallUser;
import com.moss.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private MallUserService mallUserService;

    @RequestMapping("/list")
    public String goodsList(Model model, MallUser mallUser){
        model.addAttribute("user", mallUser);
        return "goodsList";
    }
}
