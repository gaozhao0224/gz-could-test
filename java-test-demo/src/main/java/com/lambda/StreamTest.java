package com.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {




}
@Data
@AllArgsConstructor
@NoArgsConstructor
class PersonStream{
    private int id;

    private int age;

    private int height;

}
class Stream1{

    public static void main(String[] args) {
        PersonStream person1 = new PersonStream(1, 10, 170);
        PersonStream person2 = new PersonStream(5, 20, 190);
        PersonStream person3 = new PersonStream(2, 10, 180);
        PersonStream person4 = new PersonStream(4, 20, 180);
        PersonStream person5 = new PersonStream(3, 20, 180);
        PersonStream person6 = new PersonStream(6, 20, 190);

        List<PersonStream> persons = Arrays.asList(person1, person2, person3, person4, person5, person6);

        /*
        anyMatch：判断的条件里，任意一个元素成功，返回true
        allMatch：判断条件里的元素，所有的都是，返回true
        noneMatch：与allMatch相反，判断条件里的元素，所有的都不是，返回true
        */
        boolean b = persons.stream().allMatch(p -> {
            return p.getAge() == 20;
        });
        boolean c = persons.stream().anyMatch(p -> {
            return p.getAge() == 20;
        });
        boolean d = persons.stream().noneMatch(p -> {
            return p.getAge() == 40;
        });
        List<PersonStream> collect = persons.stream().filter(p -> p.getAge() == 20).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);


    }

}

