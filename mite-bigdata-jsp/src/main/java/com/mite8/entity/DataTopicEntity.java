package com.mite8.entity;

/**
 * Author: blogchong
 * Created: 2016/10/24
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des: data_topic表示类
 */

public class DataTopicEntity {

    private String p_time;
    private String t_title;
    private String t_desc;
    private String t_url;
    private int t_type;

    public int getT_type() {
        return t_type;
    }

    public void setT_type(int t_type) {
        this.t_type = t_type;
    }

    public String getP_time() {
        return p_time;
    }

    public void setP_time(String p_time) {
        this.p_time = p_time;
    }

    public String getT_title() {
        return t_title;
    }

    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    public String getT_desc() {
        return t_desc;
    }

    public void setT_desc(String t_desc) {
        this.t_desc = t_desc;
    }

    public String getT_url() {
        return t_url;
    }

    public void setT_url(String t_url) {
        this.t_url = t_url;
    }
}
