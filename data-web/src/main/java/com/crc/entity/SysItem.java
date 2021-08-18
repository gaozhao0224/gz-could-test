package com.crc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author gz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemName;

    private String itemCode;
    @TableField(exist = false)
    private List<SysCentre> centreList;



}
