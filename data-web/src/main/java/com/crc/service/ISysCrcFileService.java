package com.crc.service;

import com.crc.config.response.AjaxResult;
import com.crc.entity.SysCrcFile;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

/**
 * <p>
 * 系统文件 服务类
 * </p>
 *
 * @author gz
 * @since 2021-04-18
 */
public interface ISysCrcFileService extends IService<SysCrcFile> {

    AjaxResult uploadFile(MultipartFile file, String subjectId, String examineId);

    void downloadFile(HttpServletResponse response, String id) throws Exception;
}
