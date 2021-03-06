package com.example.production.controller;


import com.common.entity.production.GzPerson;
import com.example.production.service.IGzPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gz
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/production")
public class GzPersonController {

    @Autowired
    private IGzPersonService gzPersonService;
    @PostMapping("/addPerson")
    public Object addPerson(@RequestBody GzPerson gzPerson){
        return gzPersonService.saveOrUpdate(gzPerson);
    }

}
