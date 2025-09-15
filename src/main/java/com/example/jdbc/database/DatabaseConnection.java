package com.example.jdbc.database;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

   public static Connection getConnection() {
       try {
           return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                   "postgres",
                   "1996");
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

}
