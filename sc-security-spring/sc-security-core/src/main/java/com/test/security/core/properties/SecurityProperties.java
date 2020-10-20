package com.test.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author 费世程
 * @date 2020/10/19 23:53
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "sc.security")
public class SecurityProperties {

  /**
   * 登录页
   */
  private String loginPage = "/login";
  /**
   * 登录接口地址
   */
  private String smsLoginProcessUrl = "/mobile/login";
  /**
   * 用户名密码登录时用户名的请求字段
   */
  private String usernameParameter = "username";
  /**
   * 密码的请求字段
   */
  private String passwordParameter = "password";

  /**
   * 登录接口地址
   */
  private String loginProcessUrl = "/password/login";
  private String mobileParameter = "mobile";

}
