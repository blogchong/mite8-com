package com.mite8.controller;

import com.mite8.service.SegService;
import com.mite8.utils.DefineOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Created: 2016/10/14
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des:  分词服务
 */

@RestController
public class SegController {

    @Autowired
    SegService segService;

    private final Logger logger = Logger.getLogger(SegController.class.getName());

    //分词接口
    @ResponseBody
    @RequestMapping(value = "/mite8/tool/nlp_seg")
    public String SegWord(@RequestParam(value = "type", defaultValue = "simple") String type,  //simple简单模式，details详细模式
                            @RequestParam(value = "stop_flag", defaultValue = "false") String stopFlag, //是否启用启用词过滤true启用
                            @RequestParam(value = "rule_flag", defaultValue = "false") String ruleFlag,//是否启用规则过滤true启用
                            @RequestParam(value = "ambig_flag", defaultValue = "false") String ambigFlag,//是否启用歧义词表true启用,false不启用
                            @RequestParam(value = "synon_flag", defaultValue = "false") String synonRule,//是否启用同义词，true启用,false不启用
                            @RequestParam(value = "nature_rule", defaultValue = "no") String natureRule, //nature过滤器，default默认过滤器，specify_nature1,nature2，指定过滤器过滤
                            @RequestParam(value = "clean_flag", defaultValue = "false") String cleanFlag,//是否进行body预清理特殊字符,true为启用，false不启用
                            @RequestParam(value = "seg_dic", defaultValue = DefineOut.DEFAULT_DIC) String segDic, //分词字典标志，默认为see，具体为分词字典前缀名称
                            @RequestParam(value = "ambig_dic", defaultValue = DefineOut.DEFAULT_DIC) String ambigDic,//歧义词字典标志，同上，默认为see
                            @RequestParam(value = "stop_dic", defaultValue = DefineOut.DEFAULT_DIC) String stopDic,//停用词字典标志，同上，默认为see
                            @RequestParam(value = "synon_dic", defaultValue = DefineOut.DEFAULT_DIC) String synonDic,//同义词字典标志，同上，默认为see
                            @RequestBody String body) {
        return segService.segWord(type, stopFlag, body, ruleFlag, ambigFlag, synonRule,segDic,ambigDic,stopDic,synonDic,cleanFlag,natureRule);
    }

}

