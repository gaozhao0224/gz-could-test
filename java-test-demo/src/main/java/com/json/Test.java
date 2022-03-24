package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @ClassName: Test
 * @Description:对象转json存在属性为null的时候 会不会消失
 * @Author: gz
 * @Date 2021/11/25
 */
/**
*   Fastjson的SerializerFeature序列化属性
    QuoteFieldNames———-输出key时是否使用双引号,默认为true
    WriteMapNullValue——–是否输出值为null的字段,默认为false
    WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
    WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
    WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
    WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
* */
public class Test {
    public static void main(String[] args) {
        JsonObj obj1 = new JsonObj("1", "张三", null);
        JsonObj obj2 = new JsonObj("1", "张三");
        JsonObj obj3 = new JsonObj("2", "", 1);
        JsonObjSer objSer1 = new JsonObjSer("1", "张三", null);
        JsonObjSer objSer2 = new JsonObjSer("2", "", 1);
        String s1 = JSON.toJSONString(obj1, SerializerFeature.WriteMapNullValue);
        String s2 = JSON.toJSONString(obj2,SerializerFeature.WriteMapNullValue);
        String s3 = JSON.toJSONString(obj3);
        String s4 = JSON.toJSONString(objSer1);
        String s5 = JSON.toJSONString(objSer2);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
    }
}
