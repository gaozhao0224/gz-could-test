package com.example.test;

import lombok.Data;

@Data
public class ListDto implements Cloneable{

    private String name;

    private Integer age;

    @Override
    protected ListDto clone() throws CloneNotSupportedException {
        return (ListDto)super.clone();
    }

    @Override
    public String toString() {
        return this.hashCode()+"\tListDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}