package com.demo1;

import java.text.NumberFormat;
import java.util.Locale;

import static java.lang.String.format;

/**
 * @ClassName: Demo
 * @Description: demo
 * @Author: gz
 * @Date 2021/10/18
 */
public class Demo {

    static int num = 0;
    static int salarySum = 0;

    public static void main(String[] args) {
        float ride = ride(15500, 41,1.1f);
        String format = NumberFormat.getInstance(Locale.CHINA).format(ride);
        System.out.println(format);
        System.out.println(salarySum);
    }
    /**
    * @description: 大保健工资计算器
    * @Param: salary 工资；numYear 年份；proportion 涨幅比例
    * @Return: float
    * @Author: gz
    * @Date: 2021/10/18
    **/
    public static float ride(float salary, int numYear,float proportion){
        salarySum+=(salary*13);
        if(num==numYear){
            return salary;
        }else {
            num++;
            salary = salary * proportion;
            return ride(salary, numYear,proportion);
        }
    }
}
