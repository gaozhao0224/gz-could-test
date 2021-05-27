package com.animaldemo;
/**
 * 动物类
* */
public abstract class Animal {

    //速度 不能改变 定义常量  假设动物的速度为10M/s
    public static final double SPEED = 10;
    //呼吸方式
    public abstract String breath();
    //计算制定路程所需时间
    public abstract double move(double distance);
}