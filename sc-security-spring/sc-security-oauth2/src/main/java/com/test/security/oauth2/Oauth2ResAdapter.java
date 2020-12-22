package com.test.security.oauth2;

import com.test.security.core.result.ResAdapter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author 费世程
 * @date 2020/12/16 16:02
 */
public interface Oauth2ResAdapter extends ResAdapter {

  /**
   * 成功退出登录
   */
  void logoutSuccess(HttpServletResponse response, Oauth2UserDetails userDetails);

  /**
   * 登录成功，返回token
   */
  void oauth2LoginSuccess(HttpServletRequest request, HttpServletResponse response,
                          OAuth2AccessToken accessToken,
                          Oauth2UserDetails userDetails);

  /**
   * Authorization头不合法
   */
  void basicAuthorizationFail(HttpServletResponse response, String message);

  /**
   * AccessToken被移除
   */
  void removeAccessToken(Collection<OAuth2AccessToken> accessTokens, Oauth2UserDetails userDetails);

}
