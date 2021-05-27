package com.demo;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;

public class DemoTest3 {
    @Test
    public void test1() throws UnsupportedEncodingException {
        String a = "Tlc5cFVqVndhWFkxVERKbk5UUnBORFUwYVRRMlNWY3JOVmxUTHc9PQ==";
        //String s = new String(Base64.decodeBase64("5oiR5piv5L2g54i454i46IW+5YS/"), "UTF-8");
        //解密
        String s1 = new String(Base64.decodeBase64("6IW+5YS/5oiR5piv5L2g54i454i4"), "UTF-8");
        System.out.println(s1);
        String s2 = new String(Base64.decodeBase64(s1), "UTF-8");
        System.out.println(s2);
        String s3 = new String(Base64.decodeBase64(s2), "UTF-8");
        System.out.println(s3);
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        //加密
        String str = "腾儿我是你爸爸";
        int num = 10;
        for (int i = 0; i <num; i++) {
            str = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(str.getBytes("UTF-8"));
        }
        System.out.println(str);
        System.out.println("----------------------");
        String jiemi = jiemi(str, num);
        System.out.println(jiemi);
        //Base64 加密
        //String encoded = Base64.getEncoder().encodeToString(bytes);
    }
    public String jiemi(String str,int num) throws UnsupportedEncodingException {
        String s = str;
        for (int i = 0; i <num; i++) {
            s = new String(Base64.decodeBase64(s), "UTF-8");
        }
        return s;
    }

    @Test
    public void test3() throws UnsupportedEncodingException {
        //直接解密会把没加过密的东西解没
        String s2 = new String(Base64.decodeBase64("安达市大所多"), "UTF-8");
        System.out.println(s2);
    }
    @Test
    public void test4(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("1","2");
        boolean empty = map.isEmpty();
        System.out.println(empty);
    }
    @Test
    public void test5(){
        Integer a = 128;
        Integer b = 128;
        System.out.println(a.equals(b));
        System.out.println(a==b);
    }
    @Test
    public void test6(){
        //内存溢出 证明常量池在堆内存中

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
    @Test
    public void test7(){
        String str = "a";
        System.out.println(str);
        System.out.println("=================");
        for (int i = 0; i < 10; i++) {
            str+="a"; //str = str + "a"
            System.out.println(str);
            System.out.println(str.hashCode());
        }

    }
    @Test
    public void test8(){
        String str = "a";
        System.out.println(str);
        System.out.println("=================");
        for (int i = 0; i < 10; i++) {
            str+=str+"a"; //str = str + "a"
            System.out.println(str);
            System.out.println(str.hashCode());
        }
    }
    @Test
    public void test9(){

    }
    @Test
    public void test10(){

    }
}