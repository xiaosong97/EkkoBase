package com.ekkosong.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ekkosong.base.service.RedisService;
import com.ekkosong.base.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public String generateAuthCode(String phone) {
        // 生成六位随机数字作为验证码
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int bit = r.nextInt(10);
            sb.append(bit);
        }
        String authCode = sb.toString();
        String key = REDIS_KEY_PREFIX_AUTH_CODE + phone;

        // 将生成的验证码存储到redis 中并设置过期时间，key 为 REDIS_KEY_PREFIX_AUTH_CODE + phone
        redisService.set(key, authCode, AUTH_CODE_EXPIRE_SECONDS);

        return authCode;
    }

    @Override
    public boolean verifyAuthCode(String phone, String authCode) {
        String key = REDIS_KEY_PREFIX_AUTH_CODE + phone;
        if (!redisService.hasKey(key) || StrUtil.isBlank(authCode)) {
            return false;
        }
        return redisService.get(key).equals(authCode);
    }
}
