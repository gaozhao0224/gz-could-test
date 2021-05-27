package com.mythread.runnable;

import lombok.SneakyThrows;

public class MyRunnable implements Runnable{
    public static void main(String[] args) {

        MyRunnable myRunnable1 = new MyRunnable();
        MyRunnable myRunnable2 = new MyRunnable();
        MyRunnable myRunnable3 = new MyRunnable();
        new Thread(myRunnable1).start();
        new Thread(myRunnable2).start();
        new Thread(myRunnable3).start();
        /**
         * 简写 通过lambda表达式
         * */
        new Thread(()->{
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 300; i++) {
                System.out.println(name+"线程开始执行了>>>>>>>>"+i);
            }
        }).start();


    }

    @SneakyThrows
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 300; i++) {
            System.out.println(name+"线程开始执行了>>>>>>>>"+i);
            Thread.sleep(10);
        }
    }


}