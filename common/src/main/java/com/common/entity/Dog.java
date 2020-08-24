package com.common.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Dog implements Serializable,Cloneable {

    private String name ;

    private String age ;

    private String sex ;

    @Override
    public Dog clone() throws CloneNotSupportedException {
        return (Dog)super.clone();
    }

    public Dog(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Dog() {
    }
}