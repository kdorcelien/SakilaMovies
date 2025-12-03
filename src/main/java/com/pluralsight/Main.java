package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        System.out.println("--Welcome to the Sakila DB Search Tool!--\n");

        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
    }
}
