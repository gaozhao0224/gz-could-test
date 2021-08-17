package com.copy;

import lombok.Data;

@Data
public class Dog {

    private String dogName ;
    private String dogSex ;
    private String dogAge ;

    public Dog(String dogName) {
        this.dogName = dogName;
    }

    public Dog(String dogName, String dogSex) {
        this.dogName = dogName;
        this.dogSex = dogSex;
    }

    public Dog() {
        this.dogName = dogName;
    }
}