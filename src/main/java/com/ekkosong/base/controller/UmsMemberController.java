package com.ekkosong.base.controller;

import cn.hutool.core.util.StrUtil;
import com.ekkosong.base.common.CommonResult;
import com.ekkosong.base.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 以短信验证码的存储验证为例, 具体逻辑如下：
 * 生成验证码时，将自定义的Redis键值加上手机号生成一个Redis的key，
 * 以验证码为value存入到Redis中，并设置过期时间为自己配置的时间（这里为120s）。
 * 校验验证码时根据手机号码来获取Redis里面存储的验证码，并与传入的验证码进行比对。
 */
@Api(tags = "用户登录注册管理相关接口")
@RestController
@Slf4j
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping
    @ApiOperation("获取验证码")
    public CommonResult<String> getAuthCode(String phone) {
        log.info("获取验证码，手机号为：{}", phone);
        if (StrUtil.isBlank(phone)) {
            return CommonResult.validateFailed();
        }
        String authCode = umsMemberService.generateAuthCode(phone);
        log.info("生成的验证码为：{}", authCode);
        return CommonResult.success(authCode, "获取验证码成功");
    }

    @PostMapping
    @ApiOperation("修改密码")
    public CommonResult updatePassword(String phone, String authCode) {
        log.info("修改密码，首先校验验证码是否正确，手机号：{}, 验证码：{}", phone, authCode);
        if (StrUtil.isBlank(phone) || StrUtil.isBlank(authCode)) {
            return CommonResult.validateFailed();
        }
        boolean result = umsMemberService.verifyAuthCode(phone, authCode);
        if (!result) {
            return CommonResult.failed("验证码不正确");
        }
        return CommonResult.success(null, "验证码校验成功");
    }
}
