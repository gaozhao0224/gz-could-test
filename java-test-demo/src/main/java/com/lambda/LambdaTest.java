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
        String name = "10";
        //匿名内部类必须是fianl或者有效的final
//        if(name!=null){
//            name = "10";
//        }
        //过滤 求id为10的集合
        List<Person> list1 = persons.stream().filter(i -> i.getId().equals(name)).collect(Collectors.toList());
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
        Map<String, List<String>> collect = list.stream().filter(p -> Objects.nonNull(p)).collect(Collectors.groupingBy(Person::getLetter, Collectors.mapping(Person::getId,Collectors.toList())));
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
        List<Person> people = getListPerson();
//        people = people.stream().filter(i -> i.getId().equals("1")).collect(Collectors.toList());
//        System.out.println(people.size());
//        for (Person person4 : people) {
//            System.out.println(person4);
//        }
        List<Person> aaa = people.stream().map(i->{
            i.setLetter("a");
            return i;
        }).collect(Collectors.toList());
        for (Person person4 : aaa) {
            System.out.println(person4);
        }
    }
    @Test
    public void newObj1(){
        List<Person> people = getListPerson();

        List<Person> list = new ArrayList<>();
        //对象存放不上 需要用foreach 下面方法
//        people.stream().map(i->list.add(i));
//        for (Person person : list) {
//            System.out.println(person);
//        }
        //foreach
//        people.stream().forEach(i->list.add(i));
//        for (Person person : list) {
//            System.out.println(person);
//        }
        //用map加方法体试试
        people.stream().map(i->{//匿名内部类

            i.setLetter(i.getId());
            //list.add(i);
            return i;
        }).collect(Collectors.toList());
        for (Person person : people) {
            System.out.println("map{}"+person);
        }
    }


    public List<Person> getListPerson(){
        List<Person> people = new ArrayList<>();
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        person1.setId("1");
        person2.setId("2");
        person3.setId("3");
        person1.setName("张三");
        person2.setName("李四");
        person3.setName("赵五");
        people.add(person1);
        people.add(person2);
        people.add(person3);
        return people;
    }

    @Test
    public void getTest1(){
        List<Person> listPerson = getListPerson();
        List<Person> collect = listPerson.stream().map(i -> {
            Person person = new Person();
            person.setLetter("1111");
            return person;
        }).collect(Collectors.toList());
        for (Person person : collect) {
            System.out.println(person);
        }

    }
}