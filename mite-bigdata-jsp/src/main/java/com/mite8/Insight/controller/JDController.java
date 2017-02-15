package com.mite8.Insight.controller;

import com.mite8.Insight.jd_wumai.OptJDcomments;
import com.mite8.Insight.movie_great_wall.MovieService;
import com.mite8.Insight.movie_great_wall.MovieTagOffLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Author: blogchong
 * Created: 2016/10/14
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des:  豆瓣电影接口
 */

@RestController
public class JDController {

    private final Logger logger = Logger.getLogger(JDController.class.getName());

    @Autowired
    private OptJDcomments optJDcomments;

    //定时任务调用-问政更新
    @RequestMapping(value = "/task_XX/wm/crontab/begin")
    public String runJD(){
        logger.info("TASK BEGIN!");
        Thread t = new Thread(){
            public void run() {
                try {
                    optJDcomments.getJDComments();
                } catch (Exception e){
                    logger.info("TASK ERROR: " + e);
                }
            }
        };
        t.start();
        return "OK";
    }

}

