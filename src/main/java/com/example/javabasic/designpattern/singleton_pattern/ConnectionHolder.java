package com.example.javabasic.designpattern.singleton_pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author：Cheng.
 * @date：Created in 17:14 2019/12/9
 */
public class ConnectionHolder {

    public static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();

    public static Connection getConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        if(null == connection){
           connection = DriverManager.getConnection("DB_URL");
           connectionHolder.set(connection);
        }
        return connection;
    }
}
