package com.example.test;

public class ExceptionTest {
    public static void main(String[] args) {
        String test = test();
        System.out.println(test);

    }


    public static String test(){
        String i = "0";
        try {
            int b = 10/0;
            return i+="try";
        }catch (Exception e){
            System.out.println("异常信息");
            e.printStackTrace();
            return i+="catch";
        } finally {
            return i+="finally";
        }
    }
}