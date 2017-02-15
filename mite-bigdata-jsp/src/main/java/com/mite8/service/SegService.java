package com.mite8.service;

import com.mite8.utils.CleanStr;
import com.mite8.utils.ansj_util.LoadDictionary;
import com.mite8.utils.ansj_util.ResultFilter;
import com.mite8.utils.ansj_util.SegBrandSpeOpt;
import com.mite8.utils.ansj_util.WordNatureFilter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.ansj.domain.Result;
import org.ansj.recognition.impl.FilterRecognition;
import org.ansj.splitWord.analysis.SeeDicAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.springframework.stereotype.Service;

/**
 * Author: blogchong
 * Created: 2016/7/14
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des:  分词服务service
 */

@Service
public class SegService {

    public String segWord(String type, String stopFlag, String body, String ruleFlag, String ambigFlag, String synonFlag,
                          String segDic, String ambigDic, String stopDic, String synonDic, String cleanFlag, String natureRule) {

            String resultStr = "";

            Forest seeForest = LoadDictionary.dicSegMap.get(segDic);

            //判断是否需要进行预处理清理body
            if(cleanFlag.equals("true")) {
                body = CleanStr.cleanStr(body);
            }

            Result result = null;
            if (ambigFlag.equals("true")) {
                Forest ambigForest = LoadDictionary.dicAmbigMap.get(ambigDic);
                result = SeeDicAnalysis.parse(seeForest, ambigForest, body);
            } else {
                result = SeeDicAnalysis.parse(seeForest, body);
            }

            //对于brand进行专门处理
            if(segDic.equals("brand")) {
                result = SegBrandSpeOpt.segBrandSpeOpt(result, "brand");
            }

            //是否使用默认的停用过滤
            if (stopFlag.equals("true")) {
                FilterRecognition filter = new FilterRecognition();
                filter.insertStopWords(LoadDictionary.dicStopMap.get(stopDic));
                result = result.recognition(filter);
            }

            //是否使用nature过滤规则
            if(natureRule.equals("default")) {
                //使用默认的nature过滤器
                FilterRecognition filter = new FilterRecognition();
                filter = WordNatureFilter.wordNatureFilter(filter);
                result = result.recognition(filter);
            } else if (natureRule.contains("specify_")) {
                //过滤指定的nature
                String[] pars = natureRule.split("_");
                if (pars.length == 2) {
                    String[] natureFilters = pars[1].split(",");
                    ResultFilter resultFilter = new ResultFilter();
                    for (String nature: natureFilters) {
                        resultFilter.addNatureFilterByNature(nature);
                    }
                    result = resultFilter.resultFilterBySpecifyNature(result);
                }
            }

            //是否执行规则过滤
            if (ruleFlag.equals("true")) {
                result = ResultFilter.resultFilterByRule(result);
            }

            //是否进行同义词合并
            if (synonFlag.equals("true")) {
                result = ResultFilter.resultFilterBySynon(result, LoadDictionary.dicSynonMap.get(synonDic), synonDic);
            }

            //返回形式: 简洁模式 还是详情模式
            if (type.equals("simple")) {
                for (int i = 0; i < result.size(); i++) {
                    String word = result.get(i).getName();
                    if (resultStr.equals("")) {
                        resultStr = word;
                    } else {
                        resultStr = resultStr + " " + word;
                    }
                }
            } else if (type.equals("details")) {
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < result.size(); i++) {
                    String word = result.get(i).getName();
                    String nature = result.get(i).getNatureStr();
                    JSONObject jsonObjectTmp = new JSONObject();
                    jsonObjectTmp.put("word", word);
                    jsonObjectTmp.put("nature", nature);
                    jsonArray.add(jsonObjectTmp);
                }
                jsonObject.put("size", result.size());
                jsonObject.put("word_list", jsonArray);
                resultStr = jsonObject.toString();
            }


        return resultStr;
    }

}
