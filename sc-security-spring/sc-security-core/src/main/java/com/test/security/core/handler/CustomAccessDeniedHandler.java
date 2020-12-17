package com.test.security.core.handler;

import com.test.security.core.result.ResAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 费世程
 * @date 2020/12/16 16:33
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
  private final ResAdapter resAdapter;

  public CustomAccessDeniedHandler(ResAdapter resAdapter) {
    this.resAdapter = resAdapter;
  }

  @Override
  public void handle(HttpServletRequest httpServletRequest,
                     HttpServletResponse httpServletResponse,
                     AccessDeniedException e) throws IOException {
    log.error("AccessDeniedException -> {}", e.getMessage());
    resAdapter.unauthorized(httpServletResponse, e);
  }
}
