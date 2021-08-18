package com.example.rabbitmq.test.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/*
* 绑定多个队列 一条消息会被多个队列消费
* 分词  通过点  点分割词  不是字符  aa.bb.cc 代表三个词 aa bb cc
*
* */
@Component("topic")//类名一样 注入默认小驼峰 然后报错  起个别名
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
    * 可以创建多个队列
    * */
    /*@RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("topicQueue"),//创建队列
                    exchange = @Exchange(value = "topicExchange",type = "topic"),
                    key = {"user.*.*.lr"}
            )
    })
    public void getTopic1(String message){
        System.out.println("111111111111111消费key为user.*.*.lr的消息\t\t"+message);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("topicQueue1"),//创建队列
                    exchange = @Exchange(value = "topicExchange",type = "topic"),
                    key = {"user.#.lr"}
            )
    })
    public void getTopic2(String message){
        System.out.println("2222222222222222消费key为user.#.lr的消息\t\t"+message);
    }*/
//    @RabbitListener(bindings = {
//            @QueueBinding(
//                    value = @Queue("topicQueue3"),//创建队列
//                    exchange = @Exchange(value = "topicExchange",type = "topic"),
//                    key = {"user.*"}
//            )
//    })
//    public void getTopic3(String message){
//        System.out.println("333333333消费key为user.*的消息\t\t"+message);
//    }



    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("topicQueue4"),//创建队列
                    exchange = @Exchange(value = "topicExchange",type = "topic"),
                    key = {"user.#"}
            )
    })
    public void getTopic4(String message){
        System.out.println("4444444444444消费key为user.#的消息--topicQueue4\t\t"+message);
    }





    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("topicQueue5"),//创建队列
                    exchange = @Exchange(value = "topicExchange1",type = "topic"),
                    key = {"gz.#"}
            )
    })
    public void getTopic5(Dog message){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("4444444444444消费key为user.#的消息\t\t"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("topicQueue6"),//创建队列
                    exchange = @Exchange(value = "topicExchange1",type = "topic"),
                    key = {"gz.#"}
            )
    })
    public void getTopic6(Dog message){
//        try {
//            TimeUnit.MILLISECONDS.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("66666666666666消费key为user.#的消息\t\t"+message);
    }

}