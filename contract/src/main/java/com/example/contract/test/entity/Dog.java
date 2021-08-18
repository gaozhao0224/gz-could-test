package com.example.contract.test.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Dog implements Cloneable, Serializable {

    private String name ;
    private String age ;
    private String sex ;
    private Cat cat ;

    public Dog(String name, String age, String sex,Cat cat) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.cat = cat;
    }

    @Override
    public Dog clone() throws CloneNotSupportedException {
        return (Dog)super.clone();
    }

    @Override
    public String toString() {
        return  super.hashCode()+"\t\tDog{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", Cat='" + cat + '\'' +
                '}';
    }
}