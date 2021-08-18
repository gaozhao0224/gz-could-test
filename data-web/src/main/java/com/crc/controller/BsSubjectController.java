package com.crc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.config.response.AjaxResult;
import com.crc.entity.BsSubject;
import com.crc.service.IBsSubjectService;
import com.crc.util.CommonNumberUtil;
import com.crc.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 受试者信息 前端控制器
 * </p>
 *
 * @author gz
 * @since 2021-04-18
 */
@RestController
@RequestMapping("/subject")
@Slf4j
public class BsSubjectController extends BaseController{

    @Autowired
    private IBsSubjectService iBsSubjectService ;

    
    /*
     * 受试者列表
     * */
    @GetMapping("/subjectList")
    public AjaxResult list(String itemId, String centreId, String search,String type){
        try {
            if(!StringUtils.isEmpty(type) && type.equals(CommonNumberUtil.ONEStr)){
                QueryWrapper<BsSubject> bsSubjectQueryWrapper = new QueryWrapper<>();
                bsSubjectQueryWrapper.eq("item_id",itemId);
                bsSubjectQueryWrapper.eq("centre_id",centreId);
                List<BsSubject> list = iBsSubjectService.list(bsSubjectQueryWrapper);
                log.info("查询受试者信息成功：", list);
                return AjaxResult.success(list);
            }else{
                IPage<BsSubject> page = initPage();
                IPage<BsSubject> list = iBsSubjectService.getList(page,itemId,centreId,search);
                log.info("查询受试者信息成功：", list);
                return AjaxResult.success(list);
            }
        }catch (Exception e){
            log.error("查询受试者信息失败", e);
            throw e;
        }
    }


    /*
     * 新增受试者
     * */
    @PostMapping("/subjectSave")
    public AjaxResult save(@RequestBody BsSubject bsSubject) throws Exception {

        try {
            log.info("新增入参受试者信息：{}", bsSubject);
            boolean save = iBsSubjectService.save(bsSubject);
            if (save){
                return AjaxResult.success(bsSubject);
            }else {
                return AjaxResult.error("新增受试者信息失败");
            }
        }catch (Exception e){
            log.error("新增受试者信息失败", e);
            throw e;
        }
    }
    /*
     * 删除受试者
     * */
    @GetMapping("/subjectDelete")
    public AjaxResult delete(@RequestParam String id){
        try {
            boolean del = iBsSubjectService.removeById(id);
            if(del){
                return AjaxResult.success(del);
            }else {
                return AjaxResult.error("不存在改受试者");
            }
        }catch (Exception e){
            log.error("删除受试者信息失败", e);
            throw e;
        }
    }
    /*
     * 修改受试者
     * */
    @PostMapping("/subjectEdit")
    public AjaxResult edit(@RequestBody BsSubject bsSubject){

        try {
            log.info("编辑入参受试者信息：{}", bsSubject);
            boolean edit = iBsSubjectService.updateById(bsSubject);
            return AjaxResult.success(edit);
        }catch (Exception e){
            log.error("编辑受试者信息失败", e);
            throw e;
        }
    }
    /*
     * 受试者详情
     * */
    @GetMapping("/subjectInfo")
    public AjaxResult subjectInfo(@RequestParam String id){

        try {
            BsSubject byId = iBsSubjectService.getById(id);
            if (byId!=null){
                return AjaxResult.success(byId);
            }else {
                return AjaxResult.error("受试者信息为空");
            }

        }catch (Exception e){
            log.error("编辑受试者信息失败", e);
            throw e;
        }
    }


}
