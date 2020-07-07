package com.example.redis.controller;

import com.example.redis.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 梦中梦i
 * @CreateTime: 2020-06-03 23:18
 * @Description:
 */
@RestController
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key, String value) {
        return redisUtil.set(key, value);
    }

    @RequestMapping("get")
    public Object redisget(String key) {
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key) {
        // redis中存储的过期时间60s
        int expireTime = 60;
        return redisUtil.expire(key, expireTime);
    }
}
