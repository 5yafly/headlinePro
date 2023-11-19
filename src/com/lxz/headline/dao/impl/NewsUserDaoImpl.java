package com.lxz.headline.dao.impl;

import com.lxz.headline.dao.BaseDao;
import com.lxz.headline.dao.NewsUserDao;
import com.lxz.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao{
    @Override
    public NewsUser findByUsername(NewsUser newsUser) {
        String sql = """
                select
                    uid,
                    username,
                    user_pwd userPwd,
                    nick_name nickName
                from news_user
                where username = ?
                """;
        List<NewsUser> newsUsers = executeQuerys(NewsUser.class, sql, newsUser.getUsername());
        return newsUsers != null && newsUsers.size() != 0 ? newsUsers.get(0) : null;
    }
}
