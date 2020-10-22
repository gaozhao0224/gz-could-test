package com.copy;


public class CatTest {

    public static void main(String[] args) {

        Cat cat = new Cat();
        cat.DogName();

        Integer a = -128;
        Integer b = -128;
        System.out.println(a==b);   //-128到127  ==可以比值 是true 其他得是false 比值需要用equals
        System.out.println(a.equals(b));
    }

}