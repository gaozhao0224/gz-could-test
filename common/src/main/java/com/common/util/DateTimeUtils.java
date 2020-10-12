package com.common.util;


import com.common.context.StringPool;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DateTimeUtils {

    private static final Map<String, DateTimeFormatter> FORMATTERS = new ConcurrentHashMap<>();

    private DateTimeUtils() {
        throw new IllegalStateException(StringPool.UTILITY_CLASS);
    }

    public static String generalFormatNow() {
        return generalFormat(LocalDateTime.now());
    }

    public static String generalFormat(LocalDateTime localDateTime) {
        return ofPattern(StringPool.DATETIME).format(localDateTime);
    }
    
    public static DateTimeFormatter ofPattern(String pattern) {
        return FORMATTERS.computeIfAbsent(pattern, DateTimeFormatter::ofPattern);
    }

    /**
     * @param cntDateBeg 开始时间
     * @param cntDateEnd 结束时间
     * @param format 返回格式 (yyyyMMdd yyyy-MM-dd)
     * @return 时间区间
     */
    public static List<String> addDates(String cntDateBeg, String cntDateEnd,String format) {
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
            list.add(new SimpleDateFormat(format).format(new Date(time)));
            time += oneDay;
        }
        return list;
    }
}
