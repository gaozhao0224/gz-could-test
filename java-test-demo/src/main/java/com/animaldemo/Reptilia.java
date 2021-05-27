package com.animaldemo;
/**
 * 爬行类生物
 * */
public class Reptilia extends Animal{
    @Override
    public String breath() {
        return "以肺呼吸";
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