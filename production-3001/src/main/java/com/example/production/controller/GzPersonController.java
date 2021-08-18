package com.example.production.controller;


import com.common.entity.production.GzPerson;
import com.example.production.service.IGzPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        return gzPersonService.getSaveOrUpdate(gzPerson);
    }
    @PostMapping("/timeOut")
    public Object timeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(7);
        System.out.println("production 自己设置自己得超时时间为10s  consumer的为5s 然后consumer到production的时候睡7s，" +
                "如果不报错代表超时时间只是自己这个方法执行的超时时间，但是consumer那调这个的时候那个方法里面的走的时间应该也包含这个7s把 试试");
        return "production 自己设置自己得超时时间为10s  consumer的为5s 然后consumer到production的时候睡7s，" +
                "如果不报错代表超时时间只是自己这个方法执行的超时时间，但是consumer那调这个的时候那个方法里面的走的时间应该也包含这个7s把 试试";
    }
    @PostMapping("/test")
    public void test(@RequestParam String name){
        System.out.println(name);
    }

    @PostMapping("/getGzPerson")
    public GzPerson getGzPerson(@RequestParam String id){
        GzPerson gzPerson = new GzPerson();
        gzPerson.setId(id);
        gzPerson.setUserName("张三李四");
        gzPerson.setSex("男");
        return gzPerson;
    }

    @GetMapping("/getUiBotVal")
    public String getUiBotVal(){
        Random random = new Random();
        if(random.nextInt(10)>5){
            return "hello uiBot";
        }else{
            return ">5";
        }
    }

}
