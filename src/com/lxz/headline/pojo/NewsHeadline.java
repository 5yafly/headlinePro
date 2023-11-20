package com.lxz.headline.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NewsHeadline implements Serializable {

    private Integer hid;

    private String title;

    private String article;

    private Integer type;

    private Integer publisher;

    private Integer pageViews;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    public NewsHeadline() {
    }

    public NewsHeadline(Integer hid, String title, String article, Integer type, Integer publisher, Integer pageView, Date createTime, Date updateTime, Integer isDeleted) {
        this.hid = hid;
        this.title = title;
        this.article = article;
        this.type = type;
        this.publisher = publisher;
        this.pageViews = pageView;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;
    }
}
