package com.crc.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *  map的key可以存放对象，但是不支持转为json对象
 * */
public class MapTest {
    public static void main(String[] args) {
        Animal animal = new Animal("1", "动物");
        Dog dog = new Dog("10", "狗");
        Cat cat = new Cat("20", "猫");
        HashMap<String, Object> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
        list.add(dog);
        list.add(cat);

        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("123",animal);
        JSONObject jsonObject = new JSONObject(hashMap);
        System.out.println(jsonObject);
        //标准的json规范中，要求对象的key必须为string  所以new JSONObject(map<Obj,Obj>)会报错
        /**将对象转为json在放到mapkey中*/
        String jsonString = JSON.toJSONString(animal);
        map.put(jsonString,list);
        JSONObject mapJson = new JSONObject(map);
        System.out.println(mapJson);
    }

    @Data
    static class Animal{
        private String id;
        private String name;

        public Animal(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    static class Dog{
        private String id;
        private String name;

        public Dog(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    static class Cat{
        private String id;
        private String name;

        public Cat(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}