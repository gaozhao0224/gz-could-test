package com.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DemoTest5 {
    @Test
    public void test1(){
        String a = "";
        for (int i = 0; i < 3; i++) {
            a+=","+i;
        }
        System.out.println(a);
        System.out.println(a.substring(1));
    }
    @Test
    public void test2(){

    }
    @Test
    public void test3(){

    }
    @Test
    public void test4(){

    }
    @Test
    public void test5(){
        String str = "https://www.baidu.com/884-00.jpg\"><br><im";
        String substring = str.substring(str.lastIndexOf("."));
        System.out.println(str.lastIndexOf("."));
        System.out.println(substring);
        System.out.println(str.substring(0,str.lastIndexOf(".")+4));
        System.out.println(str.substring(0,str.lastIndexOf(".")));

    }
    @Test
    public void test6(){
        List<Dog> strings = new ArrayList<>();
        Dog dog1 = new Dog();
        dog1.setName("111");
        Dog dog2 = new Dog();
        dog2.setName("222");
        Dog dog3 = new Dog();
        dog3.setName("333");
        strings.add(dog1);
        strings.add(dog2);
        strings.add(dog3);
        strings.stream().forEach(i->{
            i.setName(i.getName()+">>>>>>>>>");
        });
//        for (String string : strings) {
//            string+=">>>";
//        }
        System.out.println(strings);
    }
    @Test
    public void test7(){
        long a = 1000;
        int b = 1000;
        System.out.println(a==b);
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