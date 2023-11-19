package com.lxz.headline.dao;

import com.lxz.headline.pojo.NewsUser;

public interface NewsUserDao {
    /**
     * 根据用户信息在数据库查找用户的方法
     * @param newsUser  用户信息
     * @return  找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByNewsUser(NewsUser newsUser);

    /**
     * 根据用户id查找用户的方法
     * @param userId   用户id
     * @return  找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUid(Integer userId);

    /**
     * 根据用户名查找用户的方法
     * @param username 用户名
     * @return  找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUername(String username);

    /**
     * 添加用户信息的方法
     * @param newsUser 用户信息
     * @return  成功返回1，失败返回0
     */
    Integer addNewsUser(NewsUser newsUser);
}
