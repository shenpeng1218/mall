package com.moss.conf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Autowired
    MallUserArgumentResolver userArgumentResolver;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //允许页面加载静态资源
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        //给controller方法里的参数赋值
        argumentResolvers.add(userArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);
    }
}
