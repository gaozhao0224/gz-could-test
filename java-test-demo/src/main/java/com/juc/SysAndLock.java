package com.juc;

import java.util.concurrent.TimeUnit;

public class SysAndLock {
    /**
     * synchronized 如果是普通方法 那锁的就是调用者 下面的调用者就是对象本身 所以即使打电话有延迟3秒  还是顺序执行 因为打电话的时候把对象锁了
     *
     * 如果在创建一个对象 那就并行了 锁的不是一个对象
     *
     * 如果是静态的  锁的就是模板 即class本身  不管创建多少个对象 都会先把第一次进入方法的执行完 解锁后 别的才能执行
    * */
    public static void main(String[] args) {
        Phone phone = new Phone();
        phoneStatic phonestatic = new phoneStatic();
        phoneStatic phonestatic1 = new phoneStatic();
//        new Thread(()->{
//            phone.call();
//        },"A").start();
//        new Thread(()->{
//            phone.cms();
//        },"B").start();
//

        new Thread(()->{
            phonestatic.call();
        },"C").start();
        new Thread(()->{
            phonestatic1.cms();
        },"D").start();

    }
}

class Phone{

    public synchronized void call(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------->>打电话");
    }
    public synchronized void cms(){
        System.out.println("------->>发短信");
    }


}

class phoneStatic{

    public static synchronized void call(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------->>打电话");
    }
    public static synchronized void cms(){
        System.out.println("------->>发短信");
    }


}