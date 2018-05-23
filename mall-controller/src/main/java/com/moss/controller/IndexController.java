package com.moss.controller;

import com.moss.bean.User;
import com.moss.redis.RedisService;
import com.moss.redis.UserKey;
import com.moss.result.Result;
import com.moss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shenpeng on 2018/5/8.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //如下两个接口纯属测试mybaits
    @RequestMapping("/user/{id}")
    @ResponseBody
    public Result<User> getUser(@PathVariable int id){
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @RequestMapping("/user/insert")
    @ResponseBody
    public Result<Integer> insertUser(){
        int id = userService.insertUser();
        return Result.success(id);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> testRedis(){
        User user = userService.getUserById(1);
        boolean result = redisService.set(UserKey.getById,"1", user);
        return Result.success(result);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.getById,"1", User.class);
        return Result.success(user);
    }

}
