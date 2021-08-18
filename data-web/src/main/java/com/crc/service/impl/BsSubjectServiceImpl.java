package com.crc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.SysUser;
import com.crc.entity.BsSubject;
import com.crc.mapper.BsSubjectMapper;
import com.crc.service.IBsSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 受试者信息 服务实现类
 * </p>
 *
 * @author gz
 * @since 2021-04-18
 */
@Service
@Transactional
public class BsSubjectServiceImpl extends ServiceImpl<BsSubjectMapper, BsSubject> implements IBsSubjectService {

    @Override
    public IPage<BsSubject> getList(IPage<BsSubject> page, String itemId, String centreId, String search) {
        QueryWrapper<BsSubject> bsSubjectQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(itemId)) {
            bsSubjectQueryWrapper.eq("item_id",itemId);
        }
        if (StringUtils.isNotEmpty(centreId)) {
            bsSubjectQueryWrapper.eq("centre_id",centreId);
        }
        if (StringUtils.isNotEmpty(search)) {
            bsSubjectQueryWrapper.and(i->i.like("subject_name",search).or().like("subject_name_spell",search).like("subject_code",search));
        }
        IPage<BsSubject> pageList = super.page(page,bsSubjectQueryWrapper);
        return pageList;
    }

}
