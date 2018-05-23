package com.moss.conf.web;

import com.moss.bean.MallUser;
import com.moss.impl.MallUserServiceImpl;
import com.moss.redis.MallUserKey;
import com.moss.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MallUserArgumentResolver implements HandlerMethodArgumentResolver{


    @Autowired
    private RedisService redisService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //获取参数类型
        Class<?> clazz = methodParameter.getParameterType();
        //只处理MallUser参数
        return clazz == MallUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest)nativeWebRequest.getNativeRequest();
        HttpServletResponse response = (HttpServletResponse) nativeWebRequest.getNativeResponse();
        String paramToken = request.getParameter(MallUserServiceImpl.COOKIE_NAME_TOKEN);
        String cookieToken = getToken(request);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        MallUser mallUser = redisService.get(MallUserKey.token, token, MallUser.class);
        return mallUser;
    }

    public String getToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(MallUserServiceImpl.COOKIE_NAME_TOKEN)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
