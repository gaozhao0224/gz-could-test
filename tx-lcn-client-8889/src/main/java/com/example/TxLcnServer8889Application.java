package com.example;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.example.gz.mapper"})
@EnableDistributedTransaction
public class TxLcnServer8889Application {
    public static void main(String[] args) {
        SpringApplication.run(TxLcnServer8889Application.class,args);
    }
}