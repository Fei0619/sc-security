package com.test.security.core.result;

import org.springframework.security.access.AccessDeniedException;
import com.test.share.common.result.Res;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 费世程
 * @date 2020/12/16 15:57
 */
public interface ResAdapter {

  String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";
  String TEXT_HTML_UTF8 = "text/html;charset=UTF-8";

  void preResWrite(Res res);

  /**
   * 无效的AccessToken
   *
   * @param response HttpServletResponse
   * @param message  错误信息
   */
  void invalidAccessToken(HttpServletResponse response, String message) throws IOException;

  /**
   * 认证过的用户访问
   *
   * @param response  HttpServletResponse
   * @param exception AccessDeniedException
   */
  void unauthorized(HttpServletResponse response, AccessDeniedException exception) throws IOException;

}
