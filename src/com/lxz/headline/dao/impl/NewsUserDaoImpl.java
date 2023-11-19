package com.lxz.headline.dao.impl;

import com.lxz.headline.dao.BaseDao;
import com.lxz.headline.dao.NewsUserDao;
import com.lxz.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao{
    @Override
    public NewsUser findByNewsUser(NewsUser newsUser) {
        String sql = """
                select
                    uid,
                    username,
                    user_pwd userPwd,
                    nick_name nickName
                from news_user
                where username = ? and user_pwd = ?
                """;
        List<NewsUser> newsUsers = executeQuerys(NewsUser.class, sql, newsUser.getUsername(),newsUser.getUserPwd());
        return newsUsers != null && newsUsers.size() != 0 ? newsUsers.get(0) : null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = """
                select
                    uid,
                    username,
                    user_pwd userPwd,
                    nick_name nickName
                from news_user
                where uid = ?
                """;
        List<NewsUser> newsUsers = executeQuerys(NewsUser.class, sql, userId);
        return newsUsers != null && newsUsers.size() != 0 ? newsUsers.get(0) : null;
    }

    @Override
    public NewsUser findByUername(String username) {
        String sql = """
               select
                    uid,
                    username,
                    user_pwd userPwd,
                    nick_name nickName
                from news_user
                where username = ?
                """;
        List<NewsUser> newsUsers = executeQuerys(NewsUser.class, sql, username);
        return newsUsers != null && newsUsers.size() != 0 ? newsUsers.get(0) : null;
    }

    @Override
    public Integer addNewsUser(NewsUser newsUser) {
        String sql = """
                insert into news_user(username,user_pwd,nick_name) values(?,?,?)
                """;
        return executeUpdate(sql,newsUser.getUsername(),newsUser.getUserPwd(),newsUser.getNickName());
    }
}
