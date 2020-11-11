package ru.andreev.gradebook.db;

import java.sql.*;

public final class Database {

    private static final String URL = " ";
    private static final String USER = " ";
    private static final String PASSWORD = " ";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
