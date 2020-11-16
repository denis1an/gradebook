package ru.andreev.gradebook.db;

import java.sql.*;

public final class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/gradebook";
    private static final String USER = "user_gradebook";
    private static final String PASSWORD = "user_gradebook";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
}
