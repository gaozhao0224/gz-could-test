package com.example;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.example.gz.service")
@MapperScan({"com.example.gz.mapper"})
@EnableDistributedTransaction
public class TxLcnClient8888Application {
    public static void main(String[] args) {
        SpringApplication.run(TxLcnClient8888Application.class,args);
    }
}