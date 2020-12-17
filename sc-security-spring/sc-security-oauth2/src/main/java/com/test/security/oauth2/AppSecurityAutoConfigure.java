package com.test.security.oauth2;

import com.test.security.core.handler.AuthExceptionEntryPoint;
import com.test.security.core.handler.CustomAccessDeniedHandler;
import com.test.security.core.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author 费世程
 * @date 2020/10/19 23:49
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties({SecurityProperties.class, Oauth2Properties.class}) //声明配置类
public class AppSecurityAutoConfigure {

  @Bean
  @ConditionalOnMissingBean
  public AuthenticationEntryPoint authenticationEntryPoint(Oauth2ResAdapter adapter) {
    return new AuthExceptionEntryPoint(adapter);
  }

  @Bean
  @ConditionalOnMissingBean
  public AccessDeniedHandler accessDeniedHandler(Oauth2ResAdapter adapter) {
    return new CustomAccessDeniedHandler(adapter);
  }

}
