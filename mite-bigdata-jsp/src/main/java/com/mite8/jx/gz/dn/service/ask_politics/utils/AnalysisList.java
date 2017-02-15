package com.mite8.jx.gz.dn.service.ask_politics.utils;

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
 * Desc: 解析问政列表
 */
public class AnalysisList {

    private static final Logger logger = Logger.getLogger(AnalysisList.class.getName());


//    public static void main(String[] args){
//        analysisList("http://www.dingnan.gov.cn/wlwz/lxxd/index.html");
//    }

    //入参例子：http://www.dingnan.gov.cn/wlwz/lxxd/index.html
    public static JSONArray analysisList(String url) {

        JSONArray jsonArray = new JSONArray();

        try {
            CrawlDatum crawlDatum = new CrawlDatum(url).putMetaData("method", "POST");

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

            Elements elements = page.select("div[class=hmwz_connr]").select("tr");
            Set<String> set = new HashSet<>();
            for(Element element: elements) {
                String id = element.select("td[width=102]").text();
                if (!set.contains(id) && id != null && !id.equals("")){
                    JSONObject jsonObject = new JSONObject();
                    //获取链接
                    String urlDetailTmp = element.select("td[width=318]").toString();
                    Matcher matcher = DefineDn.patternUrl.matcher(urlDetailTmp);
                    String urlDetailTmp2 = "";
                    String urlDetail = "";
                    if(matcher.find()) {
                        urlDetailTmp2 = matcher.group(1);
                    }
                    String[] urlDetailTmp3 = urlDetailTmp2.split("\\.\\.");
                    if (urlDetailTmp3.length == 2) {
                        urlDetail = "http://www.dingnan.gov.cn/wlwz" + urlDetailTmp3[1];
                    }
                    //标题
                    String title = element.select("td[width=318]").text();
                    //获取类型
                    String type = element.select("td[width=82]").text();
                    //获取时间
                    String pTime = element.select("td[width=99]").text();
                    //默认处理状态
                    String optStatusTmp = element.select("td[width=75]").toString();
                    String optStatus = "未处理";
                    if (optStatusTmp.contains("printstate(2)")){
                        optStatus = "已经处理";
                    } else if (optStatusTmp.contains("printstate(1)")) {
                        optStatus = "正在处理";
                    }

                    jsonObject.put("id", id);
                    jsonObject.put("title", title);
                    jsonObject.put("type",type);
                    String[] pTimeTmp = pTime.split("/");
                    if (pTimeTmp.length == 3) {
                        pTime = "20" + pTimeTmp[0] + "-" + pTimeTmp[1] + "-" + pTimeTmp[2];
                    }
                    jsonObject.put("p_time", pTime);
                    jsonObject.put("status", optStatus);
                    jsonObject.put("from_url", urlDetail);
                    jsonArray.add(jsonObject);
                    set.add(id);
                }
            }

        } catch (Exception e) {
            logger.info("[ERROR] - analysisList: url["+ url +"] - Detail: " + e);
        }
        return jsonArray;
    }

}
