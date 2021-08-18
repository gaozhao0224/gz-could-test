package com.crc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户对应项目中心
 * </p>
 *
 * @author gz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUicRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemId;

    private String userId;

    private String centreId;

}
