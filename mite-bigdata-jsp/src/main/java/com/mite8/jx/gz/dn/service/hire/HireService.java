package com.mite8.jx.gz.dn.service.hire;

import com.mite8.jx.gz.dn.service.hire.utils.OptHire;
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
 * Desc:  招聘Service
 */
@Service
public class HireService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(HireService.class.getName());


    public void getHire(){
        OptHire.optHire(jdbcTemplate);
    }
}
