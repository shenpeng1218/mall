package com.moss.service;

import com.moss.bean.MallUser;
import com.moss.result.CodeMessage;
import com.moss.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

public interface MallUserService {

    public MallUser getById(long id);

    public String login(HttpServletResponse response, LoginVo loginVo);

    public MallUser getByToken(HttpServletResponse response, String token);
}
