package com.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

public class DemoTest1 {

    //比较两个日期大小
    public static int compare_date(String DATE1, String DATE2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //第一个日期在第二个日期之后
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //第一个日期在第二个日期之前
                return -1;
            } else {
                //两个日期相等
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 30);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    @Test
    public void test1(){
        System.out.println(1);
        if(true){
            System.out.println(2);
            return ;
        }
        System.out.println(3);
    }

    @Test
    public void test2() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date date = simpleDateFormat.parse("2020-12-07 10:53:30");
        String format = simpleDateFormat.format(new Date(date.getYear() + 1, date.getMonth(), date.getDate() - 1));
        System.out.println(format);
    }

    @Test
    public void test3(){
        int i = compare_date("2020-12-11", "2020-10-11");
        System.out.println(i);
    }

    @Test
    public void test4(){
        String specifiedDayBefore = getSpecifiedDayBefore("2020-12-7");
        System.out.println(specifiedDayBefore);
    }

    @Test
    public void test5(){
    }
    @Test
    public void test6(){
        Integer a = 0;
        a=null;
        if(a==null || a>1111111){
            System.out.println("22222");
        }else{
            System.out.println(1111111);
        }
    }
    @Test
    public void test8(){
        double l = (double)Math.round(131.63835616438357 * 100) / 100;
        System.out.println(l);
    }
    @Test
    public void test9(){
        //double dou = 131.63835616438357;
        double dou = 131;
        BigDecimal bigDecimal = new BigDecimal(dou).setScale(2, RoundingMode.HALF_UP);
        double newDouble = bigDecimal.doubleValue();
        System.out.println(newDouble);
    }
    @Test
    public void test10(){
        String json = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"apiId\": \"1331167632677900290\",\n" +
                "            \"appKey\": \"anGs8rapWTDbBZrezkiFTc7H\",\n" +
                "            \"consumeCount\": \"75\",\n" +
                "            \"entCode\": \"91110108M3A004CPN95\",\n" +
                "            \"requestCount\": \"100\",\n" +
                "            \"userId\": \"144\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"apiId\": \"1331167632677900290\",\n" +
                "            \"appKey\": \"anGs8rapWTDbBZrezkiFTc7H\",\n" +
                "            \"consumeCount\": \"75\",\n" +
                "            \"entCode\": \"91110108MA0042CPN95\",\n" +
                "            \"requestCount\": \"100\",\n" +
                "            \"userId\": \"144\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"apiId\": \"1331167632677900290\",\n" +
                "            \"appKey\": \"anGs8rapWTDbBZ22zkiFTc7H\",\n" +
                "            \"consumeCount\": \"75\",\n" +
                "            \"entCode\": \"91110108MA6004CPN95\",\n" +
                "            \"requestCount\": \"100\",\n" +
                "            \"userId\": \"144\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"apiId\": \"1331167632677900290\",\n" +
                "            \"appKey\": \"anGs8rapWTDbBZrezki111H\",\n" +
                "            \"consumeCount\": \"75\",\n" +
                "            \"entCode\": \"91110108MA0404CPN95\",\n" +
                "            \"requestCount\": \"100\",\n" +
                "            \"userId\": \"144\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"serialNo\": \"1001\",\n" +
                "    \"status\": 200\n" +
                "}" ;
        Map jsonToMap =  JSONObject.parseObject(json);
        List<ApiStatisticsInfo> data = JSONObject.parseArray(JSON.toJSONString(jsonToMap.get("data")), ApiStatisticsInfo.class);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        data.stream().forEach(i->{
            map.put(i.getAppKey(),i.getUserId());
        });
//        for (ApiStatisticsInfo consumptionInfoDatum : data) {
//            map.put(consumptionInfoDatum.getAppKey(),consumptionInfoDatum.getUserId());
//        }
        for (String key : map.keySet()) {
            System.out.println(key);
        }
//        System.out.println(map.toString());
//        System.out.println(jsonToMap.toString());
    }
    @Test
    public void test11(){
        String date = "2020-10-10";
        String s = date.replaceAll("-", "");
        System.out.println(s);
    }
}