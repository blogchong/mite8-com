package com.mite8.jx.gz.dn.service.resume.utils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.jx.gz.dn.utils.DefineDn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Author: blogchong
 * Time:  2016/11/22.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 解析简历列表
 */
public class AnalysisList {

    private static final Logger logger = Logger.getLogger(AnalysisList.class.getName());

    private static String urlQz = "http://www.jxdn.ccoo.cn";

//    public static void main(String[] args){
//        analysisList("http://www.jxdn.ccoo.cn/post/qiuzhi/pn1/");
//    }

    //入参例子：http://www.jxdn.ccoo.cn/post/qiuzhi/pn1/
    public static JSONArray analysisList(String url) {

        JSONArray jsonArray = new JSONArray();

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

            Elements elements = page.select("div[class=list_jianli]").get(0).select("div[class=jl-bt]");
            Set<String> set = new HashSet<>();
            for(Element element: elements) {

                //求职意向
                String intention = element.select("a[class=title]").text().trim();
                //详细链接
                String fromUrlTmp = element.select("div[class=tipsbox]").select("a").toString();
                Matcher matcher = DefineDn.patternUrl.matcher(fromUrlTmp);
                String fromUrl = "";
                if (matcher.find()) {
                    fromUrl = urlQz + matcher.group(1);
                }
                //解析名字以及性别，年龄
                String NameXNTmp = element.select("h3").text();
                Matcher matcherNXN = DefineDn.patternResumeNameXN.matcher(NameXNTmp);
                String name = "";
                String gender = "";
                String age = "";
                if (matcherNXN.find()) {
                    name = matcherNXN.group(1);
                    gender = matcherNXN.group(2);
                    age = matcherNXN.group(3);
                }
                //发布时间
                String pTimeTmp = element.select("div[class=tipsbox]").select("span[class=tip]").text();
                String pTime = "";
                if (pTimeTmp.split("发布时间：").length == 2) {
                    pTime = pTimeTmp.split("发布时间：")[1];
                }

                if (!set.contains(name)) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", name);
                    jsonObject.put("age", age);
                    jsonObject.put("gender", gender);
                    jsonObject.put("from_url", fromUrl);
                    jsonObject.put("list_url", url);
                    jsonObject.put("p_time", pTime);
                    jsonObject.put("intention", intention);

                    jsonArray.add(jsonObject);

                    set.add(name);
                }

            }

        } catch (Exception e) {
            logger.info("[ERROR] - analysisList: url["+ url +"] - Detail: " + e);
        }
        return jsonArray;
    }

}
