package com.money;


import cn.hutool.core.util.RandomUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: RandomTest
 * @Description:
 * @Author: GaoZhao
 * @Date 2022/3/11
 */
public class RandomTest {

    public static void main(String[] args) {
        /*List<BigDecimal> bigDecimals = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int i1 = RandomUtil.randomInt(100000);
            bigDecimals.add(new BigDecimal(i1+"").add(new BigDecimal(new Random().nextDouble()).setScale(Numerical.TWO, BigDecimal.ROUND_DOWN)));
        }
        BigDecimal total = new BigDecimal("0");
        for (BigDecimal bigDecimal : bigDecimals) {
            total=total.add(bigDecimal);
        }
        System.out.println(bigDecimals);
        System.out.println(total);

        BigDecimal a = new BigDecimal(RandomUtil.randomInt(total.intValue())+"");
        BigDecimal b = new BigDecimal("" + RandomUtil.randomInt(total.intValue()-a.intValue()));
        BigDecimal c = total.subtract(a).subtract(b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);*/
    }
}
