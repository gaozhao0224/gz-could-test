package com.crc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 受试者和调查项类型表
 * </p>
 *
 * @author gz
 * @since 2021-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BsTableType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemId;

    private String itemName;

    private String tableName;

    /**
     * 1未审核完成   2审核完成  3待审核
     */
    @TableField(exist = false)
    private String status;

}
