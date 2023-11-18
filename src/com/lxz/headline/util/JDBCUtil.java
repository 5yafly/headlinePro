package com.lxz.headline.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.lxz.headline.common.Result;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 通过使用druid连接池获取数据库连接
 *  1.创建连接池对象
 *  2.初始化连接池对象
 *  3.
 */
public class JDBCUtil{
    private static DataSource dataSource;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConnection(){
        Connection connection = tl.get();
        if (null == connection) {
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            tl.set(connection);
        }

        return connection;
    }

    /**
     * 释放连接对象资源
     */
    public static void close(){
        Connection connection = tl.get();
        if (null != connection) {
            tl.remove();
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
