package com.test.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 费世程
 * @date 2020/10/19 23:53
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "sc.security")
@Data
public class SecurityProperties {

  /**
   * 登录页
   */
  private String loginPage = "/login";
  /**
   * 1）用户名密码登录接口地址
   */
  private String smsLoginProcessUrl = "/password/login";
  /**
   * _用户名的请求字段
   */
  private String usernameParameter = "username";
  /**
   * _密码的请求字段
   */
  private String passwordParameter = "password";
  /**
   * 2）短信验证码登录接口地址
   */
  private String loginProcessUrl = "/mobile/login";
  /**
   * _手机号请求字段名
   */
  private String mobileParameter = "mobile";
  /**
   * 是否允许重复登录
   * - 设为true时，每个client获取token时都会尝试返回已有的token
   * - 设为false时，每次调用登录接口都会返回新的token，并将旧token作废
   */
  private boolean repeatLogin = false;
  /**
   * 退出登录接口地址
   */
  private String logoutUrl = "/auth/logout";
  /**
   * 登录token前缀
   */
  private String tokenStorePrefix = "authToken:";
  /**
   * 不需要通过security鉴权的请求路径
   */
  private List<String> permitMatchers = new ArrayList<>();
  /**
   * 额外的不需要通过security鉴权的请求路径
   */
  private List<String> subPermitMatchers = new ArrayList<>();
  /**
   * 登录接口免验证码次数
   */
  private int loginVerificationFreeTimes = 0;
  /**
   * 密码登录失败超阈值时，账号锁定时间（单位:min）
   */
  private int passwordLoginFailureReleaseTime = 30 * 60;


}
