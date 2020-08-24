package com.example.rabbitmq.test.work;

import com.common.entity.Dog;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("work")//类名一样 注入默认小驼峰 然后报错  起个别名
public class RabbitConsumer {

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))  //声明队列
    public void getWork1(Dog message){
        System.out.println("work第一个消费者111\t\t"+message.toString());
    }
    @RabbitListener(queuesToDeclare = @Queue(value = "work"))  //声明队列
    public void getWork2(Dog message){
        System.out.println("work第二个消费者222\t\t"+message.toString());
    }
}