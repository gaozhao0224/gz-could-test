package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.example","com.feign"})
@EnableDiscoveryClient
@EnableFeignClients({"com.feign"})
@EnableHystrix
public class ConsumerNacos6001Application {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerNacos6001Application.class, args);
    }
}
