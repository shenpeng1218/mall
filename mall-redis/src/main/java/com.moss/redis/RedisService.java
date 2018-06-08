package com.moss.redis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 根据key与前缀获取value
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //给key添加前缀
            String realKey = prefix.getPrefix() + key;
            String value = jedis.get(realKey);
            if(StringUtils.isEmpty(value)){
                return null;
            }
            T t = stringToBean(value, clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 插入key value，其中key要添加业务前缀，set方法永久添加，setex方法添加过期时间，setnx判断key是否存在，存在的话不覆盖
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String beanStr = beanToString(value);
            //给key添加前缀
            String realKey = prefix.getPrefix() + key;
            int expireSeconds = prefix.expireSeconds();
            if(expireSeconds <= 0){
                jedis.set(realKey, beanStr);
            }else{
                jedis.setex(realKey, expireSeconds, beanStr);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断key值是否存在
     * @param prefix
     * @param key
     * @return
     */
    public boolean exists(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //给key添加前缀
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 根据key和prefix对值进行+1操作
     * @param prefix
     * @param key
     * @return
     */
    public long incr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //给key添加前缀
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 根据key和prefix对值进行-1操作
     * @param prefix
     * @param key
     * @return
     */
    public long decr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //给key添加前缀
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    private void returnToPool(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

    public static <T> T stringToBean(String value, Class<T> clazz){
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

    public static <T> String beanToString(T value){
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
