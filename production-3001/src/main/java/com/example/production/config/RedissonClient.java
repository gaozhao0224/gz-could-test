package com.example.production.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonClient {

    @Bean
    public Redisson redisson(){
        //测试方便使用单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("gz").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}