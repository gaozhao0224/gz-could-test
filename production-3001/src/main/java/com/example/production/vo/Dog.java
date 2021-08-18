package com.example.production.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
//@EnableConfigurationProperties(Dog.class)
@Component
@ConfigurationProperties("person.dog")
public class Dog {

   private String dogName;
   private String dogAge;
}