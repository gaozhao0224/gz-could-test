package com.example.test;

import lombok.Data;

@Data
/*
* 虽然Object默认有clone方法
* 但是必须要实现Cloneable 接口  不然会报  java.lang.CloneNotSupportedException 异常
* */
public class ListDto implements Cloneable{


    private String id;

    private String name;

    private Integer age;

    @Override
    protected ListDto clone() throws CloneNotSupportedException {
        return (ListDto)super.clone();
    }

    @Override
    public String toString() {
        return this.hashCode()+"\tListDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}