package com.crc.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

@Slf4j
public class A计算方法时间 {
    @Test
    public void test1() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 5; i++) {
            TimeUnit.SECONDS.sleep(1);
        }
        stopWatch.stop();
        log.error("查询缓存耗时{}",stopWatch.getLastTaskTimeMillis());
    }
}