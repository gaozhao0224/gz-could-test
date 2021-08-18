package com.crc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 受试者和调查项关联表
 * </p>
 *
 * @author gz
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CeSubjectExamine extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String examineId;

    private String subjectId;

    private String tableTypeId;

    private String examineTypeId;

    /**
     * 答案的值
     */
    private String anValue;
    /**
     * 其他答案的值
     */
    private String elseValue;
    /**
     * 文件类型获取地址
     */
    private String fileUrlValue;


}
