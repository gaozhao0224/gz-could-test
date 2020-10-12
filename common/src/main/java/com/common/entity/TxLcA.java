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
public class TxLcA implements Serializable,Cloneable {

    private static final long serialVersionUID = 1L;

    private String lcaName;

    private String lcaVersion;

    @Override
    public TxLcA clone() throws CloneNotSupportedException {
        return (TxLcA)super.clone();
    }
}
