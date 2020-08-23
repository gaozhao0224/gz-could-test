package com.example.test;

import lombok.Data;

@Data
public class Dog implements Cloneable{
    private String name ;
    private String age ;

    public Dog(String name, String age) {
        this.name = name;
        this.age = age;
    }


    @Override
    protected Dog clone() throws CloneNotSupportedException {
        return (Dog)super.clone();
    }
}