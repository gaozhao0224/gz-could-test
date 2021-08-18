package com.crc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 调查项种类表
 * </p>
 *
 * @author gz
 * @since 2021-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BsExamineType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemId;

    private String itemName;

    private String examineTypeName;

    private String tableTypeId;

    private String tableTypeName;

    /**
     * 1未审核完成   2审核完成  3待审核
     */
    @TableField(exist = false)
    private String status;
    /**
     * 意见
     */
    @TableField(exist = false)
    private String opinion;
}
