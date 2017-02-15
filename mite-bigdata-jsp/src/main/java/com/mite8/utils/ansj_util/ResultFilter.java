package com.mite8.utils.ansj_util;

import org.ansj.domain.Nature;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import java.util.*;

/**
 * Author: blogchong
 * Time:  2016/10/13.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 重构的过滤器，与原有的过滤器不同的是，把指定nature的词过滤出来。
 */
public class ResultFilter {

    private Set<String> natureFilter = new HashSet<String>();

    public void addNatureFilterByNature(String natureStr) {
        this.natureFilter.add(natureStr);
    }

    //合并词汇
    public static Result resultSetMeger(Result result) {
        Set<String> set = new HashSet<>();
        List<Term> listTerm = result.getTerms();
        for (Term term: listTerm){
            set.add(term.getName());
        }
        listTerm.clear();
        for (String word: set){
            listTerm.add(new Term(word,0,"flag",0));
        }
        result.setTerms(listTerm);
        return result;
    }

    //指定nature的过滤出来
    public Result resultFilterBySpecifyNature(Result result) {

        List<Term> list = result.getTerms();

        List<Term> listRet = new ArrayList<Term>();

        for (Term term: list) {
            if (natureFilter.size() > 0 && natureFilter.contains(term.natrue().natureStr)) {
                listRet.add(term);
            }
        }

        Result  resultRet = new Result(listRet);
        return resultRet;
    }

    //规则过滤
    public static Result resultFilterByRule(Result result) {

        List<Term> list = result.getTerms();

        List<Term> listRet = new ArrayList<Term>();

        for (Term term: list) {
            String word = term.getName().trim();
            String nature = term.getNatureStr();
            if (word.length() >= 2 && !word.matches("-?[0-9]+.*[0-9]*") && !word.matches("[a-zA-Z]]\\.") && nature != null) {
                listRet.add(term);
            }
        }

        Result  resultRet = new Result(listRet);
        return resultRet;
    }

    //规则过滤
    public static Result resultFilterBySynon(Result result, Map<String, String> synonMap, String nature) {

        List<Term> list = result.getTerms();

        List<Term> listRet = new ArrayList<Term>();

        for (Term term: list) {
            String word = term.getName().toLowerCase();
            if (synonMap.containsKey(word)) {
                term.setName(synonMap.get(word));
                term.setNature(new Nature(nature));
            }
            listRet.add(term);
        }

        Result  resultRet = new Result(listRet);
        return resultRet;
    }

}
