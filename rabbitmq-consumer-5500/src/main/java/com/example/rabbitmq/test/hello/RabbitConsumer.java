//package com.example.rabbitmq.test.hello;
//
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///*
//* 持久化 非独占  不是自动删除的队列
//* */
//@Component("hello")//类名一样 注入默认小驼峰 然后报错  起个别名
//@RabbitListener(queuesToDeclare = @Queue(value = "hello",durable = "true",autoDelete = "false"))  //声明队列  durable持久化（默认开启）  autoDelete  是否自动删除
//public class RabbitConsumer {
//
//    @RabbitHandler
//    public void getHello(String message){
//        System.out.println("hello\t\t"+message);
//    }
//}