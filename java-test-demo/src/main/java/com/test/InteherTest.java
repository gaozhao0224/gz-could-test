package com.test;
/**
 * Integer比较所有情况
 *
 * ①、无论如何，Integer与new Integer不会相等。不会经历拆箱过程，因为它们存放内存的位置不一样。（要看具体位置，可以看看这篇文章：点击打开链接）
 *
 * ②、两个都是非new出来的Integer，如果数在-128到127之间，则是true,否则为false。
 *
 * ③、两个都是new出来的,则为false。
 *
 * ④、int和integer(new或非new)比较，都为true，因为会把Integer自动拆箱为int，其实就是相当于两个int类型比较。
 *
 * 清楚原理 防止犯低级错误和一些npe问题
 * java的两种默认数据类型，int（整数类型）、double（浮点数类型）
 * */
public class InteherTest {
    public static void main(String[] args) {
        Integer a = 1288 ;
        Integer c = 125 ;
        System.out.println(a == 1288);  //true  括号里的1288 是默认的int 类型  根据上面④

        Integer b = new Integer(1288);//超出缓存值范围
        Integer d = new Integer(125); //-128 和 127 之间
        System.out.println(a==b || c == d);//false  根据上面①

        Integer e = 127;
        Integer f = 127;
        Integer ee = -128;
        Integer ff = -128;

        Integer eee = 128;
        Integer fff = 128;
        Integer eeee = -129;
        Integer ffff = -129;
        System.out.println( e == f && ee == ff);
        System.out.println( eee == fff || eeee == ffff); //这两个根据②

        Integer bb = new Integer(1288);
        Integer dd = new Integer(125);
        System.out.println(b == bb || d == dd);//根据上面 ③
    }
}