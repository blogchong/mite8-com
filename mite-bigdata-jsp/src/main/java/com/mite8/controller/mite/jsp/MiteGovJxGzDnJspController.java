package com.mite8.controller.mite.jsp;

import com.mite8.service.GovJxGzDnService;
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
 * Desc: jx-gz-dn 政务舆情页面入口
 */

@Controller
public class MiteGovJxGzDnJspController {

    @Autowired
    private GovJxGzDnService govJxGzDnService;

    private final Logger logger = Logger.getLogger(MiteGovJxGzDnJspController.class.getName());

    //舆情类型
    @Autowired
    private TypeService typeService;
    @RequestMapping(value = "/gov/addr_type")
    public String getGovAddrType(HttpServletRequest httpServletRequest,
                                 @RequestParam(value="a_id", defaultValue="111") int aId,
                                 Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", typeService.typeByAID(aId));

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+ GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/gov/addr_type] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "gov_addr_type";
    }

    //gov_jx_gz_dn总菜单
    @RequestMapping(value = "/gov/jx_gz_dn")
    public String govJxGzDn(HttpServletRequest httpServletRequest,
                                    Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

//        model.addAttribute("data", govJxGzDnService.govJxGzDnPolitics());
        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/gov/jx_gz_dn/politics] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "gov_jx_gz_dn";
    }


    @RequestMapping(value = "/gov/jx_gz_dn/politics")
    public String govJxGzDnPolitics(HttpServletRequest httpServletRequest,
                                    Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", govJxGzDnService.govJxGzDnPolitics());
        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/gov/jx_gz_dn/politics] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "gov_jx_gz_dn_politics";
    }

    @RequestMapping(value = "/gov/jx_gz_dn/finance")
    public String govJxGzDnFinance(HttpServletRequest httpServletRequest,Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", govJxGzDnService.govJxGzDnFinance());

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/gov/jx_gz_dn/finance] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "gov_jx_gz_dn_finance";
    }

    @RequestMapping(value = "/gov/jx_gz_dn/hire")
    public String govJxGzDnHire(HttpServletRequest httpServletRequest,Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", govJxGzDnService.govJxGzDnResumeAndHire());

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/gov/jx_gz_dn/hire] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "gov_jx_gz_dn_hire";
    }

    @RequestMapping(value = "/gov/jx_gz_dn/praise")
    public String govJxGzDnPraise(HttpServletRequest httpServletRequest,Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", govJxGzDnService.govJxGzDnPraise());

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/gov/jx_gz_dn/praise] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "gov_jx_gz_dn_praise";
    }

    @RequestMapping(value = "/gov/jx_gz_dn/house")
    public String govJxGzDnHouse(HttpServletRequest httpServletRequest,Model model) {

        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", govJxGzDnService.govJxGzDnHouse());

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/gov/jx_gz_dn/house] SERVICE_TIME["+(endTime-beginTime)+"ms]");

        return "gov_jx_gz_dn_house";
    }

    @RequestMapping(value = "/gov/jx_gz_dn/edu")
    public String govJxGzDnEdu(HttpServletRequest httpServletRequest,Model model) {
        long beginTime = TransferTime.dateToLong(new Date());

        model.addAttribute("data", govJxGzDnService.govJxGzDnEdu());

        long endTime = TransferTime.dateToLong(new Date());

        logger.info("IP["+ GetAddrHostUtils.getAddrHost(httpServletRequest)+"] MODULE[/gov/jx_gz_dn/edu] SERVICE_TIME["+(endTime-beginTime)+"ms]");
        return "gov_jx_gz_dn_edu";
    }

}
