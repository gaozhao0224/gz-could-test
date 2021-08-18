import com.example.WebDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebDemoApplication.class)
public class TestRedis {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void redis(){
        String s = redisTemplate.opsForValue().get("LB_ZUUL_ROUTE_KEY");
        System.out.println(s);
    }
    /*
    * list
    * */
    @Test
    public void testList(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"_q");
        }
        redisTemplate.opsForList().leftPushAll("myList",list);
    }
    /*
    * Hash
    * */
    @Test
    public void testHash(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","黑");
        map.put("age","30");
//        redisTemplate.opsForHash().putAll("dog1", map);
//        redisTemplate.opsForHash().put("dog","name","白");
//        RedisOperations<String, ?> operations = redisTemplate.opsForHash().getOperations();
//        System.out.println("-------------->>>>>>"+operations);
        redisTemplate.opsForHash().put("dog","age","21");
        //values参数key对应的map值，返回结果为List<Object> ，keys方法也类似
        Object o1 = redisTemplate.opsForHash().values("dog");
        Object o2 = redisTemplate.opsForHash().get("dog", "name");
        Map<Object, Object> o3 = redisTemplate.opsForHash().entries("dog");
        Boolean aBoolean = redisTemplate.opsForHash().hasKey("dog", "age");
        System.out.println("-------------->>>>>>"+aBoolean);
        System.out.println("-------------->>>>>>"+o1);
        System.out.println("-------------->>>>>>"+o2);
        System.out.println("-------------->>>>>>"+o3);
    }

}