package com.juc;

import cn.hutool.core.util.IdUtil;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ListAndSetAndMap {


}

class ListTest{
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
//        for (int i = 1; i <= 20; i++) {
//            new Thread(()->{
//                String uuid = IdUtil.simpleUUID().substring(0, 5);
//                list.add(uuid);
//                System.out.println(list+"\n\n");
//            },i+"_号线程").start();
//        }
        //通过 CopyOnWriteArrayList 来实现线程安全
        CopyOnWriteArrayList<String> listCOW = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                String uuid = IdUtil.simpleUUID().substring(0, 5);
                listCOW.add(uuid);
                System.out.println(listCOW+"_"+Thread.currentThread().getName()+"_长度是："+listCOW.size());
            },i+"号线程").start();
        }

    }



}

class SetTest{
    public static void main(String[] args) {
//        HashSet set = new HashSet();
//        for (int i = 1; i <= 111; i++) {
//            new Thread(()->{
//                String uuid = IdUtil.simpleUUID().substring(0, 10);
//                set.add(uuid);
//                System.out.println(set+"_"+Thread.currentThread().getName()+"_长度是："+set.size());
//            },i+"号线程").start();
//        }
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 2000; i++) {
            new Thread(()->{
                String uuid = IdUtil.simpleUUID().substring(0, 10);
                set.add(uuid);
                System.out.println(set+"_"+Thread.currentThread().getName()+"_长度是："+set.size());
                list.add(set.size()+"");
            },i+"号线程").start();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.stream().filter(i->i.equals("2000"));
        System.out.println(list.size());
    }
}

class MapTest{

}

class ListTestConcurrent{
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        List<String> list = new ArrayList<>();
//        List<String> listCopy = new ArrayList<>();
//        for (int i = 0; i < 600000; i++) {
//            list.add(i+"");
//        }
//        System.out.println(list.size());
//        list.parallelStream().forEach(i->{
//            listCopy.add(i);
//        });
//        System.out.println(listCopy.size());

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> listCopy = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 600000; i++) {
            list.add(i+"");
        }
        System.out.println(list.size());
        list.parallelStream().forEach(i->{
            listCopy.add(i);
        });
        System.out.println(listCopy.size());
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }
}

class ListTestConcurrent1{
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        List<String> list = new ArrayList<>();
//        List<String> listCopy = new ArrayList<>();
//        for (int i = 0; i < 600000; i++) {
//            list.add(i+"");
//        }
//        System.out.println(list.size());
//        list.parallelStream().forEach(i->{
//            listCopy.add(i);
//        });
//        System.out.println(listCopy.size());

        List<String> list = new ArrayList<>();
        //CopyOnWriteArrayList<String> listCopy = new CopyOnWriteArrayList<>();
        ArrayList<String> listCopy = new ArrayList<>();
        for (int i = 0; i < 60000; i++) {
            list.add(i+"");
        }
        System.out.println(list.size());
//        list.parallelStream().forEach(i->{
//            listCopy.add(i);
//        });
        list.stream().forEach(i->{
            listCopy.add(i);
        });
        System.out.println(listCopy.size());
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }
}

class ListTestConcurrent2{
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> list = new ArrayList<>();
        List<String> listCopy = new ArrayList<>();
        for (int i = 0; i < 600000; i++) {
            list.add(i+"");
        }
        System.out.println(list.size());
//        list.parallelStream().forEach(i->{
//            listCopy.add(i);
//        });
//        System.out.println(listCopy.size());

//        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
//        CopyOnWriteArrayList<String> listCopy = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 60000; i++) {
//            list.add(i+"");
//        }
        System.out.println(list.size());
//        list.parallelStream().forEach(i->{
//            listCopy.add(i);
//        });
//        System.out.println(listCopy.size());
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }
}

