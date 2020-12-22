package com.test.security.oauth2;

import com.test.security.core.handler.AuthExceptionEntryPoint;
import com.test.security.core.handler.CustomAccessDeniedHandler;
import com.test.security.core.properties.SecurityProperties;
import com.test.security.oauth2.handler.CustomRedisTokenStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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

  @Bean
  @ConditionalOnMissingBean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @ConditionalOnMissingBean
  public Oauth2ResAdapter oauth2ResAdapter() {
    return new DefaultOauth2ResAdapter();
  }

  @Bean
  @ConditionalOnMissingBean
  public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory,
                               SecurityProperties securityProperties,
                               Oauth2Properties oauth2Properties) {
    RedisTokenStore redisTokenStore = new CustomRedisTokenStore(redisConnectionFactory, oauth2Properties);
    redisTokenStore.setPrefix(securityProperties.getTokenStorePrefix());
    return redisTokenStore;
  }

  @Bean
  @ConditionalOnMissingBean
  public AuthenticationSuccessHandler authenticationSuccessHandler(
      PasswordEncoder passwordEncoder,
      Oauth2ResAdapter oauth2ResAdapter) {
    return null;
  }


}
