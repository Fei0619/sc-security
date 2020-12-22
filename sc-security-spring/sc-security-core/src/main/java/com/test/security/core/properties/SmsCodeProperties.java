package com.test.security.core.properties;

import lombok.Data;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 费世程
 * @date 2020/12/17 9:21
 */
@Data
@SuppressWarnings("unused")
public class SmsCodeProperties {

  /**
   * 短信验证码长度
   */
  private int length = 6;
  /**
   * 手机号校验规则
   */
  private String phoneRegex = "";
  /**
   * 发送短信 或 验证短信验证码 时手机号参数
   */
  private String mobileParam = "mobile";
  /**
   * 请求中携带短信验证码值的参数名
   */
  private String smsCodeParam = "smsCode";
  /**
   * session中存储的手机号码,通过手机号码获取验证码时,会通过该key将手机号码存入session
   */
  private String mobileSessionKey = "mobile";
  /**
   * 获取短信验证码的请求地址
   */
  private String processingUrl = "/sms/code";
  /**
   * 需要验证短信验证码的接口地址列表
   */
  private Map<HttpMethod, List<String>> checkUrls = new HashMap<>();

}
