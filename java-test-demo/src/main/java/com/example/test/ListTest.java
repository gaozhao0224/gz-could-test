package com.example.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {


    public static void main(String[] args) throws Exception {
        /*
        * 原型的好处 克隆对象  不然对象指定的一个地址 修改一个 那整个集合的都修改了
        *
        * */
        /*List<ListDto> listDtos = new ArrayList<>();
        ListDto listDto = new ListDto();
        listDto.setName("A");
        listDto.setAge(20);
        listDtos.add(listDto);
        listDto.setName("B");
        listDto.setAge(30);
        listDtos.add(listDto);
        System.out.println(listDtos.size());
        for (ListDto dto : listDtos) {
            System.out.println(dto.toString());
        }*/






        List<ListDto> listDtos = new ArrayList<>();
        ListDto listDto = new ListDto();
        listDto.setId("123");
        listDto.setName("A");
        listDto.setAge(20);
        listDtos.add(listDto);
        ListDto clone = listDto.clone();
        clone.setName("B");
        clone.setAge(30);
        listDtos.add(clone);
        for (int i = 0; i < 5; i++) {
            ListDto a = listDto.clone();
            a.setName(i+"");
            a.setAge(i+20);
            listDtos.add(a);
        }
        System.out.println(listDtos.size());
        for (ListDto dto : listDtos) {
            System.out.println(dto.toString());
        }
    }
}