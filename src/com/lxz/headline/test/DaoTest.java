package com.lxz.headline.test;

import com.lxz.headline.dao.BaseDao;
import com.lxz.headline.pojo.NewsUser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class DaoTest {
    private static BaseDao baseDao;
    @BeforeClass
    public static void beforeTest(){
        baseDao = new BaseDao();
    }
    //测试BaseDao
    @Test
    public void baseDaoTest() {
        //1.测试增删改
        String sql = "insert into `news_user` values(?,?,?,?);";
        Object[] params = {7,"lxz","123","lzx"};
        int i = baseDao.executeUpdate(sql, params);
        System.out.println("1.测试增删改" + i);
        System.out.println("---------------");
        //2.测试查找单个结果
        String sql1 = "select count(1) from `news_user`;";
        Long l = baseDao.executeQuery(Long.class, sql1);
        System.out.println("测试查找单个结果" + l);
        System.out.println("---------------");
        //3.测试查询结果集
        String sql2 = "select uid,username,user_pwd userPwd,nick_name nickName from `news_user`;";
        List<NewsUser> newsUsers = baseDao.executeQuerys(NewsUser.class, sql2);
        System.out.println("测试查询结果集" +newsUsers);

    }
}
