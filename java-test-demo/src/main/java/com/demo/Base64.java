package com.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/*
* base64加解密
*
* */
@Slf4j
public class Base64 {


    /*
    * 加密
    * */
    public static String encryption(String str,int num) throws UnsupportedEncodingException {
        log.info("加密的字符串为:{},加密的次数为:{};",str,num);
        for (int i = 0; i <num; i++) {
            str = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(str.getBytes("UTF-8"));
        }
        return str;
    }
    /*
    * 解密
    * */
    public static String decode(String str,int num) throws UnsupportedEncodingException {
        log.info("解密的字符串为:{},解密的次数为:{};",str,num);
        for (int i = 0; i <num; i++) {
            str = new String(org.apache.tomcat.util.codec.binary.Base64.decodeBase64(str), "UTF-8");
        }
        return str;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String decode = decode("5om56YeP5Y+R56Wo5byC5q2l5p+l6aqM57uT5p6c6I635Y+W", 1);
        System.out.println(decode);
    }
}