package com.crc.controller;


import cn.hutool.core.util.IdUtil;
import com.crc.config.response.AjaxResult;
import com.crc.config.response.R;
import com.crc.entity.SysCrcFile;
import com.crc.service.ISysCrcFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

/**
 * <p>
 * 系统文件 前端控制器
 * </p>
 *
 * @author gz
 * @since 2021-04-18
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class SysCrcFileController {

    @Autowired
    private ISysCrcFileService iSysCrcFileService;


    @PostMapping("/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("subjectId") String subjectId,@RequestParam("examineId") String examineId){

        return iSysCrcFileService.uploadFile(file,subjectId,examineId);

    }
    @GetMapping("/downloadFile")
    @ResponseBody
    public void downloadFile(HttpServletResponse response, String id) throws Exception {
        iSysCrcFileService.downloadFile(response,id);
    }
    @GetMapping("/fileInfo")
    @ResponseBody
    public AjaxResult fileInfo(String id) throws Exception {
        SysCrcFile byId = iSysCrcFileService.getById(id);
        return AjaxResult.success(byId);
    }
}
