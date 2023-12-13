package com.ekkosong.base.service;

public interface UmsMemberService {
    /**
     * 生成验证码
     */
    String generateAuthCode(String phone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    boolean verifyAuthCode(String phone, String authCode);
}
