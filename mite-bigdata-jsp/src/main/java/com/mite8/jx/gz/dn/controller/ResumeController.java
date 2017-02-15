package com.mite8.jx.gz.dn.controller;

import com.mite8.jx.gz.dn.service.resume.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Created: 2016/10/14
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des:  简历人才舆情接口
 */

@RestController
public class ResumeController {

    private final Logger logger = Logger.getLogger(ResumeController.class.getName());

    @Autowired
    private ResumeService resumeService;

    //定时任务调用-问政更新
    @RequestMapping(value = "/task_XX/resume/crontab/begin")
    public String runTaskBack(){
        logger.info("TASK BEGIN!");
        Thread t = new Thread(){
            public void run() {
                try {
                    resumeService.getResume();
                } catch (Exception e){
                    logger.info("TASK ERROR: " + e);
                }
            }
        };
        t.start();
        return "OK";
    }

}

