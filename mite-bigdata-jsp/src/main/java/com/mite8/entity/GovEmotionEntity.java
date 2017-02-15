package com.mite8.entity;

/**
 * Author: blogchong
 * Created: 2016/10/24
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des:  GOV-Politics-Emotion表示类
 */

public class GovEmotionEntity {

    private String words;
    private int praise;
    private int anger;
    private int fear;
    private int hate;
    private int sad;
    private int shock;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getAnger() {
        return anger;
    }

    public void setAnger(int anger) {
        this.anger = anger;
    }

    public int getFear() {
        return fear;
    }

    public void setFear(int fear) {
        this.fear = fear;
    }

    public int getHate() {
        return hate;
    }

    public void setHate(int hate) {
        this.hate = hate;
    }

    public int getSad() {
        return sad;
    }

    public void setSad(int sad) {
        this.sad = sad;
    }

    public int getShock() {
        return shock;
    }

    public void setShock(int shock) {
        this.shock = shock;
    }
}
