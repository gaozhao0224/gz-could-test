package com.juc;

import cn.hutool.core.date.StopWatch;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

public class StreamTest {


    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Long sumFor = getSumFor(25_0000_0000L);  //2000000001000000000
            stopWatch.stop();
            System.out.println(Thread.currentThread().getName()+"时间:"+stopWatch.getLastTaskTimeMillis()+"\t结果:"+sumFor);
        },"A").start();

        new Thread(()->{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Long sumFor = getSumStream(25_0000_0000L);//2000000001000000000
            stopWatch.stop();
            System.out.println(Thread.currentThread().getName()+"时间:"+stopWatch.getLastTaskTimeMillis()+"\t结果:"+sumFor);
        },"B").start();
    }



    public static Long getSumFor(Long i){
        Long sum = 0L;
        for (int j = 0; j <= i; j++) {
            sum+=j;
        }
        return sum;
    }
    public static Long getSumStream(Long i){
        long count = LongStream.rangeClosed(0,10).parallel().reduce(0,Long::sum);
        return count;
    }

}