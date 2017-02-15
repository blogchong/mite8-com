package com.mite8.Insight.movie_great_wall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

/**
 * Author: blogc
 * Time:  2016/12/17.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 服务入口
 */
@Service
public class MovieService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(MovieService.class.getName());

    public void getMovie(String id){
        OptMovie.optMovie(id,jdbcTemplate);
    }
}
