package com.vopo;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName: PoVoTest
 * @Description:
 * @Author: GaoZhao
 * @Date 2022/1/22
 */
public class PoVoTest {
    private String name;
/*    public static void main(String[] args) {
//        Po1 po1 = new Po1("a", "b", "c");
//        PoB1 pob1 = new PoB1("bbbb", "ccccc");
//
//        System.out.println(po1);
//        BeanUtil.copyProperties(pob1,po1);
//        System.out.println(po1);

        BigDecimal a = new BigDecimal(2.00);
        BigDecimal b = new BigDecimal(3.00);
        System.out.println(a.divide(b,2,BigDecimal.ROUND_DOWN));
    }*/
    public static void main(String[] args) {
        PoVoTest poVoTest = null;
        if(poVoTest.name ==null || "".equals(poVoTest.name)){
            System.out.println("2222");
        }else
        {
            System.out.println("111");
        }
        SnowflakeIdWorker idWorker1 = new SnowflakeIdWorker(5, 10);
        SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(20, 20);

        for (int i = 0; i < 100; i++) {
            long id1 = idWorker1.nextId();
//            long id2= idWorker2.nextId();
//            System.out.println(Long.toBinaryString(id)); 940332219983986747
            System.out.println(idWorker1.nextId().toString());
//            System.out.println("1:     "+id2);
        }
    }
}
