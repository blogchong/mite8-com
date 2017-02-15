package com.mite8.jx.gz.dn.service.hire.utils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.jx.gz.dn.utils.DefineDn;
import net.sf.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Author: blogchong
 * Time:  2016/11/22.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 招聘详情页
 */
public class AnalysisDetail {

    private static final Logger logger = Logger.getLogger(AnalysisDetail.class.getName());

//    public static void main(String[] args){
//        analysisDetail("http://www.jxdn.ccoo.cn/post/zhaopin/3249760x.html", new JSONObject());
//    }

    //入参例子：http://www.jxdn.ccoo.cn/post/zhaopin/3318309x.html
    //http://www.jxdn.ccoo.cn/post/zhaopin/3319075x.html
    public static JSONObject analysisDetail(String url, JSONObject jsonObject) {

        try {
            CrawlDatum crawlDatum = new CrawlDatum(url).putMetaData("method", "GET");

            HttpRequest request = new HttpRequest(crawlDatum.getUrl());

            request.setMethod(crawlDatum.getMetaData("method"));
            String outputData = crawlDatum.getMetaData("outputData");
            if (outputData != null) {
                request.setOutputData(outputData.getBytes("utf-8"));
            }

            /*
            //通过下面方式可以设置Cookie、User-Agent等http请求头信息
            request.setCookie("xxxxxxxxxxxxxx");
            request.setUserAgent("WebCollector");
            request.addHeader("xxx", "xxxxxxxxx");
            */

            HttpResponse httpResponse = request.getResponse();
            Page page = new Page(crawlDatum, httpResponse);

            //职位
            String title = page.select("h1").get(0).text().trim();
            //企业名称
            String companyName = page.select("div[class=gongsimq]").text().split(" ")[0].split("　")[0];
            //发布时间
            String pTime = page.select("div[class=balefthead]").select("span[class=fltl]").text().split(" ")[0].split("：")[1];
            //关注人数
            int careNum = 0;
            Matcher matcher = DefineDn.getPatternHireCareNum.matcher(page.select("div[class=balefthead]").select("span[class=fltl]").text().split(" ")[1]);
            if (matcher.find()) {
                careNum = Integer.parseInt(matcher.group(1));
            }
            //解析公司规模、性质、行业
            String companyTmp = page.select("div[class=mqconx]").select("p").text();
            String[] companyTmps = companyTmp.split(" ");
            String companyScale = companyTmps[0].split("：")[1];
            String companyNature = companyTmps[1].split("：")[1];
            String companyIndustry = companyTmps[2].split("：")[1];
            //解析职位、人数、地点、联系人
            Elements elements1 = page.select("div[class=zpleftshow]").select("dl").select("dd");
            String position = "";
            String num = "若干";
            String address = "";
            String linkman = "";
            if (elements1.size() >=4) {
                position = elements1.get(0).text().trim();
                num = elements1.get(1).text().trim();
                address = elements1.get(2).text().trim();
                linkman = elements1.get(3).text().trim();
            }
            //解析月薪、学历、经验、电子邮箱
            String salary = "面议";
            String edu = "不限";
            String exp = "不限";
            String mail = "";
            Elements elements11 = page.select("div[class=zprightshow]").select("dl").select("dd");
            if (elements11.size() == 3) {
                salary = elements11.get(0).text().split("查看")[0];
                edu = elements11.get(1).text();
                exp = elements11.get(1).text();
                mail = elements11.get(2).text();
            } else if (elements11.size() == 4) {
                salary = elements11.get(0).text().split("查看")[0];
                edu = elements11.get(1).text();
                exp = elements11.get(2).text();
                mail = elements11.get(3).text();
            }

            //职位描述 - text
            String positionDesc = page.select("div[class=zwms]").text().split("联系我时请说明")[0];
            //公司介绍 - text
            String companyDesc = page.select("div[class=gsjs]").text();

            jsonObject.put("title",title);
            jsonObject.put("company_name",companyName);
            jsonObject.put("p_time",pTime);
            jsonObject.put("care_num",careNum);
            if(companyScale.equals("--")){
                companyScale = "未知";
            }
            jsonObject.put("company_scale",companyScale);
            if (companyNature.equals("--")){
                companyNature = "未知";
            }
            jsonObject.put("company_nature",companyNature);
            if (companyIndustry.equals("--")){
                companyIndustry = "其他行业";
            }
            jsonObject.put("company_industry",companyIndustry);
            if(position.equals("--")){
                position = "其他";
            }
            jsonObject.put("position",position);
            jsonObject.put("num",num);
            jsonObject.put("address",address);
            jsonObject.put("linkman",linkman);
            jsonObject.put("salary",salary);

            if (edu.equals("高中及中专")) {
                edu = "高中/中专";
            }
            jsonObject.put("edu",edu);
            jsonObject.put("exp",exp);
            jsonObject.put("mail",mail);
            jsonObject.put("position_desc",positionDesc);
            jsonObject.put("company_desc",companyDesc);

            //解析高级属性
            int salaryCount = 0;
            if (salary.contains("元以下")){
                int tmp = Integer.parseInt(salary.trim().split("元以下")[0]);
                salaryCount = (int)(tmp - tmp*0.2);
            } else if (salary.contains("以上")){
                int tmp = Integer.parseInt(salary.trim().split("以上")[0]);
                salaryCount = (int)(tmp + 0.2*tmp);
            } else if (salary.contains("元")){
                String[] tmp = salary.trim().split("元")[0].split("-");
                salaryCount = (Integer.parseInt(tmp[0]) + Integer.parseInt(tmp[1]))/2;
            }

            int numCount = 3;
            if (num.contains("人")){
                numCount = Integer.parseInt(num.split("人")[0]);
            }

            jsonObject.put("salary_count", salaryCount);
            jsonObject.put("num_count", numCount);

        } catch (Exception e) {
            logger.info("[ERROR] - analysisDetail: url["+ url +"] - Detail: " + e);
        }
        return jsonObject;
    }

}
