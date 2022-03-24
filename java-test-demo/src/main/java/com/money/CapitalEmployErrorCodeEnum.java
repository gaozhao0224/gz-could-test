package com.money;

import lombok.Getter;

/**
 * @ClassName: CapitalEmployErrorCodeEnum
 * @Description: 维修资金使用错误码
 * @Author: GaoZhao
 * @Date 2022/1/13
 */
@Getter
public enum CapitalEmployErrorCodeEnum {

    WXZQXX_FWXZ_SW_ZRZ("600011","商维性质只有房屋参与分摊，不存在楼幢！"),
    WXZQXX_RWD_QS("600012","该支取业务缺失维修项目！"),
    WXZQXX_FWXZ_BCZ("600013","不存在该房屋性质！"),
    WXZQXX_FTFS_BCZ("600014","不存在该分摊方式！"),

    WXZQXX_SW_ZRZ_BCZ("600031","自然幢信息为空！"),
    WXZQXX_YZ_FWXX_YC("600032","业主房屋信息查询异常！"),

    WXZQXX_ZQBH_BCZ("600041","支取信息获取异常！"),


    WXZQXX_ZQBH_CYYC("600051","本次拨付金额超过支取金额！"),
    WXZQXX_ZQBH_MX_FTYC("600052","本次分摊拨付明细金额总和不能大于本次拨付总金额！"),
    WXZQXX_ZQBH_MX_ZHYC("600053","拨付明细应支取明细金额总和不能大于支取总和！！"),

    WXZQXX_BF_ISEND("601021","该笔拨付订单已结束"),
    WXZQXX_BF_NOTALLOW("601022","当前状态不允许该操作"),
    WXZQXX_BF_ADDERROR("601023","资金拨付新增异常"),
    WXZQXX_BF_YEBZ("601024","可拨付余额不足"),
    WXZQXX_BF_YWLSHNULL("601025","业务流水号不能未空"),
    WXZQXX_BF_ISSTOP("601026","该笔支取订单已经终止"),
    WXZQXX_RECORD_ISDELETE("601027","该条记录已被删除"),
    WXZQXX_DATA_ERROR("601028","数据错误"),
    WXZQXX_BCBFJE_NULL("601029","本次拨付金额不能为空"),

    WXZQXX_SP_FQ("600021","审批发起失败！"),
    WXZQXX_SP_CX("600022","审批撤销失败！"),
    WXZQXX_SP_TH("600023","审批退回失败！"),
    WXZQXX_SP_TG("600024","审批通过失败！");



    private String failCode;

    private String failMsg;

    CapitalEmployErrorCodeEnum(String failCode, String failMsg) {
        this.failCode = failCode;
        this.failMsg = failMsg;
    }
}
