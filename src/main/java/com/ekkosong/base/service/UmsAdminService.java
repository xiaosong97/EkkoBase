package com.ekkosong.base.service;

import com.ekkosong.base.domain.AdminUserDetails;
import com.ekkosong.base.domain.UmsResource;

import java.util.List;

/**
 * 后台用户管理Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    AdminUserDetails getAdminByUsername(String username);

    /**
     * 获取所有权限列表
     */
    List<UmsResource> getResourceList();

    /**
     * 用户名密码登录
     */
    String login(String username, String password);
}
