package com.juc;

import org.springframework.util.StopWatch;

import java.util.concurrent.*;

public class CountDownLatchPool {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 自定义线程池！工作 ThreadPoolExecutor
        ExecutorService threadPool = new ThreadPoolExecutor(
                5,
                10,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()); //丢弃任务并抛出RejectedExecutionException异常。  【默认】
                //new ThreadPoolExecutor.DiscardPolicy());//也是丢弃任务，但是不抛出异常。
                //new ThreadPoolExecutor.CallerRunsPolicy());//由调用线程处理该任务 【谁调用，谁处理】
                //new ThreadPoolExecutor.DiscardOldestPolicy()); //队列满了，尝试去和最早的竞争，也不会抛出异常！
        CountDownLatch countDownLatch = new CountDownLatch(5);
        try {
            // 最大承载：Deque + max
            // 超过 RejectedExecutionException
            for (int i = 1; i <= 5; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                /**
                 * submit 有返回值底层也是execute 只是返回一个futrue函数
                 * */
                /*Future<String> submit = threadPool.submit(() -> {
                    return "123";
                });
                System.out.println(submit.get());*/

                threadPool.execute(()->{
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" ok");
                    countDownLatch.countDown();  /**  必须放在线程执行最后   不然多线程进来直接执行了 后面逻辑没走完；    */
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
        countDownLatch.await();
        stopWatch.stop();

        System.out.println("结束,用时:"+stopWatch.getLastTaskTimeMillis());
    }

}