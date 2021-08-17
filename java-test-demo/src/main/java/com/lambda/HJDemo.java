package com.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gz
 * @since 2021-7-27
 */
public class HJDemo {

    /*
    * //输入是List<Person> 有id age  height 三个字段  输出也是List<Person>
    * //其中同样年龄只输出身高最高的  如果多个身高年龄都相同的 只输出id最大的
    * */
    public static void main(String[] args) {

        Person person1 = new Person(1, 10, 170);
        Person person2 = new Person(5, 20, 190);
        Person person3 = new Person(2, 10, 180);
        Person person4 = new Person(4, 20, 180);
        Person person5 = new Person(3, 20, 180);
        Person person6 = new Person(6, 20, 190);
        List<Person> persons = Arrays.asList(person1, person2, person3, person4, person5, person6);
        //方法一
        Map<Integer, List<Person>> collect = persons.stream().collect(Collectors.groupingBy(Person::getAge));
        List<List<Person>> lists =new ArrayList<List<Person>>();
        for(Map.Entry<Integer, List<Person>> entry:collect.entrySet()){
            lists.add(entry.getValue());
        }
        List<Person> resultList = new ArrayList<>();
        lists.stream().forEach(list->{
            Person person = list.stream().sorted(Comparator.comparing(Person::getHeight, Comparator.reverseOrder()).thenComparing(Person::getId, Comparator.reverseOrder())).collect(Collectors.toList()).get(0);
            resultList.add(person);
        });
        System.out.println(resultList);

        //方法二 优化后的
        HashMap<Integer, Person> map = new HashMap<>();
        for (Person person : persons) {
            boolean containsKey = map.containsKey(person.getAge());
            if (containsKey){
                Person p = map.get(person.getAge());
                if (person.getHeight()>p.getHeight()){
                    map.put(person.getAge(),person);
                }else {
                    if (person.getId()>p.getId()){
                        map.put(person.getAge(),person);
                    }
                }
            }else {
                map.put(person.getAge(),person);
            }
        }
//        for(Map.Entry<Integer, Person> entry:map.entrySet()){
//            System.out.println(entry.getValue());
//        }
        persons.forEach(i->{
            System.out.println(i);
        });
        map.entrySet().stream().forEach(i->{
            System.out.println(i.getValue());
        });
        map.entrySet().forEach(i->{
            System.out.println(i.getValue());
        });
    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
    private int id;

    private int age;

    private int height;

}