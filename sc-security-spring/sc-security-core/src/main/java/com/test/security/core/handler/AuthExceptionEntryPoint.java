package com.test.security.core.handler;

import com.test.security.core.result.ResAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来解决匿名用户无访问权限异常
 *
 * @author 费世程
 * @date 2020/12/16 16:04
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

  private static final Logger log = LoggerFactory.getLogger(AuthExceptionEntryPoint.class);
  private final ResAdapter resAdapter;

  public AuthExceptionEntryPoint(ResAdapter resAdapter) {
    this.resAdapter = resAdapter;
  }

  @Override
  public void commence(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AuthenticationException e) throws IOException {
    String requestURI = httpServletRequest.getRequestURI();
    String message = e.getMessage();
    resAdapter.invalidAccessToken(httpServletResponse, message);
    log.debug("authenticationException -> {}, requestURI -> {}", message, requestURI);
  }

}
