package com.demo1;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: Test11
 * @Description: 1
 * @Author: GaoZhao
 * @Date 2022/3/23
 */
@Slf4j
public class Test11 {


}

class A {
    private static int m;
    private static int n;
    static {
        new A();
        System.out.println(String.format(">>>m:%s, n:%s", m, n));
    }
    static {
        System.out.println("11111");
        m = 2;
        n = 2;
    }
    {
        System.out.println("2222");
        n = 1;
    }

    public static void main(String[] args) {
        new B();
        System.out.println(String.format("===m:%s, n:%s", m, n));
    }

    public static class B extends A {
        static {
            System.out.println(String.format("---m:%s, n:%s", m, n));
            n = 3;
        }

        {
            System.out.println("3333");
            m = 3;
        }
    }
}


class D{
    {
        System.out.println("3333");
    }
    public D() {
        System.out.println("111");
    }
//    static {
//        System.out.println("5555");
//    }
//    public static void aa(){
//        System.out.println(6666);
//    }
}
class E extends D{
    {
        System.out.println("4444");
    }
    public E() {
        System.out.println("222");
    }

    public static void main(String[] args) {
//        D.aa();
        E e = new E();
    }
}