package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @BelongsProject: demo
 * @Author: DanBrown
 * @CreateTime: 2020-03-28 14:33
 * @description: TODO
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Value("${file-download.url}")
  private String uploadPath;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // /home/file/**为前端URL访问路径 后面 file:xxxx为本地磁盘映射
    registry.addResourceHandler("/home/file/**").addResourceLocations("file:G:" + "/upload/");
  }
}