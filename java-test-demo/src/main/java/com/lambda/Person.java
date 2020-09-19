package com.lambda;

import lombok.Data;

@Data
public class Person implements Cloneable{
    private String id ;
    private String name ;
    private String sex ;
    private String letter ;

    @Override
    protected Person clone() throws CloneNotSupportedException {
        return (Person)super.clone();
    }
}