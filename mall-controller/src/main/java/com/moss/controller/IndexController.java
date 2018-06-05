package com.moss.controller;

import com.moss.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.moss.rabbitmq.MQSender;

/**
 * Created by shenpeng on 2018/5/8.
 */
@Controller
public class IndexController {

    @Autowired
    MQSender mqSender;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/mq")
    @ResponseBody
    public Result<String> mq(){
        mqSender.send("hello, rabbitmq");
        return Result.success("hello, rabbitmq");
    }

}
