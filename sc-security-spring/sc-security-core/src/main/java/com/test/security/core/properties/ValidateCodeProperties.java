package com.test.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author 费世程
 * @date 2020/12/17 9:19
 */
@Data
public class ValidateCodeProperties {

  /**
   * 验证码有效时间（单位：秒）
   */
  private long expireSeconds = 300L;

  @NestedConfigurationProperty
  private ImageCodeProperties image = new ImageCodeProperties();

  @NestedConfigurationProperty
  private SmsCodeProperties sms = new SmsCodeProperties();

}
