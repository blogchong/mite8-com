package com.mite8.Insight.controller;

import com.mite8.Insight.movie_great_wall.MovieService;
import com.mite8.Insight.movie_great_wall.MovieTagOffLineService;
import com.mite8.jx.gz.dn.service.hire.HireService;
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
public class MovieController {

    private final Logger logger = Logger.getLogger(MovieController.class.getName());

    @Autowired
    private MovieService movieService;

    //定时任务调用-问政更新
    @RequestMapping(value = "/task_XX/movie/crontab/begin")
    public String runMovie(@RequestParam(value = "id", defaultValue = "6982558") final String id){
        logger.info("TASK BEGIN!");
        Thread t = new Thread(){
            public void run() {
                try {
                    movieService.getMovie(id);
                } catch (Exception e){
                    logger.info("TASK ERROR: " + e);
                }
            }
        };
        t.start();
        return "OK";
    }

    //定时任务调用-问政更新
    @Autowired
    private MovieTagOffLineService movieTagOffLineService;
    @RequestMapping(value = "/task_XX/movie_tags/crontab/begin")
    public String runMovieTag(){
        logger.info("TASK BEGIN!");
        Thread t = new Thread(){
            public void run() {
                try {
                    movieTagOffLineService.movieTagOffLine();
                } catch (Exception e){
                    logger.info("TASK ERROR: " + e);
                }
            }
        };
        t.start();
        return "OK";
    }


}

