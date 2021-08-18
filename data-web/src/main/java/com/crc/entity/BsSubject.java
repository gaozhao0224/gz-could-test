package com.crc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 受试者信息
 * </p>
 *
 * @author gz
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BsSubject extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subjectName;

    private String subjectNameSpell;

    private String subjectCode;

    private String startDate;

    private String endDate;

    private String subjectCrfStatus;

    private String subjectStatus;

    private String itemId;

    /**
     * 冗余名称减少不必要数据库查询
     */
    private String itemName;

    private String centreId;

    /**
     * 冗余名称减少不必要数据库查询
     */
    private String centreNanme;



}
