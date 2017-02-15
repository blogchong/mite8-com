package com.mite8.controller.mite.jsp;

import com.mite8.service.BigdataService;
import com.mite8.service.JDCommentsService;
import com.mite8.service.TypeService;
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
 * Author: blogchong
 * Time:  2016/10/24.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 行业洞察入口
 */

@Controller
public class MiteInsightController {

    //舆情类型
    @Autowired
    private TypeService typeService;
    @RequestMapping(value = "/insight_type")
    public String getInsightType(HttpServletRequest httpServletRequest,
                                 @RequestParam(value="a_id", defaultValue="10001") int aId,
                                 Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", typeService.typeByAID(aId));

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+ GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/gov/addr_type] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "insight_type";
    }

    @Autowired
    private BigdataService bigdataService;

    private final Logger logger = Logger.getLogger(MiteInsightController.class.getName());

    //大数据行业报告
    @RequestMapping(value = "/insight/big_data")
    public String bigData(HttpServletRequest httpServletRequest,
                          Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", bigdataService.bigData());

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+ GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/insight/big_data] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "big_data";
    }

    //JD-C行业报告
    @Autowired
    private JDCommentsService jdCommentsService;
    @RequestMapping(value = "/insight/jd_comments_wm")
    public String jdComments(HttpServletRequest httpServletRequest,
                          Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", jdCommentsService.jdComments());

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+ GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/insight/jd_comments_wm] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "jd_comments_wm";
    }
}
