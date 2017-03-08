package com.mite8.wechat.wpweixin_com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Created: 2016/10/14
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des:  微果酱数据爬取http://data.wpweixin.com/接口
 */

@RestController
public class WechatController {

    private final Logger logger = Logger.getLogger(WechatController.class.getName());

    @Autowired
    private WpweixinService wpweixinService;

    //定时任务调用-问政更新
    @RequestMapping(value = "/XXX/XXX/task")
    public String runJD(){
        logger.info("TASK BEGIN!");
        Thread t = new Thread(){
            public void run() {
                try {
                    wpweixinService.getWechatData();
                } catch (Exception e){
                    logger.info("TASK ERROR: " + e);
                }
            }
        };
        t.start();
        return "OK";
    }

}

