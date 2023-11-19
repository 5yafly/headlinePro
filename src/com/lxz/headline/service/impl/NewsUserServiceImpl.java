package com.lxz.headline.service.impl;

import com.lxz.headline.dao.NewsUserDao;
import com.lxz.headline.dao.impl.NewsUserDaoImpl;
import com.lxz.headline.pojo.NewsUser;
import com.lxz.headline.service.NewsUserService;
import com.lxz.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao userDao = new NewsUserDaoImpl();
    @Override
    public NewsUser findByNewsUser(NewsUser newsUser) {
        newsUser.setUserPwd(MD5Util.MD5(newsUser.getUserPwd()));
        return userDao.findByNewsUser(newsUser);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return userDao.findByUid(userId);
    }

    @Override
    public NewsUser findByUername(String username) {
        return userDao.findByUername(username);
    }

    @Override
    public Integer registUser(NewsUser newsUser) {
        newsUser.setUserPwd(MD5Util.MD5(newsUser.getUserPwd()));
        return userDao.addNewsUser(newsUser);
    }
}
