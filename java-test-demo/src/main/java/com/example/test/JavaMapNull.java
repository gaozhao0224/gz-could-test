package com.example.test;

import java.util.HashMap;
import java.util.Map;

public class JavaMapNull {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        map.put("c","c");
        System.out.println(map.get("d")); //返回  null
        //System.out.println(map.get("d").equals("")); //报错

    }
}