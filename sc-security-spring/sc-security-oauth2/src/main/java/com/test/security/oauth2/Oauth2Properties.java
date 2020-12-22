package com.test.security.oauth2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.time.Duration;

/**
 * @author 费世程
 * @date 2020/12/16 15:34
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "sc.security.oauth2")
public class Oauth2Properties {

  /**
   * accessToken是否自动续期
   */
  private boolean accessTokenAutoRenewal;
  /**
   * accessToken有效期
   */
  private Duration accessTokenExpires = Duration.ofHours(1);
  /**
   * refreshToken有效期
   */
  private Duration refreshTokenExpires = Duration.ofDays(30);

}
