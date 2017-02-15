package com.mite8.jx.gz.dn.service.resume;

import com.mite8.jx.gz.dn.service.resume.utils.OptResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Author: blogchong
 * Time:  2016/11/25.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  简历Service
 */
@Service
public class ResumeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(ResumeService.class.getName());


    public void getResume(){
        OptResume.optResume(jdbcTemplate);
    }
}
