package com.pluralsight;

import com.pluralsight.models.*;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMainMenu() {
        System.out.println("What do you want to do?\n" +
                "\t1) Search actor by last name\n" +
                "\t2) Search Movies made by an actor\n" +
                "\t0) Exit\n");
    }

    public static void displayActor(List<Actor> actors) {
        if (actors.isEmpty()) {
            System.out.println("No Actors Found");
            return;
        }
        for (Actor actor : actors) {
            System.out.println("Actor ID: " + actor.getActorId());
            System.out.println("First Name: " + actor.getFirstName());
            System.out.println("Last Name: " + actor.getLastName());
            System.out.println("--------------------------------------------------------");
        }
    }

    public static void displayMovies(List<Film> movies) {
        if (movies.isEmpty()) {
            System.out.println("No Movies Found");
            return;
        }
        for (Film movie : movies) {
            System.out.println("Film ID: " + movie.getFilmId());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Description: " + movie.getDescription());
            System.out.println("Release Year: " + movie.getReleaseYear());
            System.out.println("Length: " + movie.getLength());
            System.out.println("--------------------------------------------------------");
        }
    }

    public static int getUserOption(String prompt) {
        System.out.print(prompt);

        boolean notChosen = true;
        int option = -1;

        do {
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option != -1) notChosen = false;

            } catch (Exception e) {
                System.out.print("Invalid Type Entered. " + prompt);
                scanner.nextLine();
            }
        } while (notChosen);

        return option;
    }

    public static String getUserString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static void waitForEnter() {
        System.out.print("\n...Press Enter to Continue");
        scanner.nextLine();
    }

    public static void clearScreen() {
        for (int i = 0; i < 60; i++) {
            System.out.println();
        }
    }

    public static void waitAndContinue() {
        waitForEnter();
        clearScreen();
    }

    public static void closeScanner() {
        scanner.close();
    }
}
