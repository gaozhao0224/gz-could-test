package com.example.contract.controller;


import com.example.contract.service.IGzPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gz
 * @since 2020-11-27
 */
@RestController
@RequestMapping("/contract/gz-person")
@Slf4j
public class GzPersonController {
    @Autowired
    private IGzPersonService iGzPersonService;
    @GetMapping("test")
    public String getTest(){
        iGzPersonService.getTst();
        return "ok";
    }
}
