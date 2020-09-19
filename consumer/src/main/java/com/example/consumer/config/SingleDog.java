package com.example.consumer.config;

import lombok.Data;

@Data
public class SingleDog {

    private String name;
    private String age;
    private String sex;

    public SingleDog(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

}