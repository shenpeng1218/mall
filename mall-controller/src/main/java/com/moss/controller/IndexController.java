package com.moss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shenpeng on 2018/5/8.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("test", "ok");
        return "index";
    }
}
