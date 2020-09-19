package com.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.springframework.util.StopWatch;

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
        List<Person> persons = getList();
        //转map后id当key 对象当value
        Map<String, Person> map = persons.stream().collect(Collectors.toMap(Person::getId, i -> i));
        //转map后id当key name当value  ::代表当前对象的方法
        Map<String, String> map1 = persons.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        for (String s : map.keySet()) {
            System.out.println(s+"  "+map.get(s));
        }
        for (String s : map1.keySet()) {
            System.out.println(s+"  "+map1.get(s));
        }
        //过滤 求id为10的集合
        List<Person> list1 = persons.stream().filter(i -> i.getId().equals("10")).collect(Collectors.toList());
        //将对象的id拿出来创建一个集合 ::代表当前对象的方法
        List<String> list2 = persons.stream().map(p -> p.getId()).collect(Collectors.toList());
        List<String> list3 = persons.stream().collect(Collectors.mapping(Person::getName,Collectors.toList()));
        for (Person s : list1) {
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
        List<Person> list = getList();
        //按照Person::getLetter 这个字段分组 返回根据Person::getId这个对象 返回一个map
        /*           结果
        *           abc [1, 2, 3, 4, 5]
        *           efg [6, 7, 8, 9, 10]
        * */
        Map<String, List<String>> collect = list.stream().filter(p -> Objects.nonNull(p)).collect(Collectors.groupingBy(Person::getLetter, Collectors.mapping(Person::getId, Collectors.toList())));
        for (String s : collect.keySet()) {
            System.out.println(s+" "+collect.get(s));
        }
        //根据业务处理后返回新的集合
        StopWatch stopWatch = new StopWatch(); //计时器
        stopWatch.start();
        List<Person> collect1 = list.stream().map(person -> {
            person.setName(person.getName() + "新增");
            return person;
        }).collect(Collectors.toList());
        TimeUnit.SECONDS.sleep(1);
        stopWatch.stop();
        log.info("耗时"  + stopWatch.getLastTaskTimeMillis());
        List<String> collect2 = list.stream().map(person -> {
            return "000";
        }).collect(Collectors.toList());
        stopWatch.start();
        for (Person s : collect1) {
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
        List<Person> list = getList();
        list.stream().forEach(i->gao(i));
        for (Person person : list) {
            System.out.println(person.toString());
        }
    }

    public void gao(Person p){
        //System.out.println(p.toString());
        p.setLetter("_________");
    }
    public List<Person> getList() throws CloneNotSupportedException {
        Person person = new Person();
        person.setSex("男");
        List<Person> persons = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Person clone = person.clone();
            clone.setId(i+"");
            clone.setName("张"+i+""+i);
            if(i>5){
                clone.setLetter("efg");
            }else{
                clone.setLetter("abc");
            }
            persons.add(clone);
        }
        return persons;
    }

    @Test
    public void newObj(){
        Runnable aNew = Person::new;
        Person person = new Person(){};

    }

}