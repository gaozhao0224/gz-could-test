package com.mythread;
/**
 * 多线程抢票
 * */
public class ScrambleTickets {
    static int num = 100;
    public static void main(String[] args) {
        //同时N个人过来抢100张票 避免多线程的问题 可以加synchronized
        for (int i = 0; i < 1000; i++) {
            if (num<=0){
                break;
            }
            new Thread(()->{
                String name = Thread.currentThread().getName();
                buyTickets(name);
            }).start();
        }
    }
    //synchronized
    public static synchronized void buyTickets(String name){
        if(num>0) {
            num--;
            System.out.println(name + "抢到一张票，剩余" + num);
        }
    }


}