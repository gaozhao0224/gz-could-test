package com.crc.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

}

//冒泡
class SortArr{
    public static void main(String[] args) {
        int [] arr = {1,8,6,5,3,2,4,7,10,9};
        int temp,flag=0; //冒泡
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1] = temp;
                    flag = 1;
                }
            }
            if(flag==0){
                break;
            }
        }
        doSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    static void doSort(int [] arr,int left,int right){
        if(left>=right){
            return ;
        }
        //比较对象
        int key = arr[left];
        int l = left;
        int r= right;

        while (l<r){

            //移动右侧指针 直到找到比key小的
            while (arr[r]>=key && l<r){
                r--;
            }
            //移动右侧指针 直到找到比key小的
            while (arr[l]<=key && l<r){
                l++;
            }
            //交换位置
            if(l<r){
                int temp = arr[r];
                arr[r] = arr[l];
                arr[l] = temp;
            }
        }
        //如果指向统一位置 交换对比对象的位置
        arr[left] = arr[l];
        arr[l] = key;
        //递归执行
        doSort(arr,left,l-1);
        doSort(arr,l+1,right);
    }



}

//单例
class Single{
    //懒汉式 需要的时候创建 线程安全需要加锁 时间换空间
    private static Single single = null;
    private Single() {
        this.single = single;
    }

    //饿汉式 直接创建对象 空间换时间
//    //创建对象
//    private static Single single = new Single();
//    //私有化构造方法
//    private Single() {
//        this.single = single;
//    }
//    //提供对外方法 获取对象
//    public static Single getSingle(){
//        return single;
//    }
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Single.getSingle().hashCode());
            }).start();
        }

    }

    //提供对外方法 获取对象
    public static synchronized Single getSingle(){
        if(single==null){
            single = new Single();
        }
        return single;
    }



}




//消费者
class MyThread{

    public static void main(String[] args) {
        Mydata mydata = new Mydata();
        new Thread(()->{for (int i = 0; i < 10; i++) mydata.methA();},"a线程").start();
        new Thread(()->{for (int i = 0; i < 10; i++) mydata.methB();},"b线程").start();
        new Thread(()->{for (int i = 0; i < 10; i++) mydata.methC();},"c线程").start();
    }
}

class Mydata{
    int num = 1;
    private ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

public void methA(){
        lock.lock();
        try {
            while (num!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"--------->消费A通知B");
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    };
    public void methB(){
        lock.lock();
        try {
            while (num!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"--------->消费B通知C");
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    };
    public void methC(){
        lock.lock();
        try {
            while (num!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"--------->消费C通知A");
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    };


}


//二分查找法
class ArraySelect{
    static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left<=right) {
            System.out.println(left+"-"+right);
            int mid = (right + left) / 2;
            //当然是去搜索 [left, mid - 1] 或者 [mid + 1, right] 对不对？因为 mid 已经搜索过，应该从搜索区间中去除。
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid+1;
            } else if (nums[mid] > target) {
                right = mid-1;
            }
        } //二分查找法
        return -1;
    }

    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6,7,8,9,10};
        int i = binarySearch(arr, 8);
        System.out.println(i);

    }
}



class KPTest{
    public static void main(String[] args) {
        int [] arr = {5,4,2,1,6,8,3,7};
        doSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }
    private static void doSort(int[] arr, int left, int right) {
        if(left>=right || arr ==null){
            return;
        }
        //对比对象
        int key = arr[left];
        int l = left;
        int r = right;
        while (l<r){
            //指针从右往左移动 取到比key小的
            while (l<r && arr[r]>=key){
                r--;
            }
            //指针从左往右移动 取到比key大的
            while (l<r && arr[l]<=key){
                l++;
            }
            //交换位置把小的放左边大的放右边
            if(l<r){
                int temp = arr[r];
                arr[r]=arr[l];
                arr[l] = temp;
            }
        }
        //指向同一位置后 交换位置 更换key
        arr[left] = arr[l];
        arr[l]=key;
        //递归重复执行
        doSort(arr,left,l-1);
        doSort(arr,l+1,right);
    }


}