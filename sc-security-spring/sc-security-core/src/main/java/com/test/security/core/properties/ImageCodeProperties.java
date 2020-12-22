package com.test.security.core.properties;

import lombok.Data;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 费世程
 * @date 2020/12/17 9:20
 */
@Data
@SuppressWarnings("unused")
public class ImageCodeProperties {
  /**
   * 图片宽度
   */
  private int width = 78;
  /**
   * 图片高度
   */
  private int height = 30;
  /**
   * 验证码长度
   */
  private int length = 4;
  /**
   * 请求中携带短信验证码值的参数名
   */
  private String imageCodeParam = "imageCode";
  /**
   * 图片验证码寝取地址
   */
  private String processingUrl = "/code/image";
  /**
   * 响应图片验证码的方式
   */
  private WriteType writeType = WriteType.BASE64;
  /**
   * 需要输入图片验证码的接口地址
   */
  private Map<HttpMethod, List<String>> checkUrls = new HashMap<>();


  public enum WriteType {
    /**
     * 返回base64格式图片
     */
    BASE64,
    /**
     * 直接将图片写入响应流
     */
    IMAGE,
    ;

  }

}
