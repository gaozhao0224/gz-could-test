package com.zz;

import java.util.concurrent.TimeUnit;

public class TestMP {
    static int num = 100;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    getNum();
                } catch (InterruptedException e) {
                    e.printStackTrace();//
                }
            }).start();
        }

    }

    public static synchronized void getNum() throws InterruptedException {
        num--;
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(num);
    }
}