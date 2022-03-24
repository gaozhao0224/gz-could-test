package com.date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: Test
 * @Description: 增加时间年
 * @Author: gz
 * @Date 2021/11/18
 */
public class Test {
    public static void main(String[] args) {
//        DateTime dateTime = DateUtil.offsetMonth(new Date(), -12);
//        System.out.println(dateTime);
//        IdUtil.simpleUUID();
//        DateTime parse = DateUtil.parse("20221111");
//        DateUtil.format(parse,"yyyy-MM-dd");
//        System.out.println(parse);
        String[] split = "abc".split("");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @org.junit.Test
    public void test1(){
        System.out.println(new Date().getTime());

    }
}
