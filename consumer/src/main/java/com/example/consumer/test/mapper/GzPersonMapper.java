package com.example.consumer.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.entity.production.GzPerson;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gz
 * @since 2020-08-06
 */
@Mapper
public interface GzPersonMapper extends BaseMapper<GzPerson> {

}
