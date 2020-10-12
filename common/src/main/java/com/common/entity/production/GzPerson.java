package com.common.entity.production;

import com.baomidou.mybatisplus.annotation.TableName;
import com.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author gz
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gz_person")
public class GzPerson extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    /**
     * 0为个人签署方，1为企业签署方
     */
    private String sex;

    private String age;

    @Override
    public String toString() {
        return this.hashCode()+"\tGzPerson{" +
                "userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
