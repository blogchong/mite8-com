package com.mite8.jx.gz.dn.entity;

/**
 * Author: blogchong
 * Time:  2016/11/28.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  情感分析字典表示类
 */
public class EmotionEntity {

    String word;
    String type;
    int strength;
    int polar;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getPolar() {
        return polar;
    }

    public void setPolar(int polar) {
        this.polar = polar;
    }
}
