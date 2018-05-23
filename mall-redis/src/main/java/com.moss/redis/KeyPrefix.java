package com.moss.redis;

/**
 * key的前缀，用于将业务模块的代码和key一起封装，保证多个人之间写的key不会被互相覆盖
 */
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
