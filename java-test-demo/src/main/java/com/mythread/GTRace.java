package com.mythread;
/**
* 龟兔赛跑
* */
public class GTRace {
    //胜利者
    private static String winner;


    public static void main(String[] args) {

        new Thread(()->{
            Thread.currentThread().setName("兔子");
            try {
                gameCourse();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            Thread.currentThread().setName("乌龟");
            try {
                gameCourse();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    //开始比赛
    public static void gameCourse() throws Exception {
        String name = Thread.currentThread().getName();
        for (int i = 0; i <= 10000; i++) {
            /**
            * 兔子等待 让乌龟胜利
            * */
            if (name.equals("兔子")){
                Thread.sleep(1);
            }
            boolean b = gameOver(i);
            if (b){
                break;
            }
            System.out.println(name+"跑了"+i+"步");
        }
    }
    public static boolean gameOver(int num){
        //有胜利者了
        if (winner!=null){
            return true;
        }
        if (num>=10000){
            winner = Thread.currentThread().getName();
            System.out.println(Thread.currentThread().getName()+"到终点了！！！");
            return true;
        }
        return false;
    }
}