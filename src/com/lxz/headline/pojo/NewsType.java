package com.lxz.headline.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class NewsType implements Serializable {

    private Integer tid;

    private String tname;

    public NewsType() {
    }

    public NewsType(Integer tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }
}
