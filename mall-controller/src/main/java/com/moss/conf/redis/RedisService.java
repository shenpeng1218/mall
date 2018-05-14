package com.moss.conf.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    public <T> T get(String key, Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            T t = stringToBean(value,clazz);
            return t;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public <T> boolean set(String key, T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String beanStr = beanToString(value);
            jedis.set(key, beanStr);
            return true;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    private <T> T stringToBean(String value, Class<T> clazz){
        //非空判断
        if(StringUtils.isEmpty(value) || clazz ==null){
            return null;
        }
        //类型判断
        if(clazz == int.class || clazz == Integer.class){
            return (T) Integer.valueOf(value);
        }else if(clazz == String.class){
            return (T) value;
        }else if(clazz == long.class){
            return (T) Long.valueOf(value);
        }else{
            return JSON.toJavaObject(JSON.parseObject(value), clazz);
        }
    }

    private <T> String beanToString(T value){
        //非空判断
        if(value == null){
            return null;
        }
        //类型判断
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class){
            return value + "";
        }else if(clazz == String.class){
            return (String) value;
        }else if(clazz == long.class){
            return value + "";
        }else{
            return JSON.toJSONString(value);
        }
    }
}
