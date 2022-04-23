package com.test.enumtest;

import lombok.Data;

import java.io.Serializable;
@Data
public class Cat implements Serializable {
    private String age ;
    private String sex ;
    private String name ;
    private StateEnum stateEnum;
}