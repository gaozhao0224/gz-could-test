package com.json;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: JsonObj
 * @Description: 转json对象测试  尝试加和不加Serializable
 * @Author: gz
 * @Date 2021/11/25
 */
@Data
public class JsonObj {
    private String id ;
    private String name ;
    private Integer age ;

    public JsonObj(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public JsonObj(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public JsonObj(String id) {
        this.id = id;
    }
}
