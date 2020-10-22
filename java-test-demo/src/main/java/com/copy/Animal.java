package com.copy;

import lombok.Data;

@Data
public class Animal {

    private String color ;
    private String weight ;
    private String height ;

    private Dog dog;
}