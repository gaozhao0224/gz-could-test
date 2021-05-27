package com.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

public class DateString {

    public static void main(String[] args) {
        String str = "PARTITION p20200730 VALUES LESS THAN ('20200730') ENGINE = InnoDB";

        //List<String> days = getDays("2020-01-01", "2020-09-20");
        //List<String> days = compeleteDate("2020-01-01", "2020-09-20");
        List<String> days = addDates("2020-07-27", "2020-10-30");
        System.out.println(days.size());
        for (int i = 1; i <days.size(); i++) {
            System.out.println("PARTITION p"+days.get(i-1)+" VALUES LESS THAN ('"+days.get(i)+"') ENGINE = InnoDB,");
        }
        /*for (String day : days) {
            System.out.println("PARTITION p"+day+" VALUES LESS THAN ('"+day+"') ENGINE = InnoDB,");
        }*/
    }

    /**
     * @param cntDateBeg 开始时间
     * @param cntDateEnd 结束时间
     * @return
     */
    public static List<String> addDates(String cntDateBeg, String cntDateEnd) {
        List<String> list = new ArrayList<>();
        //拆分成数组
        String[] dateBegs = cntDateBeg.split("-");
        String[] dateEnds = cntDateEnd.split("-");
        //开始时间转换成时间戳
        Calendar start = Calendar.getInstance();
        start.set(Integer.valueOf(dateBegs[0]), Integer.valueOf(dateBegs[1]) - 1, Integer.valueOf(dateBegs[2]));
        Long startTIme = start.getTimeInMillis();
        //结束时间转换成时间戳
        Calendar end = Calendar.getInstance();
        end.set(Integer.valueOf(dateEnds[0]), Integer.valueOf(dateEnds[1]) - 1, Integer.valueOf(dateEnds[2]));
        Long endTime = end.getTimeInMillis();
        //定义一个一天的时间戳时长
        Long oneDay = 1000 * 60 * 60 * 24L;
        Long time = startTIme;
        //循环得出
        while (time <= endTime) {
            list.add(new SimpleDateFormat("yyyyMMdd").format(new Date(time)));
            time += oneDay;
        }
        return list;
    }

    @Test
    public void get1(){
        String startMonth = new DateTime().minusMonths(1).toString("yyyy-MM-01");
        String endMonth = new DateTime().minusMonths(1).toString("yyyy-MM-31");
        System.out.println(startMonth);
        System.out.println(endMonth);
    }
    @Test
    public void get2(){

    }
}