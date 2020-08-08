package com.example.production.test.controller;

import cn.hutool.core.util.IdUtil;
import com.common.controller.BaseController;
import com.common.entity.User;
import com.example.production.config.RedissonClient;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController extends BaseController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @GetMapping("/getLBPro/{id}")
    public Object loadBalancerTest(@PathVariable("id") String id){
        System.out.println("当前访问的id是:\t\t"+id+"和项目production:\t\t"+serverPort);
        return "当前访问的id是:\t\t"+id+"和项目production:\t\t"+serverPort;
    }


    @GetMapping("/getLBPro/test")
    public Object loadBalancerTest(@RequestBody User user){
        System.out.println("当前访问的user是:\t\t"+user.toString()+"和项目production:\t\t"+serverPort);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","当前访问的user是:\t\t"+user.toString()+"和项目production:\t\t"+serverPort);
        return map;
    }

    @GetMapping("/concurrenceMany/{num}")
    public Object concurrenceMany(@PathVariable("num") String key){
        String str = "空";
        synchronized (this){
            String gz = stringRedisTemplate.opsForValue().get(key);
            Integer num = Integer.valueOf(gz);
            if(num>0){
                num--;
                stringRedisTemplate.opsForValue().set("gz",""+num);
                System.out.println("成功 还剩：\t"+num);
                str =  "成功 还剩：\t"+num;
            }else{
                System.out.println("库存不足");
                str = "库存不足";
            }
        }
        return str;
    }
    /*
     * 通过Jmeter 测试并发   通过集群测试  通过redis解决
     *
     * */
   /* @GetMapping("/concurrenceManyRedis/{num}")
    public Object concurrenceManyRedis(@PathVariable("num") String key){
        String str = "空";
        //假如抢购一个商品  已此商品id为key
        String commodityId = "commodityId";
        //redis中如果有这个key就返回true 就往下执行 代表现在该商品没有人操作 新增成功了
        // 如果返回false代表有请求在执行中  然后让起等待或者直接返回 （现在直接给返回）
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(commodityId, "lock");
        if(!aBoolean){
            return "系统繁忙请稍后再试";
        }
        String gz = stringRedisTemplate.opsForValue().get(key);
        Integer num = Integer.valueOf(gz);
        if(num>0){
            num--;
            stringRedisTemplate.opsForValue().set("gz",""+num);
            System.out.println("成功 还剩：\t"+num);
            str =  "成功 还剩：\t"+num;
        }else{
            System.out.println("库存不足");
            str = "库存不足";
        }
        //释放锁
        stringRedisTemplate.delete(commodityId);
        return str;
    }*/
    /*
     * 上面的方法已经实现分布式锁功能 下面对此进行优化 处理请求超时 宕机 异常 等问题
     * 通过Jmeter 测试并发   通过集群测试  通过redis解决
     * */
    @GetMapping("/concurrenceManyRedis/{num}")
    public Object concurrenceManyRedis(@PathVariable("num") String key){
        String str = "空";
        //假如抢购一个商品  已此商品id为key
        String commodityId = "commodityId";
        //生成UUID 存放在key对应的值里
        String val = IdUtil.simpleUUID();
        try {
            //redis中如果有这个key就返回true 就往下执行 代表现在该商品没有人操作 新增成功了
            // 如果返回false代表有请求在执行中  然后让起等待或者直接返回 （现在直接给返回）
            //设置超时时间  如果下面代码报错或者服务挂了 该锁没有释放 那就是死锁了  设置了时间即使挂了 到时间自动失效解锁
            /*
             * <groupId>org.redisson</groupId> 加上redisson依赖 这玩意就返回为空了 应该是冲突了  测试该方法注释该依赖
             * */
            Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(commodityId, val,10, TimeUnit.SECONDS);
            //如果业务模块执行时间长  那就会出现锁失效 又有并发问题 所以需要在这开一个线程
            // 然后每隔超时时间的1/3 重新把它更新为10s 直到逻辑走完锁自己释放
            // 不在这代码实现  后面通过redisson来加锁  默认封装好的
            if(!aBoolean){
                return "系统繁忙请稍后再试";
            }
            String gz = stringRedisTemplate.opsForValue().get(key);
            Integer num = Integer.valueOf(gz);
            if(num>0){
                num--;
                stringRedisTemplate.opsForValue().set("gz",""+num);
                System.out.println("成功 还剩：\t"+num);
                str =  "成功 还剩：\t"+num;
            }else{
                System.out.println("库存不足");
                str = "库存不足";
            }
        } finally {
            //释放锁   放在finally里 防止代码异常 不释放锁死锁情况
            //该判断是如果设置的锁 过期了 那第一个请求执行15s 第二个请求执行8秒  10s后 锁失效 第二个请求加锁
            // 但是 5秒后第二个还没释放锁（不到10s）  第一个执行完了 他的锁过期了 他把后面的就释放了 因为key是一样的
            // 所以通过uuid 来让他释放自己的锁   但是解决了锁失效问题这个就不存在了
            if(val.equals(stringRedisTemplate.opsForValue().get(commodityId))){
                stringRedisTemplate.delete(commodityId);
            }
        }
        return str;
    }

    /*
     * 上面的方法已经实现分布式锁功能 下面对此进行优化 处理请求超时 宕机 异常 等问题
     * 通过Jmeter 测试并发   通过集群测试  通过redis解决
     * */
    @GetMapping("/concurrenceManyRedisson/{num}")
    public Object concurrenceManyRedisson(@PathVariable("num") String key){
        String str = "空";
        //假如抢购一个商品  已此商品id为key
        String commodityId = "commodityId";
        //Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(commodityId, val,10, TimeUnit.SECONDS);
        //添加锁
        RLock lock = redisson.getLock(commodityId);
        try {
            //当有请求过去后 其他请求阻塞等待   10s内请求走完释放锁 没走完里面继续更新10s（只要逻辑没走完就不会让锁超时）
            lock.lock(10,TimeUnit.SECONDS);
            String gz = stringRedisTemplate.opsForValue().get(key);
            Integer num = Integer.valueOf(gz);
            if(num>0){
                num--;
                stringRedisTemplate.opsForValue().set("gz",""+num);
                System.out.println("成功 还剩：\t"+num);
                str =  "成功 还剩：\t"+num;
            }else{
                System.out.println("库存不足");
                str = "库存不足";
            }
        }finally {
            //释放锁
            lock.unlock();
        }

        return str;
    }


}