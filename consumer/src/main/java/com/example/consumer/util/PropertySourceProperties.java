package com.example.consumer.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:config.properties")
public class PropertySourceProperties {

    @Value("${nameUser}")
    private String name ;

}