package com.example.contract.controller;


import com.common.annotation.AutoId;
import com.common.controller.BaseController;
import com.common.properties.SnowflakeIdProperties;
import com.example.contract.service.IBsContractSignLogService;
import com.example.contract.vo.BsContractSignLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.TimerTask;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gz
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/contract/bsContractSignLog")
@Slf4j
public class BsContractSignLogController extends BaseController {

    @Autowired
    private IBsContractSignLogService bsContractSignLogService;

    @RequestMapping("/list")
    public Object test(){
        List<BsContractSignLog> list = bsContractSignLogService.list();
        return list;
    }
    @RequestMapping("/add")
    public Object add(BsContractSignLog bsContractSignLog){
        boolean save = bsContractSignLogService.save(bsContractSignLog);
        //boolean save = bsContractSignLogService.save1(bsContractSignLog);
        return save;
    }



}
