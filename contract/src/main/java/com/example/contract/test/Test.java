package com.example.contract.test;

import com.example.contract.test.entity.Cat;
import com.example.contract.test.entity.Dog;

public class Test {

    /*
    * 浅拷贝就是 ： 克隆对象 就是生成了一个新的对象和被克隆对象的基本数据值是一样的  比如基本数据类型包装类型String等
    * 这样克隆出来的对象有一部分是给克隆前是分开的独立的 但是还有一部分是关联的  这时候也叫浅克隆
    *
    *深拷贝就是 ：完全的克隆对象 就是连带着对象中的属性对象一起克隆   因为浅克隆过来的对象是固定的一个地址 指向堆内存的一个地址
    * 所以如果更改了这个对象 那克隆前后都会改变 这不是我们要的效果   所以需要把他也单独克隆  像下面的代码一样
    *
    * 最后的解决办法可以通过流 避免递归克隆
    *
    * */

    public static void main(String[] args) throws Exception {
        Cat cat = new Cat("猫", "20", "男");
        Dog dog = new Dog("大狗子", "9", "男",cat);
        Dog cloneDog = dog.clone();  //克隆一个dog对象
        Cat cloneCat = dog.getCat().clone();//如果不克隆dog对象里的cat 那cat修改两个人都会改变
        cloneDog.setCat(cloneCat);
        cat.setNameCat("大猫");
        dog.setCat(cat); //如果不克隆dog对象里的cat 那cat修改两个人都会改变
        System.out.println(dog+"\t\t"+System.identityHashCode(dog));
        System.out.println(cloneDog+"\t\t"+System.identityHashCode(cloneDog));
    }
}