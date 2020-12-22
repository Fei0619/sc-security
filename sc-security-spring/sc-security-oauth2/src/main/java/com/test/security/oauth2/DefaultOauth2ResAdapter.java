package com.test.security.oauth2;

import com.test.security.core.result.DefaultResAdapter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author 费世程
 * @date 2020/12/21 10:46
 */
public class DefaultOauth2ResAdapter extends DefaultResAdapter implements Oauth2ResAdapter {

  @Override
  public void logoutSuccess(HttpServletResponse response, Oauth2UserDetails userDetails) {

  }

  @Override
  public void oauth2LoginSuccess(HttpServletRequest request, HttpServletResponse response, OAuth2AccessToken accessToken, Oauth2UserDetails userDetails) {

  }

  @Override
  public void basicAuthorizationFail(HttpServletResponse response, String message) {

  }

  @Override
  public void removeAccessToken(Collection<OAuth2AccessToken> accessTokens, Oauth2UserDetails userDetails) {

  }
}
