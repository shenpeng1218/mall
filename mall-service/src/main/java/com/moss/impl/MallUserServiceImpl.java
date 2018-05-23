package com.moss.impl;

import com.moss.bean.MallUser;
import com.moss.exception.GlobalException;
import com.moss.mapper.MallUserDao;
import com.moss.redis.MallUserKey;
import com.moss.redis.RedisService;
import com.moss.result.CodeMessage;
import com.moss.service.MallUserService;
import com.moss.util.UUIDUtil;
import com.moss.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MallUserServiceImpl implements MallUserService{

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private MallUserDao mallUserDao;

    @Autowired
    private RedisService redisService;

    @Override
    public MallUser getById(long id) {
        return mallUserDao.getById(id);
    }

    @Override
    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        MallUser mallUser = mallUserDao.getById(Long.parseLong(loginVo.getCellphoneNum()));
        if(mallUser == null){
            throw new GlobalException(CodeMessage.CELLPHONE_NOT_EXITE);
        }
        if(!mallUser.getPassword().equals(loginVo.getPassword())){
            throw new GlobalException(CodeMessage.PASSWORD_ERROR);
        }
        //登录成功，生成token，写入cookie与session
        String token = UUIDUtil.uuid();
        addCookie(mallUser, response, token);
        return true;
    }

    private void addCookie(MallUser mallUser, HttpServletResponse response, String token){

        //单机应用写入服务器session即可，此处仿照分布式应用，将session写入redis之类的中间件
        redisService.set(MallUserKey.token, token, mallUser);

        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MallUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public MallUser getByToken(HttpServletResponse response, String token) {
        if(token == null){
            return null;
        }
        MallUser user = redisService.get(MallUserKey.token, token, MallUser.class);
        //再次访问之后延长过期时间-----1刷新缓存2刷新cookie
        addCookie(user, response, token);
        return user;
    }
}
