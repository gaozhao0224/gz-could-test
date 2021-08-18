package com.crc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crc.config.context.HttpContext;
import com.crc.config.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * 控制器基类，封装一些公共方法，如分页等
 * gz
 */
public abstract class BaseController {

    /**
     * 封装分页参数
     * @param <T>
     * @return
     */
    protected <T> IPage<T> initPage() {
        IPage<T> page = new Page<>();
        HttpServletRequest request = HttpContext.getHttpServletRequest();
        String current = request.getParameter("current");
        String size = request.getParameter("size");
        if (StringUtils.isEmpty(current) || StringUtils.isEmpty(size) || Integer.valueOf(current) < 1 || Integer.valueOf(size) < 1) {
            throw new BusinessException("分页查询参数错误！");
        }
        page.setCurrent(Integer.valueOf(current));
        page.setSize(Integer.valueOf(size));
        return page;
    }
}
