import com.common.entity.Dog;
import com.example.RabbitMQ5500Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RabbitMQ5500Application.class)
@RunWith(SpringRunner.class)
/*
* 没有消费者 只有生产者不会创建队列  有消费者消费后就会有队列创建 如果不删除
*  队列就一直在 消息还会保留 等下次消费者在绑定就会一起在消费做到持久化
*
* */
public class TestRabbitMQ {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
    * 简单模式
    * */
    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","队列hello内容");
    }

    /*
     * work模式
     * */
    @Test
    public void testWork() throws CloneNotSupportedException {
        Dog dog = new Dog("大狗子", "2", "男");
        for (int i = 0; i < 10; i++) {
            Dog clone = dog.clone();
            clone.setAge(i+"");
            rabbitTemplate.convertAndSend("work",clone);
        }
    }
    /*
    * 广播模式  fanout
    * */
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logsExchange","","fanout模式");
    }
    /*
    * 路由模式  route
    *
    * */
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("directExchange","add","发送key为add的消息");
        rabbitTemplate.convertAndSend("directExchange","insert","发送key为insert的消息");
        rabbitTemplate.convertAndSend("directExchange","update","发送key为update的消息");
        rabbitTemplate.convertAndSend("directExchange","delete","发送key为delete的消息");
    }

    /*
     * 路由模式  主题模式  有空试试多交换机 一样的原理
     * */
    @Test
    public void testTopic(){
//        rabbitTemplate.convertAndSend("topicExchange","useradd","发送key为user.add的消息");
//        rabbitTemplate.convertAndSend("topicExchange","userinsertgz","发送key为user.insert.gz的消息");
//        rabbitTemplate.convertAndSend("topicExchange","userupdategzlr","发送key为user.update.gz.lr的消息");
//        rabbitTemplate.convertAndSend("topicExchange","userdelete","发送key为delete的消息");
        rabbitTemplate.convertAndSend("topicExchange","user.add","发送key为user.add的消息");
        rabbitTemplate.convertAndSend("topicExchange","user.insert.gz","发送key为user.insert.gz的消息");
        rabbitTemplate.convertAndSend("topicExchange","user.update.gz.lr","发送key为user.update.gz.lr的消息");
        rabbitTemplate.convertAndSend("topicExchange","user.delete","发送key为delete的消息");
        rabbitTemplate.convertAndSend("topicExchange1","user.delete","发送key为delete的消息");
    }
}