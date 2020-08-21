package com.example.test;

public class ExceptionTest {
    public static void main(String[] args) {
        String test = test();
        System.out.println(test);

    }


    /*
    * finally 是最后执行的  如果finally里有return  返回结果就是finally里return的值 给try没有关系了
    * catch 里有return 报错 走catch后 返回的结果是catch里return的值 异常被捕获不影响业务继续进行
    * */
    public static String test(){
        String i = "0";
        try {
            int b = 10/0;
            return i+="try";
        }catch (Exception e){
            System.out.println("异常信息");
            e.printStackTrace();
            return i+="catch";
        } finally {
            //return "finally";
        }
    }
}