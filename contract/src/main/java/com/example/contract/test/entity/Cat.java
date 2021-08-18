package com.example.contract.test.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cat implements Cloneable, Serializable {

    private String nameCat ;
    private String ageCat ;
    private String sexCat ;

    public Cat(String nameCat, String ageCat, String sexCat) {
        this.nameCat = nameCat;
        this.ageCat = ageCat;
        this.sexCat = sexCat;
    }

    @Override
    public Cat clone() throws CloneNotSupportedException {
        return (Cat)super.clone();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "nameCat='" + nameCat + '\'' +
                ", ageCat='" + ageCat + '\'' +
                ", sexCat='" + sexCat + '\'' +
                '}';
    }
}