package com.coding.jdbc;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /**
         * 数据库驱动
         */
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_name", "root", "root");
        //创建sql对象
/*        String sql = "select * from t_seat where price = '23300' limit 20";
        Statement statement = connection.createStatement();*/
        String sql = "select * from t_seat where price=? limit 20";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "7500");

        //通过statement执行sql语句
//        ResultSet resultSet = statement.executeQuery(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("start_station"));
        }

        connection.close();
        preparedStatement.close();
        resultSet.close();
    }
}
