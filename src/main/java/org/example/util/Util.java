package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    private static final String url = "jdbc:postgresql://localhost:5432/postgress";
    private static final String username = "postgres";
    private static final String password = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    url,
                    username,
                    password);
            System.out.println("Connected to database!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
