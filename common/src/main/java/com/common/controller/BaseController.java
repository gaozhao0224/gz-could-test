package com.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.context.HttpContext;
import com.common.context.SpringContext;
import com.common.exception.BusinessException;
import com.common.exception.RuntimeExceptionWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 控制器基类，封装一些公共方法，如分页等
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

    /**
     * 下载文件
     * @param filename
     * @param data
     */
    protected void downFile(String filename, byte[] data) {
        try {
            HttpServletResponse response = HttpContext.getHttpServletResponse();
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("content-disposition", "attachment;filename=" + encodeFilename(filename));
            response.setCharacterEncoding(StringPool.UTF_8);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeExceptionWrapper("文件下载失败！", e);
        }
    }

    /**
     * 根据不同平台对文件名进行编码
     * @param filename
     * @return
     * @throws UnsupportedEncodingException
     */
    protected String encodeFilename(String filename) throws UnsupportedEncodingException {
        HttpServletRequest request = HttpContext.getHttpServletRequest();
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        if (userAgent.contains("windows")) {
            filename = URLEncoder.encode(filename, StringPool.UTF_8);
        } else {
            filename = new String((filename).getBytes(StringPool.UTF_8), StringPool.ISO_8859_1);
        }
        return filename;
    }

    /**
     * 不封装返回值，直接写到response
     * @param result
     */
    protected void writeResponse(Object result) {
        try {
            HttpServletResponse response = HttpContext.getHttpServletResponse();
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpStatus.OK.value());
            OutputStream outputStream = response.getOutputStream();
            byte[] data = (result instanceof CharSequence ? result.toString() : SpringContext.getBean(ObjectMapper.class).writeValueAsString(result)).getBytes();
            outputStream.write(data);
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeExceptionWrapper(e);
        }
    }

}
