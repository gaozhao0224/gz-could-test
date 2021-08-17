package com.mythread.pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolTest {

    /**
    * 线程池的创建不要使用 Executors   通过底层ThreadPoolExecutor来创建和使用
    * */

    public static void main(String[] args) {
        //四种默认的 线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newScheduledThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();


        try {
            for (int i = 0; i < 10000000; i++) {
                final int num = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"->ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }


    }

}


class MyThreadPool{

    public static void main(String[] args) {

        // 自定义线程池！工作 ThreadPoolExecutor
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()); //丢弃任务并抛出RejectedExecutionException异常。  【默认】
                //new ThreadPoolExecutor.DiscardPolicy());//也是丢弃任务，但是不抛出异常。
                //new ThreadPoolExecutor.CallerRunsPolicy());//由调用线程处理该任务 【谁调用，谁处理】
                new ThreadPoolExecutor.DiscardOldestPolicy()); //队列满了，尝试去和
        //最早的竞争，也不会抛出异常！
        try {
            // 最大承载：Deque + max
            // 超过 RejectedExecutionException
            for (int i = 1; i <= 90; i++) {
                System.out.println(i);
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
                //                4种拒绝策略
                //                 小结和拓展
                //                池的最大的大小如何去设置！
                //                了解：IO密集型，CPU密集型：（调优）
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }




}


class PX{
    public static void main(String[] args) {
        int [] arr = {6,5,1,3,2,4,7};
//        int flag = 0;
//        for (int i = 0; i < arr.length-1; i++) {
//            for (int j = 0; j < arr.length-1-i; j++) {
//                if(arr[j]>arr[j+1]){
//                    int temp = arr[j];
//                    arr[j]=arr[j+1];
//                    arr[j+1] = temp;
//                    flag = 1;
//                }
//            }
//            if(flag==0){
//                break;
//            }
//        }
//        for (int i : arr) {
//            System.out.println(i);
//        }

        //快排
        doSort(arr,0,arr.length-1);



        //二分查找法
        int i = doDichotomia(arr, 6);
        System.out.println(i);

    }

    private static int doDichotomia(int[] arr, int num) {
        int left = 0;
        int right = arr.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(arr[mid]==num){
                return mid;
            }else if(arr[mid]>num){
                right =mid-1;
            }else if(arr[mid]<num){
                left =mid+1;
            }
        }
        return -1;
    }


    private static void doSort(int[] arr, int left, int right) {
        if(arr==null || left>=right){
            return ;
        }
        //比对对象
        int key = arr[left];
        int l = left;
        int r = right;
        while (l<r){
            //右边指针左移 比key小停
            while (l<r && arr[r]>=key){
                r--;
            }
            //左边指针右移动 比key大停
            while (l<r && arr[l]<=key){
                l++;
            }
            //交换位置 小的去左边 大的去右边
            int temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
        }
        //指向同一位置了 将key交换
        arr[left] = arr[l];
        arr[l] = key ;

        //递归执行
        doSort(arr,left,l-1);
        doSort(arr,l+1,right);



    }


}

class PLT{
    public static void main(String[] args) {
        int climblingWays = getClimblingWays(10);
//        int climblingWays2 = getClimblingWays2(10,new HashMap<>());
//        int climblingWays3 = getClimblingWays3(10);
        System.out.println(climblingWays);
//        System.out.println(climblingWays2);
//        System.out.println(climblingWays3);

    }
    public static int getClimblingWays(int n){
        System.out.println("n="+n);
        if ( n<1 ) {
            System.out.println("-->"+0);
            return 0;
        }
        if (n == 1){
            System.out.println("-->"+1);
            return 1;
        }
        if (n == 2){
            System.out.println("-->"+2);
            return 2;
        }
        return getClimblingWays(n-1)+getClimblingWays(n-2);
    }

    public static int getClimblingWays2(int n, Map<Integer,Integer> map){
        if ( n<1 ) return 0;
        if (n == 1)return 1;
        if (n == 2)return 2;
        if (map.containsKey(n)){
            return map.get(n);
        }else{
            int val =  getClimblingWays2(n-1,map)+getClimblingWays2(n-2,map);
            map.put(n,val);
            return val;
        }
    }
    public static int getClimblingWays3(int n ){
        if ( n<1 ) return 0;
        if (n == 1)return 1;
        if (n == 2)return 2;
        int a = 1;
        int b = 2;
        int temp =0;
        for(int i = 3;i <= n ;i++){
            temp = b + a;
            a = b;
            b = temp;
        }
        return temp;
    }



}

class Test1{

    public static void main(String[] args) {

        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);


    }

}
