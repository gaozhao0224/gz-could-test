package com.copy;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeanUtilsCopy {

    public static void main(String[] args) {
        /*
        * 测试是深copy还是浅copy
        * */
        Animal animal = new Animal();
        Animal animalCopy = new Animal();
        animal.setColor("red");
        Dog dog = new Dog();
        dog.setDogName("狗子一号");
        dog.setDogAge("20");
        dog.setDogSex("男");
        animal.setDog(dog);
        BeanUtils.copyProperties(animal,animalCopy);
        System.out.println(animalCopy);
        dog.setDogName("换个狗子");
        System.out.println(animalCopy);
        System.out.println("结论 ： 深克隆");
        System.out.println("-------------------------上面是copy对象 下面是copy集合里的对象-------------------------");


        /*
        * 为什么copy集合需要克隆所有集合里的对象？？？ 因为相同对象是一个地址
        * 避免一个修改对象让集合里都改变 那就不是真正的克隆
        * */
        List<Dog> dogs = new ArrayList<>();
        List<Dog> dogsCopy = new ArrayList<>();
        Dog dog1 = new Dog();
        dog1.setDogName("狗子一号");
        dog1.setDogAge("10");
        dog1.setDogSex("男");
        Dog dog2 = new Dog();
        dog2.setDogName("狗子二号");
        dog2.setDogAge("20");
        dog2.setDogSex("女");
        Dog dog3 = new Dog();
        dog3.setDogName("狗子三号");
        dog3.setDogAge("30");
        dog3.setDogSex("男");
        dogs.add(dog1);
        dogs.add(dog2);
        dogs.add(dog3);
        /*
        //直接赋值那还是一个集合  只要修改原集合对象 复制出来的一会改变 所以要一一copy
        dogsCopy = dogs;
        dog3.setDogAge("3033");
        for (Dog dogv : dogsCopy) {
            System.out.println(dogv);
        }*/
        dogsCopy = dogs.stream().map(dogVal -> {
            Dog dogCopy = new Dog();
            BeanUtils.copyProperties(dogVal, dogCopy);
            return dogCopy;
        }).collect(Collectors.toList());
        /*
        * 这样copy后 即使老集合里的属性对象改变了  也给新集合没关系了
        * */
        dog3.setDogAge("3033");
        for (Dog dogv : dogsCopy) {
            System.out.println(dogv);
        }

    }
}