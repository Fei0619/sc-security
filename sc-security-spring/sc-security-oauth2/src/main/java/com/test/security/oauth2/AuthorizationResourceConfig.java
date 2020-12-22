package com.test.security.oauth2;

import com.test.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.List;

/**
 * @author 费世程
 * @date 2020/12/5 18:20
 */
//开启资源服务
@EnableResourceServer
public class AuthorizationResourceConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;
  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint;
  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.authenticationEntryPoint(authenticationEntryPoint)
        .accessDeniedHandler(accessDeniedHandler);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    List<String> permitMatchers = securityProperties.getPermitMatchers();
    List<String> subPermitMatchers = securityProperties.getSubPermitMatchers();
    if (subPermitMatchers != null && subPermitMatchers.size() > 0) {
      permitMatchers.addAll(subPermitMatchers);
    }
    permitMatchers.add(securityProperties.getLoginProcessUrl()); //短信验证码登录接口
    permitMatchers.add(securityProperties.getSmsLoginProcessUrl()); //用户名密码登录接口
    permitMatchers.add(securityProperties.getValidateCode().getImage().getProcessingUrl()); //获取图片验证码接口地址
    permitMatchers.add(securityProperties.getValidateCode().getSms().getProcessingUrl()); //获取短信验证码接口地址

    http.formLogin()
        .loginPage(securityProperties.getLoginPage()) //登录页
        .loginProcessingUrl(securityProperties.getLoginProcessUrl()) //登录接口地址
        .usernameParameter(securityProperties.getUsernameParameter()) //用户名参数名
        .passwordParameter(securityProperties.getPasswordParameter()); //密码参数名

    super.configure(http);
  }

}
