package com.mythread;
/**
* 线程 interrupt 和  isInterrupted 的用法
 * interrupt 中断线程
 * isInterrupted 判断是否是中断线程
 *
* */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(1); // 暂停1毫秒
        System.out.println(t.isInterrupted());
        t.interrupt(); // 中断t线程
        System.out.println(t.isInterrupted());
        t.join(); // 等待t线程结束
        System.out.println("end");
    }
}
 
class MyThread extends Thread {
    @Override
    public void run() {
        int n = 0;

        while (! isInterrupted()) {
            n ++;
            System.out.println("当前是是否是中断状态"+isInterrupted()+"；第几次>"+n + " hello!");
        }
    }
}
