package com.crc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 检查员对该受试者项目种类意见和表类意见  统一关联
 * </p>
 *
 * @author gz
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CeSubjectTtEt extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subjectId;

    private String tableTypeId;

    private String examineTypeId;

    private String opinion;

    /**
     * 1未审核完成   2审核完成  3待审核
     */
    private String status;

}
