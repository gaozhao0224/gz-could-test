package com.crc.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crc.config.response.AjaxResult;
import com.crc.entity.SysCrcFile;
import com.crc.mapper.SysCrcFileMapper;
import com.crc.service.ISysCrcFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

/**
 * <p>
 * 系统文件 服务实现类
 * </p>
 *
 * @author gz
 * @since 2021-04-18
 */
@Service
@Transactional
@Slf4j
public class SysCrcFileServiceImpl extends ServiceImpl<SysCrcFileMapper, SysCrcFile> implements ISysCrcFileService {
    // 文件保存路径
    @Value("${file-download.url}")
    private String fileUrl;


    @Override
    public AjaxResult uploadFile(MultipartFile file,String subjectId,String examineId) {
        log.info("上传文件信息：{}；文件名：{}",file,file.getOriginalFilename());
        SysCrcFile sysCrcFile = new SysCrcFile();
        sysCrcFile.setSubjectId(subjectId);
        sysCrcFile.setExamineId(examineId);
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        if(StringUtils.isEmpty(fileName)){
            return AjaxResult.error("上传文件不能为空");
        }
        sysCrcFile.setOriginalName(fileName);
        // 获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        sysCrcFile.setFileType(suffixName);
        String code = IdUtil.simpleUUID();
        // 文件重命名，防止重复
        fileName = fileUrl + code + suffixName;
        sysCrcFile.setFileName(fileName);
        sysCrcFile.setFileCode(code);
        sysCrcFile.setShowUrl("file/"+code+suffixName);
        log.info("图片路径：{}",fileName);
        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
            save(sysCrcFile);
            return AjaxResult.success("上传成功",sysCrcFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.error("上传失败");
    }

    @Override
    public void downloadFile(HttpServletResponse response, String id) throws Exception {
        SysCrcFile byId = getById(id);
        if (byId==null){
            throw new RuntimeException("该文件不存在！");
        }
        // 文件地址，真实环境是存放在数据库中的
        File file = new File(byId.getFileName());
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        //获取文件后缀
        String suffixName = byId.getFileType();
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + IdUtil.simpleUUID() +suffixName);
        // 创建输出对象
        OutputStream os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }
}
