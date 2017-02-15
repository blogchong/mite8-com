package com.mite8.controller.mite.json;

import com.mite8.service.BigdataService;
import com.mite8.service.JDCommentsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: blogchong
 * Time:  2016/12/20.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  行业洞察json入口
 */
@RestController
public class MiteInsightJsonController {

    //大数据行业报告
    @Autowired
    private BigdataService bigdataService;
    @RequestMapping(value = "/json_XX/insight/bigdata")
    public JSONObject getBigData() throws Exception{
        return bigdataService.bigData();
    }

    //JD-C行业报告
    @Autowired
    private JDCommentsService jdCommentsService;
    @RequestMapping(value = "/json_XX/insight/jd_comments_wm")
    public JSONObject getJDComments() throws Exception{
        return jdCommentsService.jdComments();
    }

}
