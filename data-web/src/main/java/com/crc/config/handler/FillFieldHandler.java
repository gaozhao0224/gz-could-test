package com.crc.config.handler;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.crc.util.DateTimeUtils;
import com.crc.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
/*
* 数据库公共字段处理
* gz
* */
public class FillFieldHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("creator", SecurityUtils.getUserId() == null ? null : SecurityUtils.getUserId() , metaObject);
        this.setFieldValByName("createtime", DateTimeUtils.generalFormatNow(), metaObject);
        this.setFieldValByName("ts", DateTimeUtils.generalFormatNow(), metaObject);
//        this.setFieldValByName("dr", 0, metaObject);
        this.setFieldValByName("version", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    	this.setFieldValByName("modifier", SecurityUtils.getUserId() == null ? null : SecurityUtils.getUserId(), metaObject);
        this.setFieldValByName("ts", DateTimeUtils.generalFormatNow(), metaObject);
        //this.setFieldValByName("version", IdUtil.simpleUUID(), metaObject);
    }

}
