package com.crc.demo;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
        Integer a = 127;
        Integer b = 128;
        Integer cc = -129;
        Integer c = -128;
        Integer d = -127;

        System.out.println(a==127);
        System.out.println(a==128);
        System.out.println(c==-128);
        System.out.println(d==-127);
        System.out.println(d==-129);
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
        List<String> strings = Arrays.asList("1", "2", "3", "4", "5");
        for (int i = 0; i < 6; i++) {
            System.out.println(strings.get(i));
        }
    }
    @Test
    public void test10(){
        List<String> list = Arrays.asList("1","1","2","3","4");
        System.out.println(list.size());
        list = list.stream().filter(s->{
            return s.equals("1");
        }).collect(Collectors.toList());
        System.out.println(list.size());
    }
}