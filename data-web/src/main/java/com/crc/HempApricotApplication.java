package com.crc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.crc.mapper")
public class HempApricotApplication {
    public static void main(String[] args) {
        SpringApplication.run(HempApricotApplication.class,args);
    }
}