package com.moss.controller;

import com.moss.result.CodeMessage;
import com.moss.result.Result;
import com.moss.service.MallUserService;
import com.moss.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private MallUserService mallUserService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login/doLogin")
    @ResponseBody
    public Result<Boolean> doLogin(@Valid LoginVo loginVo){
        mallUserService.login(loginVo);
        return Result.success(true);
    }

}