package com.animaldemo;
/**
 * 鱼
 * */
public class Fish extends Animal{


    @Override
    public String breath() {
        return "以鳃呼吸";
    }
    /*
    * @parameter distance 距离/M
    * */
    @Override
    public double move(double distance) {
        double date = distance/SPEED;
        return date;
    }
}