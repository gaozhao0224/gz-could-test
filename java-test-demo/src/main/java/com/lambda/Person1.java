package com.lambda;

import lombok.Data;

@Data
public class Person1 implements Cloneable{
    private String id ;
    private String name ;
    private String sex ;
    private String letter ;

    @Override
    protected Person1 clone() throws CloneNotSupportedException {
        return (Person1) super.clone();
    }
}