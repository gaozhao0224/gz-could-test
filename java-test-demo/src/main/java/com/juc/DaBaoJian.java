package com.juc;

import cn.hutool.core.date.StopWatch;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class DaBaoJian {

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = new ThreadPoolExecutor(
                500,
                10000,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        StopWatch watch = new StopWatch();
        watch.start();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            threadPool.execute(()->{
                send(Thread.currentThread().getName());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
//        for (int i = 0; i < 2; i++) {
//            send(Thread.currentThread().getName());
//        }
        watch.stop();
        System.out.println("耗时："+watch.getLastTaskTimeMillis());
    }


    public static void send(String name){
        try {

            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程："+name+"   执行完毕；");
    }


}
/**
 * ThreadPoolExecutor 的  beforeExecute() 和 afterExecute（）方法,
 * 不是继承自 AbstractExecutorService , 这是设计上的一个败笔。
 * 例如 netty 就是去实现 AbstractExecutorService的
 *
 * 线程池问题：如何获取线程池ThreadPoolExecutor正在运行的线程？
 */
class ThreadPoolExecutorThreadQuestion {
    public static void main(String[] args) throws InterruptedException {

        //main 线程启动子线程，子线程的创造来自于 Executors.defaultThreadFactory()

        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义线程容器,通过java的引用类型记录数据。
        Set<Thread> threadsContainer = new HashSet<>();
        //自定义的方法
        setThreadFactory(executorService, threadsContainer);

        for (int i = 0; i < 500; i++) {
            executorService.submit(() -> {
            });
        }
        //线程池等待执行 3 ms
        executorService.awaitTermination(3, TimeUnit.MILLISECONDS);

        threadsContainer.stream().
                //过滤调不存活
                        filter(Thread::isAlive).
                forEach(thread ->  System.out.println("方法1：线程池的线程：" + thread)
                );

        //方法二：
        Thread mainThread = Thread.currentThread();
        ThreadGroup mainThreadThreadGroup = mainThread.getThreadGroup();
        //获取线程组中的线程。
        int count = mainThreadThreadGroup.activeCount();
        System.out.println("count:"+count);
        Thread[] threads = new Thread[count];
        //enumerate 枚举，recurse 递归
        mainThreadThreadGroup.enumerate(threads, true);
        Stream.of(threads).filter(Thread::isAlive).forEach(thread -> System.out.println("方法2：线程池的线程：" + thread ));

        //关闭线程池
        executorService.shutdown();

        /**
         *  输出结果：
         方法1：线程池的线程：Thread[pool-1-thread-3,5,main]
         方法1：线程池的线程：Thread[pool-1-thread-1,5,main]
         方法1：线程池的线程：Thread[pool-1-thread-4,5,main]
         方法1：线程池的线程：Thread[pool-1-thread-5,5,main]
         方法1：线程池的线程：Thread[pool-1-thread-2,5,main]
         count:7
         方法2：线程池的线程：Thread[main,5,main]     主线程
         方法2：线程池的线程：Thread[Monitor Ctrl-Break,5,main]  控制中断监视器
         方法2：线程池的线程：Thread[pool-1-thread-1,5,main]
         方法2：线程池的线程：Thread[pool-1-thread-2,5,main]
         方法2：线程池的线程：Thread[pool-1-thread-3,5,main]
         方法2：线程池的线程：Thread[pool-1-thread-4,5,main]
         方法2：线程池的线程：Thread[pool-1-thread-5,5,main]
         */

    }

    private static void setThreadFactory(ExecutorService executorService,Set<Thread> threadsContainer){
        if (executorService instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
            //获取线程工厂
            ThreadFactory oldThreadFactory = threadPoolExecutor.getThreadFactory();
            //在把线程工程设置到包装类 DelegatingThreadFactory ，再设置回线程池。
            threadPoolExecutor.setThreadFactory(new MyThreadFactory(oldThreadFactory,threadsContainer));
        }

    }
    //我的的线程工厂
    private static class MyThreadFactory implements ThreadFactory {

        private final ThreadFactory threadFactory;
        private final Set<Thread> threadsContainer;

        private MyThreadFactory(ThreadFactory threadFactory, Set<Thread> threadsContainer) {
            this.threadFactory = threadFactory;
            this.threadsContainer = threadsContainer;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = threadFactory.newThread(r);
            //cache thread 记录线程
            threadsContainer.add(thread);
            //删除不存活的线程
//            threadsContainer.removeIf(next -> !next.isAlive());
            return thread;
        }
    }
}