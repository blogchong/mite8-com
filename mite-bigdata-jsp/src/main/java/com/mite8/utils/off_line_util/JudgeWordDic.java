package com.mite8.utils.off_line_util;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Author: blogchong
 * Time:  2016/10/9.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  针对于品牌词，梳理其单英文字的词
 */
public class JudgeWordDic {

//    public static void main(String[] args) {
//        String dicPath = "dic/seg_words/brand.dic";
//        String outPath = "C:\\Data\\桌面空间\\工作文档\\分词\\实例\\brand_merge_only_en.dic";
//        JudgeWordDic mergeWordDic = new JudgeWordDic();
//        mergeWordDic.mergeWordDic(dicPath, outPath);
//    }

    //返回一个list进行加载
    public void mergeWordDic(String srcDicPath, String outPutPath) {

        Set<String> set = new HashSet<String>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(srcDicPath).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String dicWord = scanner.nextLine().trim();
                if (dicWord.matches("[a-zA-Z]+")) {
                    set.add(dicWord.trim().toLowerCase());
                }
            }

            scanner.close();

            writeFile(set, outPutPath);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //存储到指定位置
    public static void writeFile(Set<String> set, String outPath) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(outPath)), "UTF-8"));

            int count = 0;

            for (String word: set) {
                bw.write(word);
                bw.newLine();
                count++;
            }
            bw.close();

            System.err.println("The num of merge words is :" + count);

        } catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }

}
