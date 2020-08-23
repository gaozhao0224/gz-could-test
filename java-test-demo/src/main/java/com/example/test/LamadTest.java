package com.example.test;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LamadTest {
    public static void main(String[] args) {
        List<String> objects = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            objects.add(i+"");
        }



        long l = System.currentTimeMillis();
        for (String object : objects) {
            TestMethod();
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);


        long l2 = System.currentTimeMillis();
        objects.parallelStream().forEach(i -> TestMethod());
        long l3 = System.currentTimeMillis();
        System.out.println(l3-l2);
        /*List<String> objects = new ArrayList<String>();
        for (int i = 0; i < 100000; i++) {
            objects.add(i+"");
        }



        long l = System.currentTimeMillis();
        for (String object : objects) {
            int  a  = 10*10;
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);


        long l2 = System.currentTimeMillis();
        objects.parallelStream().forEach(i ->{
            int  a  = 10*10;
        });
        long l3 = System.currentTimeMillis();
        System.out.println(l3-l2);*/



    }

    private static void TestMethod() {
        try {
            /*Thread.sleep(1);*/
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws CloneNotSupportedException {
        List<String> objects = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            objects.add(i+"");
        }
        List<String> collect = objects.stream().filter(i -> i.equals("1")).collect(Collectors.toList());
        System.out.println(collect);
        List<Dog> dogs = new ArrayList<Dog>();
        Dog dog = new Dog("狗子", "0");
        for (int i = 0; i < 100; i++) {
            Dog cloneDog = dog.clone();
            cloneDog.setAge(i+"");
            dogs.add(cloneDog);
        }
        List<Dog> dogClone = dogs.stream().filter(i -> i.getAge().equals("1")).collect(Collectors.toList());
        System.out.println(dogClone);

    }
    @Test
    public void test2() throws CloneNotSupportedException {
        List<Dog> objects = new ArrayList<Dog>();
        Dog dog = new Dog("狗","0");
        for (int i = 1; i <=100; i++) {
            Dog clone = dog.clone();
            clone.setAge(i+"");
            clone.setName(clone.getName()+"");
            objects.add(clone);
        }
        objects.parallelStream().forEach(LamadTest::getDog);
        for (Dog object : objects) {
            System.out.println(object.toString());
        }

    }

    public static Dog getDog(Dog dog){
        //System.out.println(dog.toString());
        dog.setName("建辉");
        return dog;
    }


    /*
    * 计算字符串中数字的个数 和 每个数字出现的次数
    *
    * */
    Pattern pattern = Pattern.compile("[0-9]*");
    @Test
    public void test3(){
        String str = "asdasfds3fdg6df7sd8ass6das4das6d5s7gfd7g6df98h6gfh4dfgsdfsdf5sd5fsd5gsd3gdfghsdg5sd7g57d";
        String[] split = str.split("");
        List<String> strings = Arrays.asList(split);
        HashMap<String, String> map = new HashMap<>();
        /*strings.stream().forEach(i->{
            Matcher isNum = pattern.matcher(i);
            if(isNum.matches()){
                if(map.containsKey(i)){
                    String s = map.get(i);
                    map.put(i,(Integer.valueOf(i)+1)+"");
                }
            }
        });*/

        for (String s : strings) {
            Matcher isNum = pattern.matcher(s);
            if(isNum.matches()){
                if(map.containsKey(s)){
                    String ss = map.get(s);
                    map.put(s,(Integer.valueOf(ss)+1)+"");
                }else{
                    map.put(s,"1");
                }
            }
        }
        for (String s : map.keySet()) {
            System.out.println(s + "\t\t" +map.get(s));
        }
    }









    @Test
    public void test4(){
        Matcher isNum = pattern.matcher("i");
        Matcher num = pattern.matcher("1");
        boolean matches = isNum.matches();
        boolean nums = num.matches();
        System.out.println(matches);
        System.out.println(nums);
    }
}