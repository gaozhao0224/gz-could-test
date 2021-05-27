package com.demo;

import lombok.Data;

/*
* 接口消费信息统计实体
* */
@Data
public class ApiStatisticsInfo {

    private String userId;

    //-----------返回页面字段
    //企业名称
    private String userName;
    //企业税号
    private String userCode;
    //应用名称
    private String applyName;
    //查验票量
    private Integer checkDosage;
    //调用次数
    private Integer numberCalls;

    //-----------业务返回接收字段
    //应用appkey
    private String appKey;
    //客户税号
    private String entCode;
    //接口id
    private String apiId;
    //请求次数
    private String requestCount;
    //成功次数
    private String consumeCount;
}