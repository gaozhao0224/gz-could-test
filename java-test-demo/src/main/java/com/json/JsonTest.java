package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class JsonTest {
    public static void main(String[] args) {
        String jsonStr = "{\"zsxxList\":[{\"csky\":false,\"swjgDm\":\"14101940000\",\"se\":2.0,\"zsSzxxList\":[{\"skssqz\":\"2021-07-29\",\"swjg\":\"国家税务总局郑州市中原区税务局\",\"sjje\":2.0,\"zfsj\":\"2021-09-18 16:48:24\",\"zsxmDm\":\"304331300\",\"sfbz\":\"S\",\"zsxmMc\":\"建设行政事业性收费收入-城镇垃圾处理费\",\"skssqq\":\"2021-04-01\"}],\"feiOrShui\":\"S\",\"yhd\":true,\"yhdpzje\":2.0,\"dzsphm\":\"341016210900188086\",\"jkqx\":\"2021-08-30 00:00:00\",\"jkfs\":\"yhd\"},{\"csky\":false,\"swjgDm\":\"14101940000\",\"se\":22.0,\"zsSzxxList\":[{\"skssqz\":\"2021-07-31\",\"swjg\":\"国家税务总局郑州市中原区税务局\",\"sjje\":22.0,\"zfsj\":\"2021-09-18 16:52:21\",\"zsxmDm\":\"304331300\",\"sfbz\":\"S\",\"zsxmMc\":\"建设行政事业性收费收入-城镇垃圾处理费\",\"skssqq\":\"2021-06-01\"}],\"feiOrShui\":\"S\",\"yhd\":true,\"yhdpzje\":22.0,\"dzsphm\":\"341016210900188087\",\"jkqx\":\"2021-08-31 00:00:00\",\"jkfs\":\"yhd\"}]}";
        JSONObject parse = (JSONObject) JSON.parse(jsonStr);
        List<Map<String,Object>> zsxxList = (List<Map<String, Object>>) JSONArray.parse(parse.get("zsxxList").toString());
        zsxxList.forEach(l->{
            List<Map<String,Object>> zsSzxxList = (List<Map<String, Object>>) JSONArray.parse(l.get("zsSzxxList").toString());
            zsSzxxList.forEach(z->{
                System.out.println(z.get("zfsj"));
                z.put("zfsj","0");
            });
            l.put("zsSzxxList",zsSzxxList);
        });
        parse.put("zsxxList",zsxxList);
        System.out.println(parse);
    }
}