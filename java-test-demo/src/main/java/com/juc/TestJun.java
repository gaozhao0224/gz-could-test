package com.juc;

import com.copy.Dog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

@Slf4j
public class TestJun {

    @Test
    public void test1(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int num = 0;
        for (int i = 0; i < 1000000; i++) {
            Dog dog = new Dog();
            //log.info("123");
            num++;
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis()+"     num = "+num);

    }



    /**
    * 跳出和结束都是当前循环 想直接出循环需要搞标识位 让外层结束  return 直接结束方法
    * */
    @Test
    public void test2(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("j="+j);
                if (j==5){
                    break;
                }
            }
            System.out.println("i="+i);
        }
        System.out.println("66666666666");
    }
    @Test
    public void test3(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("j="+j);
                if (j==5){
                    return;
                }
            }
            System.out.println("i="+i);
        }
        System.out.println("66666666666");
    }







}

