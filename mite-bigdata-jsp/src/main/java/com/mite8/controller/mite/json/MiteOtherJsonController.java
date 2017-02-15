package com.mite8.controller.mite.json;

import com.mite8.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Time:  2016/12/2.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  首页+about数据接口
 */
@RestController
public class MiteOtherJsonController {

    private final Logger logger = Logger.getLogger(MiteOtherJsonController.class.getName());

    @Autowired
    private OtherService otherService;

    //首页
    @RequestMapping(value = "/json_XX")
    public JSONObject getGovJxGzDnIndexJson() throws Exception{
        return otherService.getIndex();
    }

    //大数据观
    @Autowired
    private DataTopicService dataTopicService;
    @RequestMapping(value = "/json_XX/data_topic")
    public JSONObject getDataTopicJson(@RequestParam(value="page", defaultValue="1") int page,
                                       @RequestParam(value="size", defaultValue="5") int size,
                                       @RequestParam(value="type", defaultValue="0") int type) throws Exception{
        return dataTopicService.dataTopicService(size, page, type);
    }

}
