package com.test.security.core;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author 费世程
 * @date 2020/12/21 10:49
 */
public interface SecurityUserDetails extends UserDetails {

  /**
   * 获取手机号码
   */
  String getMobile();

}
