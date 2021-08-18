package com.example.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.util.TimeUtils;

import java.util.concurrent.*;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestWebController {

    @GetMapping("/a/{id}")
    public String getTest(@PathVariable("id") String gz) throws InterruptedException {
        String now = DateUtil.now();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(now);
        return now;
    }

    public String getA(){
        // 自定义线程池！工作 ThreadPoolExecutor
        ExecutorService threadPool = new ThreadPoolExecutor(
                1000,
                2000,
                1500,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()); //丢弃任务并抛出RejectedExecutionException异常。  【默认】
                //new ThreadPoolExecutor.DiscardPolicy());//也是丢弃任务，但是不抛出异常。
                //new ThreadPoolExecutor.CallerRunsPolicy());//由调用线程处理该任务 【谁调用，谁处理】
                new ThreadPoolExecutor.DiscardOldestPolicy()); //队列满了，尝试去和
//        最早的竞争，也不会抛出异常！
        try {
// 最大承载：Deque + max
// 超过 RejectedExecutionException
            for (int i = 1; i <= 5000; i++) {
                System.out.println(i);
// 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
//                4种拒绝策略
//                 小结和拓展
//                池的最大的大小如何去设置！
//                了解：IO密集型，CPU密集型：（调优）
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
// 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
        return "ok";
    }
}