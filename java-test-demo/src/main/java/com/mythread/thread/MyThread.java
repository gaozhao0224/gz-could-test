package com.mythread.thread;

import lombok.SneakyThrows;

/**
 * 1.继承thread类  重写 run 方法
 *
 * */
public class MyThread extends Thread{

    /**
     * 线程上面的方法  会先执行完在执行线程。  但是线程下面的代码给线程是异步的 线程自己执行自己的  主线程继续往后面走
     * */
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 50; i++) {
            System.out.println("主线程在开启线程之前》》》》》》》》》"+i);
            //Thread.sleep(100);
        }
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
        for (int i = 0; i < 50; i++) {
            System.out.println("主线程的我开启线程之后>>>>>>>"+i);
            //Thread.sleep(100);
        }

    }

    @SneakyThrows
    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        for (int i = 0; i < 50; i++) {
            System.out.println(name+"线程开始运行了>>>>>>>"+i);
            //Thread.sleep(100);
        }
    }
}