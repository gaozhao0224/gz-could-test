package com.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gz
 * @since 2020-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TxLcB implements Serializable {

    private static final long serialVersionUID = 1L;

    private String lcbName;

    private String lcbVersion;


}
