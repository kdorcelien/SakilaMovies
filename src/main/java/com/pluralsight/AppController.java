package com.pluralsight;

public class AppController {
    private static DataManager dataManager;



    public static void handleDisplayActors(){}

    public static void handleDisplayMovies(){}




    private static void exitProgram() {
        UserInterface.clearScreen();
        System.out.println("Goodbye! 👋");
        System.out.println("\nThanks for visiting the Sakila DB Search Tool!! \n");

        dataManager.close();
        UserInterface.closeScanner();
        System.exit(0);
    }
}
