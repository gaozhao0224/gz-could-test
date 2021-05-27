package com.mythread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
/**
*   关于 volatile 关键字
*   线程之间的原子性  即使是在多个线程一起执行的情况下，一旦一个操作开始执行，就不会受其他的影响
 *  比如 refresh（） 修改了  initFlag 为 true  但是 load（）线程并不知道  所以还在继续执行死循环
 *  这时候如果我们想一个线程修改了主内存或者说主线程里的公共变量  其他的线程也能有所感知  需要用到 volatile关键字
 *  被volitile修饰后 在被线程修改后其他线程也能感知到这个的修改   然后load（）里感知到 initFlag 为 true 循环就停下来了
 *
 *
 *  (遗留问题：死循环里如果有输出不管是打印的out还是log都直接通知了  即使没有volatile 也实现了他的功能 不知道为什么
 *  龟兔赛跑也有这个原因 按理说线程就跑一次 第一次拿到胜利者是null 那他这个线程应该跑完 而不是因为另一个赋值 就停)
 *
 *  查询结果：：：：
 *      volatile关键字解决的是可见性问题：当一个线程修改了某个共享变量的值，其他线程能够立刻看到修改后的值。
 *      如果我们去掉volatile关键字，运行上述程序，发现效果和带volatile差不多，这是因为在x86的架构下，JVM回写主内存的速度非常快，但是，换成ARM的架构，就会有显著的延迟。
 *      所以之前遇到的疑惑 比如死循环里有了输出 就感知不到volatile的用途了 可能就是因为输出占用时间 jvm快速的刷入了主内存  导致的
* */
@Slf4j
public class TestVolatile {


    public boolean initFlag = false;
    public static void main(String[] args) throws Exception {
        TestVolatile testVolatile = new TestVolatile();

        Thread threadLoad = new Thread(() -> {
            testVolatile.load();
        });

        Thread threadRefresh = new Thread(() -> {
            testVolatile.refresh();
        });
        threadLoad.start();
        TimeUnit.SECONDS.sleep(1);
        threadRefresh.start();
    }

    public void load(){
        String name = Thread.currentThread().getName();
        boolean bl = initFlag;
        while (!initFlag){
            //log.info(name+"循环中>>>>>>>>>="+initFlag);
            //System.out.println(name+"循环中>>>>>>>>>="+initFlag);
        }
        System.out.println("线程"+name+"感应到initFlag的改变initFlag="+initFlag+"第一次进来就复制过来的值"+bl);
    }
    public void refresh(){
        String name = Thread.currentThread().getName();
        initFlag = true;
        System.out.println("线程"+name+"将initFlag改变，改变后的initFlag="+initFlag);
    }

}