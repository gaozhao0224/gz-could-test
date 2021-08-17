package com.mythread.pool;

import cn.hutool.core.date.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CountDownLatch countDownLatch = new CountDownLatch(6);
        // 自定义线程池！工作 ThreadPoolExecutor
            ExecutorService threadPool = new ThreadPoolExecutor(
                    1,
                    10,
                    3,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(3),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy()); //丢弃任务并抛出RejectedExecutionException异常。  【默认】
                    //new ThreadPoolExecutor.DiscardPolicy());//也是丢弃任务，但是不抛出异常。
                    //new ThreadPoolExecutor.CallerRunsPolicy());//由调用线程处理该任务 【谁调用，谁处理】
                    //new ThreadPoolExecutor.DiscardOldestPolicy()); //队列满了，尝试去和
                    //最早的竞争，也不会抛出异常！
            try {
                // 最大承载：Deque + max
                // 超过 RejectedExecutionException
                for (int i = 1; i <= 6; i++) {
                    // 使用了线程池之后，使用线程池来创建线程
                    threadPool.execute(()->{

                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+" ok");
                        countDownLatch.countDown();
                    });
                    //                4种拒绝策略
                    //                 小结和拓展
                    //                池的最大的大小如何去设置！
                    //
                    //                了解：IO密集型，CPU密集型：（调优）
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 线程池用完，程序结束，关闭线程池
                threadPool.shutdown();
            }

        countDownLatch.await();
        stopWatch.stop();
        System.out.println("时间:"+stopWatch.getLastTaskTimeMillis());



        List<Object> list = new ArrayList<>();
        list.parallelStream();
    }

}