package com.pluralsight;

import com.pluralsight.models.Actor;
import com.pluralsight.models.Film;

import java.sql.SQLException;
import java.util.List;

public class AppController {
    private static DataManager dataManager;

    public static void runProgram(DataManager dm) throws SQLException {
        dataManager = dm;

        do {
            UserInterface.displayMainMenu();

            switch(UserInterface.getUserOption("Enter a number option: ")) {
                case 1 ->{
                   String lastname = UserInterface.getUserString("Enter actor's last name: ").toUpperCase();
                    handleDisplayActors(lastname);
                }
                case 2 -> {
                    String firstname = UserInterface.getUserString("Enter actor's first name: ").trim().toUpperCase();
                    String lastname = UserInterface.getUserString("Enter actor's last name: ").trim().toUpperCase();
                    handleDisplayMovies(firstname,lastname);
                }
                case 0 -> exitProgram();
                default -> {
                    UserInterface.clearScreen();
                    System.out.println("Invalid option entered, please try again!\n");
                }
            }

        } while (true);
    }



    public static void handleDisplayActors(String lastname){
        List<Actor> actors = dataManager.getActor(lastname);
        UserInterface.displayActor(actors);
        UserInterface.waitAndContinue();
    }

    public static void handleDisplayMovies(String firstname, String lastname) throws SQLException {
        List<Film> movies = dataManager.getFilmByActor(firstname, lastname);
        UserInterface.displayMovies(movies);
        UserInterface.waitAndContinue();
    }




    private static void exitProgram() {
        UserInterface.clearScreen();
        System.out.println("Goodbye! 👋");
        System.out.println("\nThanks for visiting the Sakila DB Search Tool!! \n");

        dataManager.close();
        UserInterface.closeScanner();
        System.exit(0);
    }
}
