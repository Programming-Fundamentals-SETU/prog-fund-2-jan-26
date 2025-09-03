package main;

import controllers.DayCare;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    Scanner scanner = new Scanner(System.in);
    DayCare daycare = new DayCare("LeBarks", 50);
    public static void main(String[] args) {

        new Driver();

    }

    public Driver() {
        // Sample Data

        runMenu();
    }
    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------


    private int mainMenu() {
        System.out.println("""
                 -------------Doggie Day Care------
                |  1) Dog - Management MENU        |
                |  2) Reports MENU                 |
                |----------------------------------|
                |  3) Search                       |
                     :               |
                |----------------------------------|
                |  6) Find a Dog                   |
                |  7) Find an Owner                |
                |  8) Calculate Weekly Income      |
                |----------------------------------|
                |  20) Save all                    |
                |  21) Load all                    |
                |----------------------------------|
                |  0) Exit                         |
                 ----------------------------------""");
        int option = scanner.nextInt();
        return option;
    }

    private void runMenu() {

        int option = mainMenu();

        while (option != 0) {

            switch (option) {
        case 2 ->dogMenu();

                case 3 ->reportsMenu();
                case 4 ->searchMenu();
                case 5 ->findDogMenu();

                case 7 ->calculateWeekly();
                default -> System.out.println("Invalid option entered: " + option);
            }

            // Pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress enter key to continue...");
            scanner.nextLine(); // Consume leftover newline
            scanner.nextLine(); // Second read is required - bug in Scanner class.

            // Display the main menu again
            option = mainMenu();
        }

        // The user chose option 0, so exit the program
        System.out.println("Exiting...bye");
        System.exit(0);
    }

    private void calculateWeekly() {
    }
    private void addDog() {
    }
    private void updateDog() {
    }
    private void deleteDog() {
    }

    private void findDog() {
    }
    private void findOwner() {
    }
    private void load() {
    }
    private void save() {

    }


    private void findDogMenu() {
    }

    private void searchMenu() {
    }

    private void reportsMenu() {
    }

    private void dogMenu() {
    }


    //------------------------------------
    // Private methods for CRUD on Song
    //------------------------------------


    //-----------------------------------------------------------------
    //  Private methods for Search facility
    //-----------------------------------------------------------------


    //-----------------------------
    //  Private methods for Reports
    // ----------------------------


    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------


    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the Dog object and their associated owners from
    //    an XML file into the DayCare array list.


    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the dog object and their associated owners to
    //    an XML file.

}