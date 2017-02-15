package com.mite8.jx.gz.dn.controller;

import com.mite8.jx.gz.dn.service.ask_politics.AskPoliticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Created: 2016/10/14
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des:  问政舆情接口
 */

@RestController
public class PoliticsController {

    private final Logger logger = Logger.getLogger(PoliticsController.class.getName());

    @Autowired
    private AskPoliticsService askPoliticsService;

    //定时任务调用-问政更新
    @RequestMapping(value = "/task_XX/politics/crontab/begin")
    public String runTaskBack(){
        logger.info("TASK BEGIN!");
        Thread t = new Thread(){
            public void run() {
                try {
                    askPoliticsService.askPolitics();
                } catch (Exception e){
                    logger.info("TASK ERROR: " + e);
                }
            }
        };
        t.start();
        return "OK";
    }

}

