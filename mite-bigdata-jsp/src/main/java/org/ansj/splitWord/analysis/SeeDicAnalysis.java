package org.ansj.splitWord.analysis;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.domain.TermNature;
import org.ansj.domain.TermNatures;
import org.ansj.recognition.arrimpl.AsianPersonRecognition;
import org.ansj.recognition.arrimpl.ForeignPersonRecognition;
import org.ansj.recognition.arrimpl.NumRecognition;
import org.ansj.recognition.arrimpl.UserDefineRecognition;
import org.ansj.splitWord.Analysis;
import org.ansj.splitWord.impl.GetWordsImpl;
import org.ansj.util.Graph;
import org.ansj.util.MyStaticValue;
import org.ansj.util.NameFix;
import org.ansj.util.TermUtil;
import org.nlpcn.commons.lang.tire.GetWord;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.util.WordAlert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.ansj.library.DATDictionary.IN_SYSTEM;
import static org.ansj.library.DATDictionary.status;

/**
 * Author: blogchong
 * Time:  2016/10/13.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  重构dic分词器，支持按业务动态的更换字典。
 */
public class SeeDicAnalysis extends Analysis {

    private Forest ambiguityForest = null;
    protected Forest[] forests = null;

    public Result parseStr(String temp) {
        return new Result(analysisStr(temp));
    }

    /**
     * 一整句话分词,用户设置的歧异优先
     *
     * @param temp
     * @return
     */
    private List<Term> analysisStr(String temp) {
        Graph gp = new Graph(temp);
        int startOffe = 0;

        if (this.ambiguityForest != null) {
            GetWord gw = new GetWord(this.ambiguityForest, gp.chars);
            String[] params = null;
            while ((gw.getFrontWords()) != null) {
                if (gw.offe > startOffe) {
                    analysis(gp, startOffe, gw.offe);
                }
                params = gw.getParams();
                startOffe = gw.offe;
                for (int i = 0; i < params.length; i += 2) {
                    gp.addTerm(new Term(params[i], startOffe, new TermNatures(new TermNature(params[i + 1], 1))));
                    startOffe += params[i].length();
                }
            }
        }
        if (startOffe < gp.chars.length - 1) {
            analysis(gp, startOffe, gp.chars.length);
        }
        List<Term> result = this.getResult(gp);

        return result;
    }

    private void analysis(Graph gp, int startOffe, int endOffe) {
        int start = 0;
        int end = 0;
        char[] chars = gp.chars;

        String str = null;
        char c = 0;
        for (int i = startOffe; i < endOffe; i++) {
            switch (status(chars[i])) {
                case 0:
                    if (Character.isHighSurrogate(chars[i]) && (i + 1) < endOffe && Character.isLowSurrogate(chars[i + 1])) {
                        str = new String(Arrays.copyOfRange(chars, i, i + 2));
                        gp.addTerm(new Term(str, i, TermNatures.NULL));
                        i++;
                    } else {
                        gp.addTerm(new Term(String.valueOf(chars[i]), i, TermNatures.NULL));
                    }
                    break;
                case 4:
                    start = i;
                    end = 1;
                    while (++i < endOffe && status(chars[i]) == 4) {
                        end++;
                    }
                    str = WordAlert.alertEnglish(chars, start, end);
                    gp.addTerm(new Term(str, start, TermNatures.EN));
                    i--;
                    break;
                case 5:
                    start = i;
                    end = 1;
                    while (++i < endOffe && status(chars[i]) == 5) {
                        end++;
                    }
                    str = WordAlert.alertNumber(chars, start, end);
                    gp.addTerm(new Term(str, start, TermNatures.M));
                    i--;
                    break;
                default:
                    start = i;
                    end = i;
                    c = chars[start];
                    while (IN_SYSTEM[c] > 0) {
                        end++;
                        if (++i >= endOffe)
                            break;
                        c = chars[i];
                    }

                    if (start == end) {
                        gp.addTerm(new Term(String.valueOf(c), i, TermNatures.NULL));
                        continue;
                    }

                    gwi.setChars(chars, start, end);
                    while ((str = gwi.allWords()) != null) {
                        gp.addTerm(new Term(str, gwi.offe, gwi.getItem()));
                    }

                    /**
                     * 如果未分出词.以未知字符加入到gp中
                     */
                    if (IN_SYSTEM[c] > 0 || status(c) > 3 || Character.isHighSurrogate(chars[i])) {
                        i -= 1;
                    } else {
                        gp.addTerm(new Term(String.valueOf(c), i, TermNatures.NULL));
                    }

                    break;
            }
        }
    }

    /**
     * 分词的类
     */
    private GetWordsImpl gwi = new GetWordsImpl();

    @Override
    protected List<Term> getResult(final Graph graph) {

        Merger merger = new Merger() {
            @Override
            public List<Term> merger() {

                // 用户自定义词典的识别
                userDefineRecognition(graph, forests);

                graph.walkPath();

                // 用户自定义词典的识别
                userDefineRecognition(graph, forests);

                // 数字发现
                if (MyStaticValue.isNumRecognition && graph.hasNum) {
                    new NumRecognition().recognition(graph.terms);
                }

                // 姓名识别
                if (graph.hasPerson && MyStaticValue.isNameRecognition) {
                    // 亚洲人名识别
                    new AsianPersonRecognition().recognition(graph.terms);
                    graph.walkPathByScore();
                    NameFix.nameAmbiguity(graph.terms);
                    // 外国人名识别
                    new ForeignPersonRecognition().recognition(graph.terms);
                    graph.walkPathByScore();
                }

                return getResult();
            }

            private void userDefineRecognition(final Graph graph, Forest... forests) {
                new UserDefineRecognition(TermUtil.InsertTermType.REPLACE, forests).recognition(graph.terms);
                graph.rmLittlePath();
                graph.walkPathByScore();
                graph.rmLittlePath();
            }

            private List<Term> getResult() {

                List<Term> result = new ArrayList<Term>();
                int length = graph.terms.length - 1;
                for (int i = 0; i < length; i++) {
                    if (graph.terms[i] != null) {
                        result.add(graph.terms[i]);
                    }
                }
                setRealName(graph, result);

                return result;
            }
        };
        return merger.merger();
    }

    public SeeDicAnalysis(Forest forest) {
        this.forests = new Forest[] { forest };
    }

    public SeeDicAnalysis(Forest forest, Forest ambiguityForest) {
        this.forests = new Forest[] { forest };
        this.ambiguityForest = ambiguityForest;
    }

    public static Result parse(Forest forest, String str) {
        return new SeeDicAnalysis(forest).parseStr(str);
    }

    public static Result parse(Forest forest, Forest ambiguityForest, String str) {
        return new SeeDicAnalysis(forest, ambiguityForest).parseStr(str);
    }
}
