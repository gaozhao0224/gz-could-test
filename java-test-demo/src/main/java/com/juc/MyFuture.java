package com.juc;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * @ClassName: MyFuture
 * @Description: 异步回调
 * @Author: GaoZhao
 * @Date 2022/2/19
 */
public class MyFuture {
    private static final String CONTENT_TYPE = "application/json";
    public static void main(String[] args) {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "北京");
        String body = HttpRequest.post("http://localhost:5002/firstStep/test/gz")
                .contentType(CONTENT_TYPE)
                .body(JSON.toJSONString(paramMap))
                .timeout(10000).execute().body();
        System.out.println(body);
    }
}
