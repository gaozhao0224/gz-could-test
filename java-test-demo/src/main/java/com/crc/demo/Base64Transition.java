package com.crc.demo;

import java.io.UnsupportedEncodingException;

public class Base64Transition {

    String nickName = "";

    public static void main(String[] args) throws UnsupportedEncodingException {

        String s = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String("123".getBytes("UTF-8"));
        System.out.println(s);
        String s1 = new String(org.apache.tomcat.util.codec.binary.Base64.decodeBase64(s), "UTF-8");
        System.out.println(s1);
    }

    public String getNickname(String nickname) {
        try {
            nickName = new String(org.apache.tomcat.util.codec.binary.Base64.decodeBase64(nickname), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return nickName;
    }

    public void setNickname(String nickname) {
        try {
            nickname = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(nickname.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }










}