class ThreadList{

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                int a = finalI;
                a=10;

            }).start();
        }


        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> listPT = new ArrayList<>();//多线程操作会报错
        CopyOnWriteArrayList<String> objects = new CopyOnWriteArrayList<String>();//多线程下安全
        for (int i = 0; i < 100000; i++) {
            list.add(i+"");
        }
        AtomicInteger numAtomic = new AtomicInteger();
        list.parallelStream().forEach(l->{
            numAtomic.getAndIncrement();
//            listPT.add(l);
            objects.add(l);
        });
        System.out.println(numAtomic.toString()+"     "+objects.size());
    }

//    public static int add(AtomicInteger n){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
////        n.getAndIncrement();
//        return n.getAndIncrement();
//    }

}
class ThreadList1{

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            new Thread(()->{
//                int a = finalI;
//                a=10;
//
//            }).start();
//        }


        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> listPT = new ArrayList<>();//多线程操作会报错
        CopyOnWriteArrayList<String> objects = new CopyOnWriteArrayList<String>();//多线程下安全
        for (int i = 0; i < 100; i++) {
            list.add(i+"");
        }
        AtomicInteger numAtomic = new AtomicInteger();
//        list.parallelStream().forEach(l->{
//            numAtomic.getAndIncrement();
//            //            listPT.add(l);
//            objects.add(l);
//        });
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        list.stream().forEach(l->{try {
//            TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println(l);
//        });
        list.parallelStream().forEach(l->{
            try {
                TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            System.out.println(l);
        });
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
        //System.out.println(numAtomic.toString()+"     "+objects.size());
    }

    public static int add(AtomicInteger n){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        n.getAndIncrement();
        return n.getAndIncrement();
    }

}

class ThreadList2{

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            new Thread(()->{
//                int a = finalI;
//                a=10;
//
//            }).start();
//        }


        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> listPT = new ArrayList<>(10001);
        ArrayList<String> listJS = new ArrayList<>(100000);
        CopyOnWriteArrayList<String> objects = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 100000; i++) {
            list.add(i+"");
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        list.parallelStream().forEach(l->{
            listJS.add(l);
        });
        list.stream().forEach(l->{
            listPT.add(l);
            objects.add(l);
        });
        stopWatch.stop();
        System.out.println("时间： "+stopWatch.getLastTaskTimeMillis());
        System.out.println("普通的长度："+listPT.size()+"；---多线程的长度："+listJS.size()+"；---CopyOnWriteArrayList下多线程的长度："+objects.size());
    }

}

/*
 * 对比一下  是多线程下用CopyOnWriteArrayList快  还是串行的普通集合快。加延迟和不加延迟
 * */
class ThreadList3{

    public static void main(String[] args) {


        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> listPT = new ArrayList<>();
        CopyOnWriteArrayList<String> objects = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 100000; i++) {
            list.add(i+"");
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        list.parallelStream().forEach(l->{
//
//            objects.add(l);
//        });
        list.stream().forEach(l->{
            listPT.add(l);
        });
        stopWatch.stop();
        System.out.println("时间： "+stopWatch.getLastTaskTimeMillis());
        System.out.println("普通的长度："+listPT.size());

        /*
        *    10w 数据   时间： 3299
        *    CopyOnWriteArrayList下多线程的长度：100000
        * */
        //System.out.println("CopyOnWriteArrayList下多线程的长度："+objects.size());
    }

}
/**
 * 尝试讲ArrayList转成CopyOnWriteArrayList  保证parallelStream的正常使用  提升速度
 * 针对于处理业务时间长的 比如导入的excel可以进行集合转换一下（几乎不耗时）  然后再使用parallelStream  根据电脑配置肯定会提升几倍的效率
 * */
class ThreadList4{
    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        List<String> list= new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }
        CopyOnWriteArrayList<String> writeArrayList = new CopyOnWriteArrayList<>();
        watch.start();
//        for (String s : list) {
//            writeArrayList.add(s);
//        }
//        writeArrayList.parallelStream().forEach(i->{
//            get(i);
//        });
        for (String s : list) {
            get(s);
        }
        watch.stop();
        System.out.println(watch.getLastTaskTimeMillis()+"          "+writeArrayList.size());
    }

    public static void get(String str){

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }

}