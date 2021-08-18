package com.example.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@RequestMapping("/zxt")
public class ZXTAddBigDataSQL {
    static Connection conn = null;

    public static void initConn() throws ClassNotFoundException, SQLException {

//        String url = "jdbc:mysql://127.0.0.1:3306/gz-could?user=root&password=root&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
//        String url = "jdbc:mysql://127.0.0.1:3306/big_data?"
//                + "user=root&password=root&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC";
//        String url = "jdbc:mysql://118.24.17.24:3306/big_data?"
//                + "user=root&password=GAOzhao@1233&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC";
        String url = "jdbc:mysql://127.0.0.1:3306/gz-could?user=root&password=root&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        try {
            // 动态加载mysql驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动程序");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //随机start到end的数字
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    public static String randomStr(int size) {
        //定义一个空字符串
        String result = "";
        for (int i = 0; i < size; ++i) {
            //生成一个97~122之间的int类型整数
            int intVal = (int) (Math.random() * 26 + 97);
            //强制转换（char）intVal 将对应的数值转换为对应的字符，并将字符进行拼接
            result = result + (char) intVal;
        }
        //输出字符串
        return result;
    }

    public static void insert(int insertNum) {
        // 开时时间
        //Long begin = System.currentTimeMillis();
        System.out.println("开始插入数据...");
        // sql前缀
//        String prefix = "INSERT INTO ad_device_click ( click_id ,  ip ,  ad_code ,  media_code ,  game_id ,  apicode ,  idfa ,  imei ,  oaid ,  idfa_imei_md5 ,  device_model ,  ua ,  ios_fuzzy_match_data ,  ios_fuzzy_match_data_md5 ,  is_active ,  api_callback_type ,  ad_code_callback_type ,  sys_type ,  info ,  version ,  sys_date ,  created_at ,  updated_at ) VALUES ";
        String prefix = "INSERT INTO ad_device_click ( click_id ,  ip , ad_code ,  media_code ,  game_id ,  apicode,idfa ,  imei ,  oaid ,  idfa_imei_md5 ,  device_model,created_at,updated_at ) VALUES ";

        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement("");
            String simpleUUID = IdUtil.simpleUUID().substring(0,10);
            //int simpleUUID = 1;
            for (int i = 1; i <= insertNum; i++) {
                // 构建sql后缀
                suffix.append("('"+ simpleUUID + "','"+ simpleUUID+ "','"+ simpleUUID+ "','"+ simpleUUID+ "','"+ simpleUUID+ "','"+ simpleUUID+ "','"+ simpleUUID+ "','"+ simpleUUID+ "','"+ simpleUUID+ "','"+ simpleUUID+ "','"+ simpleUUID + "',now(),now()" +"),");
//                suffix.append("('"+ simpleUUID + "','"+ simpleUUID + ",'"+ simpleUUID + "','"+ simpleUUID + "','" + simpleUUID + "','" + simpleUUID+ "','" + simpleUUID+ "','" + simpleUUID+ "','" + simpleUUID+ "','" + simpleUUID+ "','" + simpleUUID+ "','" + simpleUUID+ "','" + simpleUUID + "','" + simpleUUID + "','" + 1 + "','" + 2 + "','" + 3+ "','" + 4+ "','" + 5+ "','" + 6+ "','" + 7  +"'),");
            }
            // 构建完整sql
            String sql = prefix + suffix.substring(0, suffix.length() - 1);
            // 添加执行sql
            pst.addBatch(sql);
            // 执行操作
            pst.executeBatch();
            // 提交事务
            conn.commit();

            // 关闭连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int num = 0;
        int insert = 10000;
        // 开时时间
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            initConn();
            insert(insert);
            num+=insert;
            System.out.println("第"+i+"次插入.........");
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        System.out.println("耗时 : " + (end - begin) / 1000 + " 秒\t\t共插入"+num+"条");
    }

    @RequestMapping("/xtgn")
    public String data(String number) throws SQLException, ClassNotFoundException {
        int num = 0;
        int insert = 10000;
        // 开时时间
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            initConn();
            insert(insert);
            num+=insert;
            System.out.println("第"+i+"次插入.........");
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        System.out.println("耗时 : " + (end - begin) / 1000 + " 秒\t\t共插入"+num+"条");
        return "腾儿闺女";
    }
}