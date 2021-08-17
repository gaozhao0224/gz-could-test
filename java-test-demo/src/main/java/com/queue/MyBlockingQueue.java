package com.queue;

import org.apache.tomcat.util.collections.SynchronizedQueue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 写入：如果队列满了就等待消费掉在写
 *  取： 如果队列是空的，必须阻塞等待生产
 *
 *
 *  方式          有返回值，抛出异常        有返回值，不抛出异常            阻塞 等待           超时等待
 *  添加          add                     offer()                     put()           offer(,,)
 *  移除          remove                  poll()                      take()          poll(,)
*  检测队首元素    element                   peek                           -               -
 * */
public class MyBlockingQueue {

    public static void main(String[] args) {
        /**
         *  arrayBlockingQueue 内部是数组结构  是有界的 必须给定长度 里面维护了 一个ReentrantLock 和 两个Condition
         *  入队的操作 ： 1.加锁
         *              2.判断当前的长度是否等于总长，是返回false 表示满了
         *              3.没满 入队 成功返回true
         *              4.解锁
         *  出队的操作：
         *               1.加锁
         *               2.如果队列为空，就阻塞，调用condition的await（）方法，将当前线程放入到同步条件队列中进行休眠
         *               3.不为空，正常出队，从头部出队（FIFO）
         *               4.解锁
         *  因为入队和出队用的是一把锁 同一时刻只能一个线程操作 效率会低
         *  LinkedBlockingQueue会效率高点 有两把锁 入队和出队不是同一把锁
         * */
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(3);
        /**
         *
         *
         *
         */
        LinkedBlockingQueue<Object> linkedBlockingQueue1 = new LinkedBlockingQueue<>(5);
        LinkedBlockingQueue<Object> linkedBlockingQueue2 = new LinkedBlockingQueue<>();






        SynchronizedQueue<String> synchronizedQueue = new SynchronizedQueue<>(5);
        //List<ArrayBlockingQueue<String>> arrayBlockingQueues = Arrays.asList(arrayBlockingQueue);
    }
}

class MyArrayBlockingQueueAddAndRemove{ // add 和 remove
    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.add("1");  //add 和 remove
        arrayBlockingQueue.add("2");
        arrayBlockingQueue.add("3");
        //arrayBlockingQueue.add("4"); //使用add超过长度会抛异常
        boolean remove = arrayBlockingQueue.remove("3");
        //String remove = arrayBlockingQueue.remove();



        for (String s : arrayBlockingQueue) {
            System.out.println(s);
        }
        System.out.println(remove);

    }
}
class MyArrayBlockingQueueOfferAndPoll{ // offer 和 poll 超过长度不会抛异常 会添加失败
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
//        boolean offer1 = arrayBlockingQueue.offer("1");
//        boolean offer2 = arrayBlockingQueue.offer("2");
//        boolean offer3 = arrayBlockingQueue.offer("3");
//        boolean offer4 = arrayBlockingQueue.offer("4");
//        System.out.println(offer1+" "+offer2+" "+offer3+" "+offer4);
//        //arrayBlockingQueue.poll();
//        //arrayBlockingQueue.poll();
//        //arrayBlockingQueue.poll();
//        arrayBlockingQueue.poll();//先进先出
//        String peek = arrayBlockingQueue.peek();
//        System.out.println(peek);
//
//        for (String s : arrayBlockingQueue) {
//            System.out.println(s);
//        }

        //超时等待
        boolean offer1 = arrayBlockingQueue.offer("1",3 ,TimeUnit.SECONDS);
        boolean offer2 = arrayBlockingQueue.offer("2");
        boolean offer3 = arrayBlockingQueue.offer("3");
        boolean offer4 = arrayBlockingQueue.offer("4");
        System.out.println(offer1+" "+offer2+" "+offer3+" "+offer4);
        //arrayBlockingQueue.poll();
        //arrayBlockingQueue.poll();
        //arrayBlockingQueue.poll();
        arrayBlockingQueue.poll();//先进先出
        String peek = arrayBlockingQueue.peek();
        System.out.println(peek);

        for (String s : arrayBlockingQueue) {
            System.out.println(s);
        }

    }
}


class MyArrayBlockingQueueConcurrenceTest{ // 测试并发操作阻塞队列
    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        System.out.println(arrayBlockingQueue.size());
//        for (int i = 0; i < 10; i++) {
//            //System.out.println(i);
//            boolean offer = arrayBlockingQueue.offer("1");
//            if(!offer){
//                break;
//            }
//        }
        for (int i = 0; i < 100; i++) {
            final int flag = i;
            new Thread(()->{
                for (int j = 0; j < 3; j++) {
                    //System.out.println(i);
                    boolean offer = arrayBlockingQueue.offer(Thread.currentThread().getName()+"_"+j);
                    if(!offer){
                        break;
                    }
                }
            },""+i).start();
        }
        System.out.println("结束后"+arrayBlockingQueue.size());
        for (String s : arrayBlockingQueue) {
            System.out.println(s);
        }


    }
}