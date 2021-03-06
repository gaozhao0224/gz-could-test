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
    /*
    * 当前日期前多少天
    * */
    @Test
    public void getAddSubtractData() {
        Date date = new Date();//获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化一下
        Calendar calendar = Calendar.getInstance();//获取对日期操作的类对象
        calendar.add(Calendar.DATE, -30);
        //add方法 参数也可传入 月份，获取的是前几月或后几月的日期
        //calendar1.add(Calendar.MONTH, -3);
        Date today = calendar.getTime();
        System.out.println(sdf.format(today));
    }

    /*
    * 向前延申几个月
    * */
    @Test
    public void firstFwMonths(){
        String[] strings = firstFwMonths(16);
        for (String string : strings) {
            System.out.println(string);
        }
    }
    public String [] firstFwMonths(int num){
        String[] lastMonths = new String[num];
        Calendar cal = Calendar.getInstance();
        //如果当前日期大于二月份的天数28天或者29天会导致计算月份错误，会多出一个三月份，故设置一个靠前日期解决此问题
        cal.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < num; i++) {
            lastMonths[(num-1) - i] = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); //逐次往前推1个月
        }
        /*
         * 格式化日期
         * */
        for (int i =0;i<lastMonths.length;i++) {
            String[] split = lastMonths[i].split("-");
            if(Integer.valueOf(split[1])<10){
                lastMonths[i] = split[0]+"-0"+split[1];
            }
        }
        return lastMonths;
    }
}