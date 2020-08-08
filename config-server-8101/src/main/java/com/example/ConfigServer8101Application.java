package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigServer8101Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer8101Application.class, args);
    }

}
