package com.anonymity;

import com.sun.xml.internal.bind.v2.runtime.Name;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {

        /*
        * 匿名内部类 没有类名 属于父类型的  省去了创建新类这个步骤 直接在父类型后的大括号重写或者实现方法然后直接使用
        * 不用去创建一个新的类去实现继承然后new
        * 之所以说只能用一次 是因为一次就是一个匿名内部类多次修改就是多个了  而不是像创建一个真是存在的类一样一直去根据它这个模板去new 去用
        * 一般用用继承抽象类或者实现接口的时候使用 比如new Thread(){}
        * */

        StopWatch stopWatch = new StopWatch();//spring-framework提供了一个StopWatch类可以做类似任务执行时间控制，也就是封装了一个对开始时间，结束时间记录操作的Java类
        stopWatch.start();
        PersonAbstr PersonAbstrObj1 = new PersonAbstr() {
            @Override
            public void getPerson() {
                setName("第一个匿名内部类更改后的name");
                System.out.println("第一个匿名内部类");
            }
        };
        PersonAbstr PersonAbstrObj2 = new PersonAbstr() {
            @Override
            public void getPerson() {
                setName("第二个匿名内部类更改后的name");
                System.out.println("第二个匿名内部类");
            }
        };
        //使用第一个匿名内部类（继承抽象类）
        PersonAbstrObj1.getPerson();
        System.out.println(PersonAbstrObj1);
        System.out.println("----------------------------------");
        //使用第二个匿名内部类（继承抽象类）
        PersonAbstrObj2.getPerson();
        System.out.println(PersonAbstrObj2);
        System.out.println("-----------------上面是关于抽象类的  下面是关于接口的-----------------");

        PersonInte personInteImpl1 = new PersonInte() {
            String NAME = "666";
            @Override
            public String toString() {
                return "PersonInteImpl{" +
                        "NAME='" + NAME + '\'' +
                        '}';
            }
            @Override
            public void getPersonInte() {
                System.out.println("第一个接口的实现匿名内部类");
            }
        };
        PersonInte personInteImpl2 = new PersonInte() {
            String NAME = "777";
            @Override
            public String toString() {
                return "PersonInteImpl{" +
                        "NAME='" + NAME + '\'' +
                        '}';
            }
            @Override
            public void getPersonInte() {
                System.out.println("第二个接口的实现匿名内部类");
            }
        };
        personInteImpl1.getPersonInte();
        System.out.println(personInteImpl1.toString());
        System.out.println("----------------------------------");
        personInteImpl2.getPersonInte();
        System.out.println(personInteImpl2.toString());
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        stopWatch.stop();
        System.out.println("一共执行"+stopWatch.getLastTaskTimeMillis());


        /*
        * 匿名内部类
        * */
//        new Thread(){
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println(i);
//                }
//            }
//        }.start();
    }

}