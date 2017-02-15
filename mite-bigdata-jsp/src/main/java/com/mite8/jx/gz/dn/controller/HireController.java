package com.mite8.jx.gz.dn.controller;

import com.mite8.jx.gz.dn.service.hire.HireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Created: 2016/10/14
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des:  招聘舆情接口
 */

@RestController
public class HireController {

    private final Logger logger = Logger.getLogger(HireController.class.getName());

    @Autowired
    private HireService hireService;

    //定时任务调用-问政更新
    @RequestMapping(value = "/task_XX/hire/crontab/begin")
    public String runTaskBack(){
        logger.info("TASK BEGIN!");
        Thread t = new Thread(){
            public void run() {
                try {
                    hireService.getHire();
                } catch (Exception e){
                    logger.info("TASK ERROR: " + e);
                }
            }
        };
        t.start();
        return "OK";
    }

}

