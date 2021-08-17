package com.shouxiedaima;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {

    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{for (int i = 0; i < 10; i++) myData.getA(); },"A").start();
        new Thread(()->{for (int i = 0; i < 10; i++) myData.getB(); },"B").start();
        new Thread(()->{for (int i = 0; i < 10; i++) myData.getC(); },"C").start();
    }
}



class MyData{

    ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition1 = reentrantLock.newCondition();
    Condition condition2 = reentrantLock.newCondition();
    Condition condition3 = reentrantLock.newCondition();
    int num=1;
    public void getA(){
        reentrantLock.lock();
        try {
            while (num!=1){
                condition1.await();
            }
            num=2;
            System.out.println(Thread.currentThread().getName()+"执行输出A");
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
    public void getB(){
        reentrantLock.lock();
        try {
            while (num!=2){
                condition2.await();
            }
            num=3;
            System.out.println(Thread.currentThread().getName()+"执行输出B");
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
    public void getC(){
        reentrantLock.lock();
        try {
            while (num!=3){
                condition3.await();
            }
            num=1;
            System.out.println(Thread.currentThread().getName()+"执行输出C");
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }



}