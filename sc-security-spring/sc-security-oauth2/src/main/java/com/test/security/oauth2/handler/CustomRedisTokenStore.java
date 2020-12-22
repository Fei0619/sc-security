package com.test.security.oauth2.handler;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.test.security.oauth2.Oauth2Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 费世程
 * @date 2020/12/21 11:15
 */
public class CustomRedisTokenStore extends RedisTokenStore {

  private static final Logger log = LoggerFactory.getLogger(CustomRedisTokenStore.class);
  private final Oauth2Properties oauth2Properties;
  /**
   * 自动续期的剩余有效时间,如果AccessToken的剩余有效时间小于这个值,则直接重置AccessToken的过期时间
   */
  private static final int autoRenewalExpires = 3000;
  /**
   * 自动续期时间间隔
   */
  private static final int interval = 60;
  private static final Byte FLAG = 0;
  private static final Cache<String, Byte> caches = Caffeine.newBuilder()
      .maximumSize(Integer.MAX_VALUE)
      .expireAfterWrite(interval, TimeUnit.SECONDS)
      .build();

  public CustomRedisTokenStore(RedisConnectionFactory redisConnectionFactory, Oauth2Properties oauth2Properties) {
    super(redisConnectionFactory);
    this.oauth2Properties = oauth2Properties;
  }

  /**
   * 实现AccessToken自动续期功能
   */
  @Override
  public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
    String tokenValue = token.getValue();
    OAuth2Authentication auth2Authentication = readAuthentication(tokenValue);
    if (oauth2Properties.isAccessTokenAutoRenewal() || auth2Authentication != null) {
      final int expireIn = token.getExpiresIn();
      final BooleanWrapper renewal = new BooleanWrapper(false);
      caches.get(tokenValue, (key) -> {
        renewal.set(true);
        return FLAG;
      });
      if (expireIn < autoRenewalExpires || renewal.get()) {
        log.debug("AccessToken auto refresh...");
        DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) token;
        Duration accessTokenExpires = oauth2Properties.getAccessTokenExpires();
        long millis = accessTokenExpires.toMillis();
        oAuth2AccessToken.setExpiration(new Date(System.currentTimeMillis() + millis));
        storeAccessToken(oAuth2AccessToken, auth2Authentication);
      }
    }
    return auth2Authentication;
  }

  private static class BooleanWrapper {
    private boolean value;

    public BooleanWrapper(boolean value) {
      this.value = value;
    }

    public boolean get() {
      return value;
    }

    public void set(boolean value) {
      this.value = value;
    }
  }

}
