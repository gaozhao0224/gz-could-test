package com.example.rabbitmq.test.fanout;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/*
* 持久化 非独占  不是自动删除的队列
* 绑定多个队列 一条消息会被多个队列消费  广播
* */
@Component("fanout")//类名一样 注入默认小驼峰 然后报错  起个别名
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
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("logsQueue"),//不给值默认给个临时队列
                    exchange = @Exchange(value = "logsExchange",type = "fanout")//绑定交换机
            )
    })
    public void getFanout1(String message){
        System.out.println("fanout111111111\t\t"+message);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("logsQueue1"),//不给值默认给个临时队列
                    exchange = @Exchange(value = "logsExchange",type = "fanout")//绑定交换机
            )
    })
    public void getFanout2(String message){
        System.out.println("fanout222222\t\t"+message);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("logsQueue2"),//不给值默认给个临时队列
                    exchange = @Exchange(value = "logsExchange",type = "fanout")//绑定交换机
            )
    })
    public void getFanout3(String message){
        System.out.println("fanout33333\t\t"+message);
    }
}