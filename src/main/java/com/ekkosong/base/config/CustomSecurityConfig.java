package com.ekkosong.base.config;

import com.ekkosong.base.domain.AdminUserDetails;
import com.ekkosong.base.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义配置，用于配置如何获取用户信息
 */
@Configuration
public class CustomSecurityConfig {
    @Autowired
    private UmsAdminService adminService;
    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> {
            AdminUserDetails admin = adminService.getAdminByUsername(username);
            if (admin != null) {
                return admin;
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }
}
