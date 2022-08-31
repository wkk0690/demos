package com.example.hbase;

import java.sql.*;
import java.util.Properties;

/**
 * @author OX574B4B
 * @create 2022/08/30
 * @desc
 */
public class PhoenixConnection {

    public static void main(String[] args) throws SQLException {
        //标准得jdbc连接
        String url = "jdbc:phoenix:node01,node02,node03:2181";
        Properties properties = new Properties();

        //获取连接
        Connection connection = DriverManager.getConnection(url, properties);

        PreparedStatement preparedStatement = connection.prepareStatement("select * from student");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
        }

        connection.close();
    }
}
