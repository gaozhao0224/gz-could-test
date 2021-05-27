package com.animaldemo;
/**
 * 测试类
 * */
public class Test {

    //(4) 测试代码
    public static void main(String[] args) {
        Fish fish = new Fish();
        Reptilia reptilia = new Reptilia();
        //(1) 返回呼吸方式
        String breathFish = fish.breath();
        System.out.println("鱼的呼吸方式:"+breathFish);
        String breathReptilia = reptilia.breath();
        System.out.println("爬行动物的呼吸方式:"+breathReptilia);
        //(2)动物的运行速度
        System.out.println("鱼的速度:"+Fish.SPEED+"M/S");
        System.out.println("爬行动物的速度:"+Reptilia.SPEED+"M/S");
        //(3)指定距离所需的时间值/S
        double distance = 1000l;
        double moveFish = fish.move(distance);
        System.out.println("鱼游"+distance+"M,需要"+moveFish+"S");
        distance = 2000l;
        double moveReptilia = fish.move(distance);
        System.out.println("爬行动物爬"+distance+"M,需要"+moveReptilia+"S");

    }


}