package com.juc;

import com.lock.Dog;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如何让三个线程交替执行 比如输出abc abc 输出n次
 * 通过 juc里的 lock锁和 Condition
 * */
public class A_B_C {
    public static void main(String[] args) {
        Data3 data = new Data3();
        System.out.println(data);//com.juc.Data3@1b2c6ec2
        //com.juc.Data3@1b2c6ec2
        Dog dog = new Dog();
        System.out.println(dog);
        new Thread(()->{for (int i = 0; i <10 ; i++)data.printA(); },"A").start();
        new Thread(()->{for (int i = 0; i <10 ; i++)data.printB(); },"B").start();
        new Thread(()->{for (int i = 0; i <10 ; i++)data.printC(); },"C").start();
    }
}
//操作资源对象
class Data3{ // 资源类 Lock
    Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1; // 1A 2B 3C
    public void printA(){
        lock.lock();
        try {
            // 业务，判断-> 执行-> 通知
            while (number!=1){
                // 等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>AAAAAAA");
            // 唤醒，唤醒指定的人，B
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            // 业务，判断-> 执行-> 通知
            while (number!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>BBBBBBBBB");
            // 唤醒，唤醒指定的人，c
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            // 业务，判断-> 执行-> 通知
            while (number!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>CCCCCCCCC");
            // 唤醒，唤醒指定的人，c
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}