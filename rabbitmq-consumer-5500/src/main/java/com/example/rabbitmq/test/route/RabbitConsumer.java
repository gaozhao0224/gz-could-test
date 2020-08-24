package com.example.rabbitmq.test.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/*
* 绑定多个队列 一条消息会被多个队列消费
* */
@Component("direct")//类名一样 注入默认小驼峰 然后报错  起个别名
public class RabbitConsumer {

    /*
    *   Exchange在定义的时候是有类型的，以决定到底是哪些Queue符合条件，可以接收消息
        fanout
        所有bind到此exchange的queue都可以接收消息
        direct
        通过routingKey和exchange决定的那个唯一的queue可以接收消息
        topic
        所有符合routingKey(此时可以是一个表达式)的routingKey所bind的queue可以接收消息
        表达式符号说明：#代表一个或多个字符，*代表任何字符
        例：#.a会匹配a.a，aa.a，aaa.a等
        *.a会匹配a.a，b.a，c.a等
        注：使用RoutingKey为#，Exchange Type为topic的时候相当于使用fanout
    * */


    /*
    * 没有队列绑定delete这个key  所以这个消息就不存在 有了队列才会有消息
    * */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("directQueue"),//创建队列
                    exchange = @Exchange(value = "directExchange",type = "direct"),
                    key = {"add","insert"}
            )
    })
    public void getDirect1(String message){
        System.out.println("消费key为add和insert的消息\t\t"+message);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("directQueue1"),//创建队列
                    exchange = @Exchange(value = "directExchange",type = "direct"),
                    key = {"add","update"}
            )
    })
    public void getDirect2(String message){
        System.out.println("消费key为add和update的消息\t\t"+message);
    }
}