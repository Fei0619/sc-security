package com.test.security.core.result;

import com.test.share.common.json.JsonUtils;
import org.springframework.security.access.AccessDeniedException;
import com.test.share.common.result.Res;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 费世程
 * @date 2020/12/16 17:14
 */
public class DefaultResAdapter implements ResAdapter {

  @Override
  public void preResWrite(Res res) {

  }

  @Override
  public void invalidAccessToken(HttpServletResponse response, String message) throws IOException {
    response.setStatus(200);
    response.setContentType(APPLICATION_JSON_UTF8);
    Res<Object> res = Res.error(message);
    preResWrite(res);
    try (PrintWriter writer = response.getWriter()) {
      writer.write(JsonUtils.INSTANCE.toJsonString(res));
    }
  }

  @Override
  public void unauthorized(HttpServletResponse response, AccessDeniedException exception) throws IOException {
    response.setStatus(200);
    response.setContentType(APPLICATION_JSON_UTF8);
    Res<Object> res = Res.exception(exception);
    preResWrite(res);
    try (PrintWriter writer = response.getWriter()) {
      writer.write(JsonUtils.INSTANCE.toJsonString(res));
    }
  }
}
