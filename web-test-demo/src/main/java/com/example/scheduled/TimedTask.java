package com.example.scheduled;

import cn.hutool.core.util.IdUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimedTask {

    /*
    * 合同过期修改状态
    * */
    @Scheduled(cron = "${cron}")
    public void contractStaleDated() {
        System.out.println("随机生成id="+IdUtil.simpleUUID());
    }

}