package com.test.enumtest;
/**
 * 使用枚举作为参数
 * */
public class Test {

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setStateEnum(StateEnum.CG);
        System.out.println(cat.getStateEnum().key());
    }
    
}