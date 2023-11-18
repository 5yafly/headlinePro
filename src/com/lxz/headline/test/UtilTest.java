package com.lxz.headline.test;

import com.lxz.headline.util.GsonUtil;
import com.lxz.headline.util.JDBCUtil;
import com.lxz.headline.util.MD5Util;
import lombok.Data;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UtilTest {
    @Data
    class PojoTest{
        private int id;
        private String name;
        public PojoTest(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "PojoTest{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    /**
     * 对GsonUtil工具类进行测试
     */
    @Test
    public void gsonUtilTest() {
        PojoTest pojo = new PojoTest(1,"lxz");
        List<PojoTest> lists = new ArrayList<>();
        lists.add(pojo);
        Map<String,PojoTest> map = new HashMap<>();
        map.put("pojo",pojo);
        //pojo -> String
        String json = GsonUtil.toJson(pojo);
        System.out.println("pojo -> String:"+json);
        //string -> pojo
        PojoTest pojo1 = GsonUtil.fromJson(json, PojoTest.class);
        System.out.println("string -> pojo:"+pojo1.toString());
        //list -> string
        String jsonList = GsonUtil.toJson(lists);
        System.out.println("list -> string:"+jsonList);
        //String -> list
        List<Object> list = GsonUtil.toList(jsonList);
        System.out.println("String -> list:"+list);
        //Map -> String
        String jsonMap = GsonUtil.toJson(map);
        System.out.println("Map -> String:"+jsonMap);
        //String -> Map
        Map<Object, Object> map1 = GsonUtil.toMap(jsonMap);
        System.out.println("String -> Map:"+map1);
    }

    /**
     * 对JDBCUtil工具类进行测试
     */
    @Test
    public void JdbcTest(){
        Connection connection = JDBCUtil.getConnection();
        String sql = "select count(1) from news_user;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long count = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                count = (Long) resultSet.getObject(1);
            }
            System.out.println(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (null != resultSet) {
                    resultSet.close();
                }
                if (null != preparedStatement) {
                    preparedStatement.close();
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        JDBCUtil.close();
    }


    @Test
    public void md5Test(){
        String oldPwd = "123456";
        String newPwd = MD5Util.MD5(oldPwd);
        System.out.println(newPwd);
    }
}
