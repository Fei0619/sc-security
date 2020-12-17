package com.test.security.oauth2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author 费世程
 * @date 2020/12/16 15:34
 */
@RefreshScope
@ConfigurationProperties(prefix = "sc.security.oauth")
public class Oauth2Properties {
}
