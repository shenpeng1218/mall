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

    @RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> mqTopic(){
        mqSender.sendTopic("hello, rabbitmq topic exchange");
        return Result.success("hello, rabbitmq topic exchange");
    }

    @RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> mpFanout(){
        mqSender.sendFanout("hello, rabbitmq fanout exchange");
        return Result.success("hello, rabbitmq fanout exchange");
    }

    @RequestMapping("/mq/headers")
    @ResponseBody
    public Result<String> mpHeaders(){
        mqSender.sendHeaders("hello, rabbitmq headers exchange");
        return Result.success("hello, rabbitmq headers exchange");
    }
}
