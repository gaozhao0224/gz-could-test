package com.common.vo;

import lombok.Data;

import java.util.Map;

@Data
public class ConditionVO {

    private String search;

    private Map<String, Object> extSearch;

    private String[] ascs;

    private String[] descs;

    private String tid;

    private String isadmin;


}
