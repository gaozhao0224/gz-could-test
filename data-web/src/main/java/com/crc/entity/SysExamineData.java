package com.crc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 病例审查数据
 * </p>
 *
 * @author gz
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysExamineData extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 题目
     */
    private String topic;

    /**
     * 题目类型(1.文本 2.时间 3.单选 4.图片 5.附件)
     */
    private String type;

    /**
     * 表类型
     */
    private String tableTypeId;

    /**
     * 调查项类型
     */
    private String examineTypeId;

    /**
     * 单选是否的时候哪个触发下一级
     */
    private String childLevel;

    /**
     * 当前题目为第几级
     */
    private String ceLevel;


    private String itemId;

    /**
     * 题目顺序
     */
    private String ceOrder;


    /**
     * 答案的值 从关联表取
     */
    @TableField(exist = false)
    private String anValue;
    /**
     * 其他答案的值 从关联表取
     */
    @TableField(exist = false)
    private String elseValue;
    /**
     * 文件类型获取地址 从关联表取
     */
    @TableField(exist = false)
    private String fileUrlValue;
    /**
     * 子级
     */
    @TableField(exist = false)
    private List<SysExamineData> sysExamineDatas;




}
