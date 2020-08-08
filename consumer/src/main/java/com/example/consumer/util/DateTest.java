package com.example.consumer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateTest {


    public static void main(String[] args) throws ParseException {
        List day = Day("2020-07-01", "2020-08-01");
        System.out.println(day.isEmpty());
    }
    /*
    * 获取指定时间内的日期
    * */
    public static List Day(String begin, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List strDate = new ArrayList();
        strDate.add(begin);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(begin));
        boolean bContinue = true;
        while (bContinue) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            if (sdf.parse(end).after(calendar.getTime())) {
                strDate.add(sdf.format(calendar.getTime()).toString());
            } else {
                break;
            }
        }
        strDate.add(end);
        return strDate;
    }
}