package com.lxz.headline.dao;

import com.lxz.headline.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * dao层的基类
 */
public class BaseDao {

    /**
     * 对数据库进行增删改的操作，返回的是受影响行数
     * @param sql
     * @param params
     * @return
     */
    public int executeUpdate(String sql,Object...params){
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (null != params && params.length != 0) {
                for (int i = 1; i <= params.length; i++) {
                    preparedStatement.setObject(i,params[i-1]);
                }
            }
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (null != preparedStatement) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        JDBCUtil.close();
        return rows;
    }

    /**
     * 对数据进行查的操作，返回的是单个结果
     * @param clazz
     * @param sql
     * @param param
     * @return
     * @param <T>
     */
    public <T> T executeQuery(Class<T> clazz,String sql,Object...param){
        T t = null;
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (null != param && param.length != 0) {
                for (int i = 1; i <= param.length; i++) {
                    preparedStatement.setObject(i,param[i-1]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                t = (T) resultSet.getObject(1);
            }
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        JDBCUtil.close();
        return t;
    }

    public <T> List<T> executeQuerys(Class<T> clazz, String sql, Object... param) {
        List<T> lists= new ArrayList<>();
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (null != param && param.length != 0) {
                for (int i = 1; i <= param.length; i++) {
                    preparedStatement.setObject(i,param[i-1]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                T t = clazz.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String columnLabel = metaData.getColumnLabel(i);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,value);
                }
                lists.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != resultSet) {
                    resultSet.close();
                }
                if (null != preparedStatement) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        JDBCUtil.close();
        return lists;
    }
}
