package com.mite8.controller.mite.jsp;

import com.mite8.service.DataTopicService;
import com.mite8.service.OtherService;
import com.mite8.utils.GetAddrHostUtils;
import com.mite8.utils.TransferTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.logging.Logger;


/**
 * Author: blogc
 * Time:  2016/10/24.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: about+首页
 */

@Controller
public class MiteOtherController {

    @Autowired
    private OtherService otherService;

    private final Logger logger = Logger.getLogger(MiteOtherController.class.getName());

    //首页
    @RequestMapping(value="/")
    public String initIndex(HttpServletRequest httpServletRequest,Model model) throws Exception{

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", otherService.getIndex());

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/index] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "index";
    }

    //关于我们
    @RequestMapping(value = "/about")
    public String getAbout(HttpServletRequest httpServletRequest,Model model) {


        long beginTime = TransferTime.dateToLong(new Date());

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/about] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "about";
    }

    //原创文章
    @Autowired
    private DataTopicService dataTopicService;
    @RequestMapping(value = "/data_topic")
    public String getDataTopic(HttpServletRequest httpServletRequest,
                               @RequestParam(value="page", defaultValue="1") int page,
                               @RequestParam(value="size", defaultValue="7") int size,
                               @RequestParam(value="type", defaultValue="0") int type,
                               Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", dataTopicService.dataTopicService(size,page,type));

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+ GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/data_topic] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "data_topic";
    }

}
