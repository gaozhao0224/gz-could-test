package com.mythread;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程
 * */
public class Daemon {
    public static void main(String[] args) throws InterruptedException {
        boolean b = true;
        new Thread(()->{
            int flag = 0;
            while (b){
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag++;
                if (flag>10){
                    break;
                }
                System.out.println("我是一个普通的线程111111>>>>>>>>>>>.");
            }
        }).start();
        new Thread(()->{
            int flag = 0;
            while (b){
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag++;
                if (flag>10){
                    break;
                }
                System.out.println("我是一个普通的线程22222>>>>>>>>>>>.");
            }
        }).start();

        MyThreadDaemon myThreadDaemon = new MyThreadDaemon();
        Thread thread = new Thread(myThreadDaemon);
        thread.setDaemon(true);
        thread.start();
        //TimeUnit.SECONDS.sleep(3);
        System.out.println("主线程>>>>>>>>>>>>>>>..");

    }

}


class MyThreadDaemon implements Runnable{
    @Override
    public void run() {
        while (true){
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("守护线程开启中。。。");
        }
    }
}