package com.demo;

import java.util.regex.Pattern;

public class 正则 {

    public static void main(String[] args) {
        //只能字符和数字
        String pattern="^[a-zA-Z0-9-]+$";
        boolean isMatch= Pattern.matches(pattern, "111ds11111=11N111111111");
        System.out.println(isMatch);

    }
}