package com.example;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagerServer  //增加服务放注解
public class TxLcnServer8500Application {
    public static void main(String[] args) {
        SpringApplication.run(TxLcnServer8500Application.class,args);
    }
}