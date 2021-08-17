package com.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.springframework.util.StopWatch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleToIntFunction;
import java.util.stream.Collectors;
@Slf4j
public class LambdaTest {


    @Test
    public void testObj(){
        int num = 10;
        Callable<Integer> integerCallable = () -> num * 10;
        System.out.println(integerCallable);
    }
    @Test
    public void testList() throws CloneNotSupportedException {
        List<Person1> Person1s = getList();
        //转map后id当key 对象当value
        Map<String, Person1> map = Person1s.stream().collect(Collectors.toMap(Person1::getId, i -> i));
        //转map后id当key name当value  ::代表当前对象的方法
        Map<String, String> map1 = Person1s.stream().collect(Collectors.toMap(Person1::getId, Person1::getName));
        for (String s : map.keySet()) {
            System.out.println(s+"  "+map.get(s));
        }
        for (String s : map1.keySet()) {
            System.out.println(s+"  "+map1.get(s));
        }
        String name = "10";
        //匿名内部类必须是fianl或者有效的final
//        if(name!=null){
//            name = "10";
//        }
        //过滤 求id为10的集合
        List<Person1> list1 = Person1s.stream().filter(i -> i.getId().equals(name)).collect(Collectors.toList());
        //将对象的id拿出来创建一个集合 ::代表当前对象的方法
        List<String> list2 = Person1s.stream().map(p -> p.getId()).collect(Collectors.toList());
        List<String> list3 = Person1s.stream().collect(Collectors.mapping(Person1::getName,Collectors.toList()));
        for (Person1 s : list1) {
            System.out.println(s.toString());
        }
        for (String s : list2) {
            System.out.println(s);
        }
        for (String s : list3) {
            System.out.println(s);
        }

    }

    @Test
    public void testList1() throws CloneNotSupportedException, InterruptedException {
        List<Person1> list = getList();
        //按照Person1::getLetter 这个字段分组 返回根据Person1::getId这个对象 返回一个map
        /*           结果
        *           abc [1, 2, 3, 4, 5]
        *           efg [6, 7, 8, 9, 10]
        * */
        Map<String, List<String>> collect = list.stream().filter(p -> Objects.nonNull(p)).collect(Collectors.groupingBy(Person1::getLetter, Collectors.mapping(Person1::getId,Collectors.toList())));
        for (String s : collect.keySet()) {
            System.out.println(s+" "+collect.get(s));
        }
        //根据业务处理后返回新的集合
        StopWatch stopWatch = new StopWatch(); //计时器
        stopWatch.start();
        List<Person1> collect1 = list.stream().map(Person1 -> {
            Person1.setName(Person1.getName() + "新增");
            return Person1;
        }).collect(Collectors.toList());
        TimeUnit.SECONDS.sleep(1);
        stopWatch.stop();
        log.info("耗时"  + stopWatch.getLastTaskTimeMillis());
        List<String> collect2 = list.stream().map(Person1 -> {
            return "000";
        }).collect(Collectors.toList());
        stopWatch.start();
        for (Person1 s : collect1) {
            System.out.println(s.toString());
        }
        for (String s : collect2) {
            System.out.println(s);
        }
        stopWatch.stop();
        log.info("耗时  = "+stopWatch.getLastTaskTimeMillis());
    }




    @Test
    public void testList2() throws Exception {
        List<Person1> list = getList();
        list.stream().forEach(i->gao(i));
        for (Person1 Person1 : list) {
            System.out.println(Person1.toString());
        }
    }

    public void gao(Person1 p){
        //System.out.println(p.toString());
        p.setLetter("_________");
    }
    public List<Person1> getList() throws CloneNotSupportedException {
        Person1 Person1 = new Person1();
        Person1.setSex("男");
        List<Person1> Person1s = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Person1 clone = Person1.clone();
            clone.setId(i+"");
            clone.setName("张"+i+""+i);
            if(i>5){
                clone.setLetter("efg");
            }else{
                clone.setLetter("abc");
            }
            Person1s.add(clone);
        }
        return Person1s;
    }

    @Test
    public void newObj(){
        List<Person1> people = getListPerson1();
//        people = people.stream().filter(i -> i.getId().equals("1")).collect(Collectors.toList());
//        System.out.println(people.size());
//        for (Person1 Person14 : people) {
//            System.out.println(Person14);
//        }
        List<Person1> aaa = people.stream().map(i->{
            i.setLetter("a");
            return i;
        }).collect(Collectors.toList());
        for (Person1 Person14 : aaa) {
            System.out.println(Person14);
        }
    }
    @Test
    public void newObj1(){
        List<Person1> people = getListPerson1();

        List<Person1> list = new ArrayList<>();
        //对象存放不上 需要用foreach 下面方法
//        people.stream().map(i->list.add(i));
//        for (Person1 Person1 : list) {
//            System.out.println(Person1);
//        }
        //foreach
//        people.stream().forEach(i->list.add(i));
//        for (Person1 Person1 : list) {
//            System.out.println(Person1);
//        }
        //用map加方法体试试
        people.stream().map(i->{//匿名内部类

            i.setLetter(i.getId());
            //list.add(i);
            return i;
        }).collect(Collectors.toList());
        for (Person1 Person1 : people) {
            System.out.println("map{}"+Person1);
        }
    }


    public List<Person1> getListPerson1(){
        List<Person1> people = new ArrayList<>();
        Person1 Person11 = new Person1();
        Person1 Person12 = new Person1();
        Person1 Person13 = new Person1();
        Person11.setId("1");
        Person12.setId("2");
        Person13.setId("3");
        Person11.setName("张三");
        Person12.setName("李四");
        Person13.setName("赵五");
        people.add(Person11);
        people.add(Person12);
        people.add(Person13);
        return people;
    }

    @Test
    public void getTest1(){
        List<Person1> listPerson1 = getListPerson1();
        List<Person1> collect = listPerson1.stream().map(i -> {
            Person1 Person1 = new Person1();
            Person1.setLetter("1111");
            return Person1;
        }).collect(Collectors.toList());
        for (Person1 Person1 : collect) {
            System.out.println(Person1);
        }

    }
}