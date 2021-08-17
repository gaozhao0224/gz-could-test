package com.lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class CAS_Test {

    @Test
    public void test1(){
        List<String> centres = new ArrayList<String>();
        centres.add("1");
        centres.add("2");
        centres.add("3");
        List<String> collect = centres.stream().map(c -> {
            c = "6";
            return c;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }
    @Test
    public void test2(){
        List<String> centres = new ArrayList<String>();
        centres.add("1");
        centres.add("2");
        centres.add("3");
        centres.stream().forEach(c->{
            c="6";
        });
        System.out.println(centres);
    }
    @Test
    public void test3(){
        List<String> centres = new ArrayList<String>();
        centres.add("1");
        centres.add("2");
        centres.add("3");
        for (String centre : centres) {
            centre="6";
        }
        System.out.println(centres);
    }
    @Test
    public void test4(){
        List<Dog> centres = new ArrayList<Dog>();
        Dog dog1 = new Dog("1");
        Dog dog2 = new Dog("2");
        Dog dog3 = new Dog("3");
        centres.add(dog1);
        centres.add(dog2);
        centres.add(dog3);
        for (Dog centre : centres) {
            centre.setName("123");
        }
        System.out.println(centres);


    }
    @Test
    public void test5(){
        List<Dog> centres = new ArrayList<Dog>();
        Dog dog1 = new Dog("1");
        Dog dog2 = new Dog("2");
        Dog dog3 = new Dog("3");
        centres.add(dog1);
        centres.add(dog2);
        centres.add(dog3);
        centres.stream().map(c->{
            c.setName("6");
            return c;
        });
        System.out.println(centres);
    }
    @Test
    public void test6(){
        List<Dog> centres = new ArrayList<Dog>();
        Dog dog1 = new Dog("1");
        Dog dog2 = new Dog("2");
        Dog dog3 = new Dog("3");
        centres.add(dog1);
        centres.add(dog2);
        centres.add(dog3);
        centres.stream().forEach(c->{
            c.setName("1");
        });
        System.out.println(centres);
    }




}