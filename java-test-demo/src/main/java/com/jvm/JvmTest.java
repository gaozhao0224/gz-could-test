package com.jvm;

import org.junit.Test;
/**
* JVM 测试
* */
public class JvmTest {

    @Test
    public void test1(){
        //内存溢出
        String str = "a";
        System.out.println(str);
        System.out.println("=================");
        while (true){
            str+=str; //str = str + str + "a"
            //str+="a"; //str = str + "a"
            System.out.println("----");
            System.out.println(str);
        }
    }

    @Test
    public void test2(){
        //查看当前电脑jvm内存分配第

        //返回虚拟机试图获取本机  最大  内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        //返回虚拟机试图获取本机的初始化内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("maxMemory="+maxMemory+"字节；；"+ (double)maxMemory/1024/1024 +"M");
        System.out.println("totalMemory="+totalMemory+"字节；；"+ (double)totalMemory/1024/1024 +"M");
       /**
        * 默认情况下：分配总内存是电脑的1/4    初始化的内存是电脑的1/64
        * */

        /**
         *   OOM：
                1.尝试扩大内存在查看结果
                2.分析内存  通过专业工具查看 Jprofile
                -Xms1024M -Xmx2048M -XX:+PrintGCDetails
                初始化内存  最大内存         打印GC信息   通过参数配置查看各种信息
         */

    }

    @Test
    public void test3(){

    }

    @Test
    public void test4(){

    }

    @Test
    public void test5(){

    }
}