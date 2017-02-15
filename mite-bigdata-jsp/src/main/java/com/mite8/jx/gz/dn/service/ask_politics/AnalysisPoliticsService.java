package com.mite8.jx.gz.dn.service.ask_politics;

import com.mite8.jx.gz.dn.service.ask_politics.utils.OptPolitics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Author: blogchong
 * Time:  2016/11/22.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  问政舆情-Service
 */
@Service
public class AnalysisPoliticsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(AnalysisPoliticsService.class.getName());

    public void analysisPolitics(){
//        OptPolitics.optPolitics(jdbcTemplate);
    }

}
