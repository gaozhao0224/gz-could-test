package com.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.common.util.DateTimeUtils;
import com.common.uuid.SnowflakeIdWorker;
import org.apache.ibatis.reflection.MetaObject;

public class FillFieldHandler implements MetaObjectHandler {

    private SnowflakeIdWorker snowflakeIdWorker;

    public FillFieldHandler(SnowflakeIdWorker snowflakeIdWorker) {
        this.snowflakeIdWorker = snowflakeIdWorker;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("id", snowflakeIdWorker.nextId().toString(), metaObject);
        //this.setFieldValByName("creator", UserContext.getCurUser() == null ? null : UserContext.getCurUser().getId(), metaObject);
        this.setFieldValByName("creator", "gz", metaObject);
        this.setFieldValByName("createtime", DateTimeUtils.generalFormatNow(), metaObject);
        this.setFieldValByName("ts", DateTimeUtils.generalFormatNow(), metaObject);
        this.setFieldValByName("dr", 0, metaObject);
        this.setFieldValByName("version", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    	//this.setFieldValByName("modifier", UserContext.getCurUser() == null ? null : UserContext.getCurUser().getId(), metaObject);
        this.setFieldValByName("ts", DateTimeUtils.generalFormatNow(), metaObject);
    }

}
