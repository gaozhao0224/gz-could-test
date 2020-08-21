package com.example.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateTest {

    public static void main(String[] args) {
        String a = "yyyy-MM-dd HH:mm:ss";
        String b = "2020-08-17 20:45:48";
        System.out.println(b.substring(11));

    }
    /*
    * 获取当前时间周一和周日的日期
    * */
    @Test
    public void getDate1(){
        Map<String,String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if(dayWeek==1){
            dayWeek = 8;
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        System.out.println("所在周星期一的日期：" + weekBegin);


        cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
        System.out.println("所在周星期日的日期：" + weekEnd);

        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        System.out.println(map.toString());
    }
}