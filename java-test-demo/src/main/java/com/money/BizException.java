package com.money;

import lombok.Data;

/**
* @version: 1.0.1
* @author: lianyutao
* @date: 2020/06/07 17:35
* @description: BaseQuery
**/

@Data
public class BizException extends RuntimeException {

    private String failCode;

    private String failMsg;

    public BizException(String failCode, String failMsg) {
        super(failMsg);
        this.failCode = failCode;
        this.failMsg = failMsg;
    }

    public BizException(String failMsg) {
        super(failMsg);
        this.failCode = BaseFailCodeAndMsg.FAIL_DEFAULT.getFailCode();
        this.failMsg = failMsg;
    }

}

