package com.crc.config.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @BelongsProject: demo
 * @Author: DanBrown
 * @CreateTime: gz
 * @description: TODO
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

  @Value("${file-download.url}")
  private String fileUrl;
 
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    log.info(fileUrl);
    //>>  /file/**为前端URL访问路径 后面 file:xxxx为本地磁盘映射
    registry.addResourceHandler("/file/**").addResourceLocations("file:" + "/data/crcfile/");
    System.out.println(fileUrl);
    //registry.addResourceHandler("/file/**").addResourceLocations("file:" + "G:/upload/");
  }
}