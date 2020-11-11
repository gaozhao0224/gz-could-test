package com.example.xxl.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class SampleXxlJob {

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("gz")
    public void gz() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");
            System.out.println("111111111111111111111111111-------------------------------------------11111111111111111111111111111111");
        }
}