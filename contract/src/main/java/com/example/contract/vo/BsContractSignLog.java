package com.example.contract.vo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gz
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bs_contract_sign_log")
public class BsContractSignLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 历史合同主键
     */
    private String contractId;


    private String tid;

    private String remark;

    /**
     * 回调给我的操作人id
     */
    private String creatorId;

    /**
     * 回调给我的操作人name
     */
    private String creatorName;

    private String status;


}
