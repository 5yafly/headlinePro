package com.lxz.headline.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class NewsUser implements Serializable{

    private Integer uid;

    private String username;

    private String userPwd;

    private String nickName;

    public NewsUser() {
    }
    public NewsUser(Integer uid, String username, String userPwd, String nickName) {
        this.uid = uid;
        this.username = username;
        this.userPwd = userPwd;
        this.nickName = nickName;
    }
}
