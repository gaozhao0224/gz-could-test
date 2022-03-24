package com.json;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * @ClassName: JsonObjSer
 * @Description:转json对象测试  尝试加和不加Serializable
 * @Author: gz
 * @Date 2021/11/25
 */
@Data
@AllArgsConstructor
class JsonObjSer implements Serializable {
    private static final long serialVersionUID = -3328492116864120238L;
    private String id ;
    private String name ;
    private Integer age ;
}
