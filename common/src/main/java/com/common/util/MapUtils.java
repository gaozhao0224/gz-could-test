package com.common.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MapUtils {

    /**
     *
     * @Description : 根据key排序
     * @param map
     * @return : Map<String,Object>
     * @Creation Date : 2019年7月25日 下午4:22:33
     * @Author : chutong
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        sortMap.putAll(map);
        return sortMap;
    }

    public static boolean isEmpty(Map<String, Object> paramMap){
        if (paramMap == null || paramMap.isEmpty()) {
            return true;
        }else{
            return false;
        }
    }
}
