package com.example.bigdata.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:config.properties")
public class ConfigProperties {
    @Value("url")
    private String url ;

}