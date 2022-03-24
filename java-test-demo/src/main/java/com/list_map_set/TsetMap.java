package com.list_map_set;

import java.util.HashMap;

public class TsetMap {

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("1","1-1");
        map.put("2","2-2");
        map.put("3","3-3");
        map.put("4","4-4");
        map.forEach((k,v)->{
            System.out.println(k+"  -  "+v);
        });


    }
}