package com.mite8.jx.gz.dn.service.public_praise;

import com.mite8.jx.gz.dn.service.public_praise.utils.OptPraise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Author: blogchong
 * Time:  2016/11/25.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  口碑评价Service
 */
@Service
public class PublicPraiseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(PublicPraiseService.class.getName());

    public void publicPraise() {
        OptPraise.optPraise(jdbcTemplate);
    }

}
