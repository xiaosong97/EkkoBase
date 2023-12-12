package com.ekkosong.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({"com.ekkosong.base.mbg.mapper", "com.ekkosong.base.dao"})
public class MyBatisConfig {
}
