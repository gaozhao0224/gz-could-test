package com.anonymity;

import java.util.UUID;

public class A {

    public static void main(String[] args) {
//        boolean auth = "auth1".contains("auth");
//        System.out.println(auth);
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
}