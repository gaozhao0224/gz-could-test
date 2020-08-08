package com.example.config.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/configtest")
@RefreshScope  //动态刷新  git上修改 8110自动修改 为了本机不重启可以通过在dos窗口执行
               // curl -X POST "http://localhost:8101/actuator/refresh" 不引入bus前
public class TestController {

    @Value("${person.name}")
    private String name ;
    @Value("${person.age}")
    private String age ;
    @Value("${server.port}")
    private String port ;
    /*
    * 输出结果是bootstrap里面的内容  因为优先级高会覆盖application里得person值
    *
    * */
    @RequestMapping("/config")
    public Object getConfig(){
        return "姓名是 = "+name+"\t年纪是="+age+"\t环境是="+"端口号是="+port;
    }
}