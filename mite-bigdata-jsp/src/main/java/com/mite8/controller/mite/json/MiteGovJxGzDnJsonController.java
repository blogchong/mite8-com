package com.mite8.controller.mite.json;

import com.mite8.service.GovJxGzDnService;
import com.mite8.service.TypeService;
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
 * Desc:  GOV - json数据接口
 */
@RestController
public class MiteGovJxGzDnJsonController {

    private final Logger logger = Logger.getLogger(MiteGovJxGzDnJsonController.class.getName());

    //舆情类型-jx_gz_dn
    @Autowired
    private TypeService typeService;
    @RequestMapping(value = "/json_XX/gov/addr_type")
    public JSONObject getGovAddrTypeJson(@RequestParam(value="a_id", defaultValue="111") int aId) throws Exception{
        return typeService.typeByAID(aId);
    }

    @Autowired
    private GovJxGzDnService govJxGzDnService;

    //问政舆情
    @RequestMapping(value = "/json_XX/gov/jx_gz_dn/politics")
    public JSONObject getGovJxGzDnPoliticsJson() throws Exception{
        return govJxGzDnService.govJxGzDnPolitics();
    }

    //财政
    @RequestMapping(value = "/json_XX/gov/jx_gz_dn/finance")
    public JSONObject getGovJxGzDnFinanceJson() throws Exception{
        return govJxGzDnService.govJxGzDnFinance();
    }

    //财政
    @RequestMapping(value = "/json_XX/gov/jx_gz_dn/hire")
    public JSONObject getGovJxGzDnResumeAndHireJson() throws Exception{
        return govJxGzDnService.govJxGzDnResumeAndHire();
    }

    //口碑
    @RequestMapping(value = "/json_XX/gov/jx_gz_dn/praise")
    public JSONObject getGovJxGzDnPraiseJson() throws Exception{
        return govJxGzDnService.govJxGzDnPraise();
    }

    //房产
    @RequestMapping(value = "/json_XX/gov/jx_gz_dn/house")
    public JSONObject getGovJxGzDnHouseJson() throws Exception{
        return govJxGzDnService.govJxGzDnHouse();
    }

    //教育
    @RequestMapping(value = "/json_XX/gov/jx_gz_dn/edu")
    public JSONObject getGovJxGzDnEduJson() throws Exception{
        return govJxGzDnService.govJxGzDnEdu();
    }
}
