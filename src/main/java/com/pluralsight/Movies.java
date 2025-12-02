package com.pluralsight;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;
import java.util.Scanner;

public class Movies {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/";

    private static final Scanner scan = new Scanner(System.in);

    private static Connection connection = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        loadConnection("sakila", args[0], args[1]);
        menuDisplay();

//
//        String query = "SELECT * FROM film f \n" +
//                "JOIN film_actor fa ON (fa.film_id = f.film_id) \n" +
//                "JOIN actor a ON (a.actor_id = fa.actor_id)\n" +
//                " WHERE a.first_name LIKE ? AND a.last_name LIKE ? ;";
    }
    public static void loadConnection(String database, String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url + database);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error when loading connection. Exiting application.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void menuDisplay() throws SQLException, ClassNotFoundException {
        int option = -1;

        while (option != 0) {
            System.out.println("What do you want to do?\n" +
                    "1) Search actor by last name\n" +
                    "2) Search Movies made by an actor\n" +
                    "0) Exit\n" +
                    "Select an option: " );

            option = scan.nextInt();
            scan.nextLine();


            switch (option) {}
        }
    }

    public static void displayActor() throws SQLException {
        String query = "SELECT * FROM actor WHERE last_name LIKE ? ";
        System.out.println("Enter Actor's Last name: ");
        String name = scan.next();

        try (PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    System.out.println("First Name: " + results.getString("first_name"));
                    System.out.println("Last Name: " + results.getString("last_name"));
                    System.out.println("--------------------------------------------------------");
                }

            }

        }
    }
}
