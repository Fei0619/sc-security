package com.test.security.oauth2;

import com.test.security.core.SecurityUserDetails;

/**
 * @author 费世程
 * @date 2020/12/21 10:51
 */
public interface Oauth2UserDetails extends SecurityUserDetails {

  /**
   * 设置oauth2协议中的客户端id
   */
  void setClientId(String clientId);

}
