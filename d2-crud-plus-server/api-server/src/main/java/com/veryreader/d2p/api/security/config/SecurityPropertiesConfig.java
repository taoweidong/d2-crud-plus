package com.veryreader.d2p.api.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/10/18$
 */
@ConfigurationProperties("security")
@Data
@Configuration
public class SecurityPropertiesConfig {

    private String tokenParam = "token"; //从参数中获取token的参数名

    private String tokenHeader = "authorization"; //从header里获取token的参数名
    private String tokenPrefix = "Bearer ";


    private String jwtSecret = "";
    private String jwtIssuer = "";
    // token有效期一天
    private Integer tokenExpiration = 86400;
}
