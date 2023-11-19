package com.lxz.headline.service;

import com.lxz.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 根据用户登录的信息找用户的方法
     * @param newsUser 用户输入的用户
     * @return  找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByNewsUser(NewsUser newsUser);

    /**
     * 根据用户登录的id查找用户的方法
     * @param userId  用户id
     * @return  找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUid(Integer userId);

    /**
     * 根据账户名称查找用户的方法
     * @param username 用户名
     * @return  找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUername(String username);

    /**
     * 根据注册信息添加用户的方法
     * @param newsUser 用户信息NewsUser对象
     * @return  成功返回1，不成功返回0
     */
    Integer registUser(NewsUser newsUser);
}
