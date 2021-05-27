package com.jvm;

public class Oom1 {
    public static void main(String[] args) {
        String str = "a";
        System.out.println(str);
        System.out.println("=================");
        while (true){
            str+=str; //str = str + str + "a"
            //str+="a"; //str = str + "a"
            System.out.println("----");
            System.out.println(str);
        }
    }
}