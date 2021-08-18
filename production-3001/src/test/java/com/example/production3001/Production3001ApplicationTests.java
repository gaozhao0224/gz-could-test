package com.example.production3001;

import com.example.production.vo.Dog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Production3001ApplicationTests {

    @Autowired
    private Dog dog;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Redisson redisson;

    @Test
    public void contextLoads() {
        String num = stringRedisTemplate.opsForValue().get("num");
        stringRedisTemplate.opsForValue().set("num1","2000");
        String num1 = stringRedisTemplate.opsForValue().get("num1");
        System.out.println(num);
        System.out.println(num1);
    }
    @Test
    public void test1() {
        RLock gzLock = redisson.getLock("gzLock");
        if(gzLock.isLocked()){
            if(gzLock.isHeldByCurrentThread()){
                gzLock.unlock();
            }
        }
    }

}
