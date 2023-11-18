package com.lxz.headline.pojo.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class HeadlineQueryVo implements Serializable {
    private String keyWords;

    private Integer type;

    private Integer pageNum;

    private Integer pageSize;

    public HeadlineQueryVo() {
    }

    public HeadlineQueryVo(String keyWords, Integer type, Integer pageNum, Integer pageSize) {
        this.keyWords = keyWords;
        this.type = type;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
