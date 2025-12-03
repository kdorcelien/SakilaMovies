package com.pluralsight;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("--Welcome to the Sakila DB Search Tool!--\n");

        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        if (username == null || password == null) {
            System.out.println("Database credentials not found in environment variables");
            System.out.println("Please set DB_USERNAME and DB_PASSWORD");
            System.exit(1);
        }


        DataManager dataManager = new DataManager("sakila", username, password);


        AppController.runProgram(dataManager);
    }
}
