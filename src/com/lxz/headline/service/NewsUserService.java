package com.lxz.headline.service;

import com.lxz.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 根据用户登录的信息找用户的方法
     * @param newsUser 用户输入的用户
     * @return  找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUsername(NewsUser newsUser);
}
