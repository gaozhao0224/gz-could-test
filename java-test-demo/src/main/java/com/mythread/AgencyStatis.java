package com.mythread;
/*
* 接口
* */
interface Married {
    void operate();
}

/**
 * 静态代理
 * 代理类和真实类都实现统一接口
 * 创建真实对象并且传到代理对象中 通过代理对象强化功能
 * */
public class AgencyStatis {

    public static void main(String[] args) {
        //创建代理对象 将真实对象传入  代理对象增强功能
        Me me = new Me();
        new Agency(me).operate();
    }
}

/*
* 真实类
* */
class Me implements Married{

    @Override
    public void operate() {
        System.out.println("我结婚了！！！");
    }
}

/*
 * 代理类
 * */
class Agency implements Married{

    private Married married;

    public Agency(Married married) {
        this.married = married;
    }

    @Override
    public void operate() {
        before();
        married.operate();
        later();
    }
    //前置
    public void before(){
        System.out.println("婚庆公司：结婚前操办");
    }
    //后置
    public void later(){
        System.out.println("婚庆公司：结婚后结账");
    }
}