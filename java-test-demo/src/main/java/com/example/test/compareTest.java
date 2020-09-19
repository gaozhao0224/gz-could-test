package com.example.test;

import java.util.Random;

public class compareTest {

    public static void main(String[] args) {

//        String str = "sdasdasd";
//        System.out.println(str.indexOf("11"));
//        System.out.println(str.indexOf("sda"));

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            System.out.println(random.nextInt(3));
        }

    }
}