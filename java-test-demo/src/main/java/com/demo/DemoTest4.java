package com.demo;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class DemoTest4 {
    @Test
    public void test1(){

//        int a = 30;
//        int b = 20;
//
//        if(a>b){
//            System.out.println("11111111111");
//        }else{
//            System.out.println("22222222222");
//        }
//
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println("111111111");
//        }
        Integer [] a = {110,120,114};

        for (Integer s : a) {
            if(114==s){
                a[0]=114;
                a[2]=110;
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);

        }





















    }
    @Test
    public void test2(){
        System.out.println("11111111111");
    }
    @Test
    public void test3(){

    }
    @Test
    public void test4(){

    }
    @Test
    public void test5(){

    }
    @Test
    public void test6(){

    }
    @Test
    public void test7(){

    }
    @Test
    public void test8(){

    }
    @Test
    public void test9(){

    }
    @Test
    public void test10(){

    }
}