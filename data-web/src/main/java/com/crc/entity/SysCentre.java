package com.crc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 中心
 * </p>
 *
 * @author gz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysCentre extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String centreName;

    private String centreCode;

    private String itemId;

    private String itemName;



}
