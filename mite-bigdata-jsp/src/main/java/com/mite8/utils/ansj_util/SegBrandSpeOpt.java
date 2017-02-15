package com.mite8.utils.ansj_util;

import org.ansj.domain.Nature;
import org.ansj.domain.Result;
import org.ansj.domain.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Author: blogchong
 * Time:  2016/10/20.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  对于品牌的分词进行专门的处理
 */
public class SegBrandSpeOpt {

    public static Result segBrandSpeOpt(Result result, String nature) {

        List<Term> list = result.getTerms();

        Set<String> dicSepSet = null;

        if (LoadDynamicDictionary.dicSpeMap.get(nature) != null) {
            dicSepSet = LoadDynamicDictionary.dicSpeMap.get(nature);
        } else {
            dicSepSet = LoadDictionary.dicSpeMap.get(nature);
        }
        for (Term term: list) {
            String word = term.getName().toLowerCase();

            if (word.matches("[a-zA-Z]+")) {
                if (dicSepSet.contains(word)) {
                    term.setNature(new Nature(nature));
                }
            }
        }

        return result;
    }
}
