package com.example.blog_website.Repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DBConnection {
    private static final  String URL="jdbc:mysql://localhost:3306/";
    private static final  String USERNAME="root";
    private static final  String PASSWORD="2004";

    Connection connection;
    DBConnection() throws SQLException
    {
        connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        System.out.println("Database connection established");
    }

    public Connection getConnection(){
        return connection;
    }

}
