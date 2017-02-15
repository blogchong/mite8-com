package com.mite8.utils.off_line_util;

import org.ansj.library.DATDictionary;
import org.ansj.util.MyStaticValue;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.Value;
import org.nlpcn.commons.lang.tire.library.Library;
import org.nlpcn.commons.lang.util.StringUtil;

import java.io.File;
import java.util.Scanner;

/**
 * Author: blogchong
 * Time:  2016/10/14.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 离线自定义字典加载
 */
public class UserMapForest {

    public Forest loadLibrary(Forest forest, String path, String nature) throws Exception{

        ClassLoader classLoader = UserMapForest.class.getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        int count = 0;

        try (Scanner scanner = new Scanner(file)) {

            String temp;
            String[] strs;
            Value value;

            while (scanner.hasNextLine()) {
                //获取到resource中的每行
                temp = scanner.nextLine();
                if (StringUtil.isNotBlank(temp)) {
                    temp = StringUtil.trim(temp);
                    strs = temp.split("\t");
                    strs[0] = strs[0].toLowerCase();
                    // 如何核心辞典存在那么就放弃
                    if (MyStaticValue.isSkipUserDefine && DATDictionary.getId(strs[0]) > 0) {
                        continue;
                    }
                    if (strs.length != 3) {
                        value = new Value(strs[0], nature, "1000");
                    } else {
                        value = new Value(strs[0], strs[1], strs[2]);
                    }
                    Library.insertWord(forest, value);
                    count++;
                }
            }
        }

        return forest;
    }
}
