package com.mite8.jx.gz.dn.service.resume.utils;

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
 * Desc: 解析简历详情页
 */
public class AnalysisDetail {

    private static final Logger logger = Logger.getLogger(AnalysisDetail.class.getName());


//    public static void main(String[] args){
//        analysisDetail("http://www.jxdn.ccoo.cn/post/qiuzhi/1024925x.html", new JSONObject());
//    }

    //入参例子：http://www.jxdn.ccoo.cn/post/qiuzhi/1028664x.html
    //http://www.jxdn.ccoo.cn/post/qiuzhi/989215x.html
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

            Elements elements = page.select("tr");
            if (elements.size() >= 5){
                String origin = elements.get(0).select("td[style=width:38%]").text().trim();
                String edu = elements.get(0).select("td[style=width:28%]").text().trim();
                String school = elements.get(1).select("td[style=width:38%]").text().trim();
                String major = elements.get(1).select("td[style=width:28%]").text().trim();
                String wishType = elements.get(2).select("td[style=width:38%]").text().trim();
                String address = elements.get(2).select("td[style=width:28%]").text().trim();
                String exp = elements.get(3).select("td[style=width:28%]").text().trim();
                String pay = elements.get(4).select("td[colspan=4]").text().trim();

                String phone = "";
                String mail = "";
                if (elements.size() > 5) {
                    phone = elements.get(5).select("b[class=phone]").text().trim();
                    Elements elements1 = elements.get(5).select("td[style=width:25%;]");
                    if (elements1.size() == 2) {
                        mail = elements1.get(1).text().trim();
                    }
                }

                Elements elementsPoint = page.select("div[class=feature_cont]").select("span");
                String point = "";
                for (Element element: elementsPoint) {
                    if (point.equals("")) {
                        point = element.text();
                    } else {
                        point = point + "," + element.text();
                    }
                }

                Elements elementsInt = page.select("div[class=jianjie]");
                String introduction = "";
                for (Element element: elementsInt) {
                    if (introduction.equals("")) {
                        introduction = element.text().trim();
                    } else {
                        introduction = introduction + "||" + element.text().trim();
                    }
                }


                String eduCont = "";
                Elements elementsEdu = page.select("div[class=edu_cont]").select("ul").select("li");
                for (Element element: elementsEdu) {
                    if(eduCont.equals("")) {
                        eduCont = element.text();
                    } else {
                        eduCont = eduCont + "," + element.text();
                    }
                }
                String photo = "";
                Elements elementsPhoto = page.select("ul[class=ad-thumb-list]").select("li");
                for (Element element: elementsPhoto) {
                    Matcher matcher = DefineDn.patternUrl.matcher(element.toString());
                    String photoTmp = "";
                    if (matcher.find()) {
                        photoTmp = matcher.group(1);
                    }
                    if (photo.equals("") && !photoTmp.equals("")) {
                        photo = photoTmp;
                    } else if ( !photo.equals("") && !photoTmp.equals("")) {
                        photo = photo + "," + photoTmp;
                    }
                }
                jsonObject.put("origin",origin);
                jsonObject.put("edu", edu);
                jsonObject.put("school", school);
                jsonObject.put("major", major);
                jsonObject.put("wish_type", wishType);
                jsonObject.put("address", address);
                jsonObject.put("exp", exp);
                jsonObject.put("pay", pay);
                jsonObject.put("point", point);
                jsonObject.put("introduction", introduction);
                jsonObject.put("edu_cont", eduCont);
                jsonObject.put("photo", photo);
                jsonObject.put("phone", phone);
                jsonObject.put("mail", mail);

                //解析高级属性
                int payCount = 0;
                if (pay.contains("元以下")){
                    int tmp = Integer.parseInt(pay.trim().split("元以下")[0]);
                    payCount = (int)(tmp - tmp*0.2);
                } else if (pay.contains("以上")){
                    int tmp = Integer.parseInt(pay.trim().split("元以上")[0]);
                    payCount = (int)(tmp + 0.2*tmp);
                } else if (pay.contains("元")){
                    String[] tmp = pay.trim().split("元")[0].split("-");
                    payCount = (Integer.parseInt(tmp[0]) + Integer.parseInt(tmp[1]))/2;
                }
                jsonObject.put("pay_count", payCount);
            }

        } catch (Exception e) {
            logger.info("[ERROR] - analysisDetail: url["+ url +"] - Detail: " + e);
        }
        return jsonObject;
    }

}
