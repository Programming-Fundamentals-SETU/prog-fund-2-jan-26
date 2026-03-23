package main;


import controllers.DayCareAPI;
import controllers.OwnerAPI;
import models.*;
import utils.ScannerInput;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    private Scanner scanner = new Scanner(System.in);
    private OwnerAPI ownerAPI= new OwnerAPI(new File("animals.xml"));
    //TODO:add fields
    public static void main(String[] args) {

        new Driver();

    }

    public Driver() {


        runMenu();
    }
    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------

    //TODO:add all code required to add Pets

    private int mainMenu() {

        System.out.println("""
                 -------Pet Day Care -------------
                |  1)                            |
                |  2) Owners CRUD MENU                              |
                |  3) Reports MENU               |
                |--------------------------------|
                |  4)                            |
                |  5)                            |  
                |  4)                            | 
                |--------------------------------|
                |  10) Save all                  |
                |  11) Load all                  |
                |--------------------------------|
                |  0) Exit                       |
                 --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }
    private void runMenu() {

        int option = mainMenu();

        while (option != 0) {

            switch (option) {

                case 2 ->runOwnersAPIMenu();
                case 3 ->runReportsMenu();


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

    private void runOwnersAPIMenu() {
        int option = ownersAPIMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addOwner();
                case 2 -> deleteOwner();
                case 3 -> System.out.println(ownerAPI.listOwners());
                case 4 -> updateOwner();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = ownersAPIMenu();
        }
    }

    private void updateOwner() {
        System.out.println(ownerAPI.listOwners());
        int index = ScannerInput.readNextInt("\n Press enter index to delete");
        Owner ow = ownerAPI.getOwnerByIndex(index);
        System.out.println("Current owner: " + ow);
        String name = ScannerInput.readNextLine("Enter updated name: ");
        String tele = ScannerInput.readNextLine("Enter updated telephone: ");
        ownerAPI.updateOwner(index, name, tele);

    }

    private void deleteOwner() {
        System.out.println(ownerAPI.listOwners());
        int index = ScannerInput.readNextInt("\n Press enter index to delete");
        Owner ow = ownerAPI.getOwnerByIndex(index);
        boolean result = ownerAPI.removeOwner(ow);
        if (result) {
            System.out.println("Sucessful removal \n" + ownerAPI.listOwners());
        }
    }

    private void addOwner() {
        int id = ScannerInput.readNextInt("\n Press enter id  to add owner");
        String name = ScannerInput.readNextLine("Enter owner name");
        String phone = ScannerInput.readNextLine("Enter phone number");

        Owner owner = new Owner(id, name, phone);
        boolean res = ownerAPI.addOwner(owner);
        if(res)
            System.out.println("Sucessfully added owner \n" + owner);

    }
//---------------------
    //  Pets Menu
    //---------------------


    private int ownersAPIMenu() {
        System.out.println(""" 
                 -----Owners CRUD Menu----- 
                | 1) Add a new Owner           |
                | 2) Delete a Owner        |
                | 3) List all  Owners      |
                | 4) Update Owner Information          |
                | 0) Return to main menu         |
                 ----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }



     public void runReportsMenu() {
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 2 -> runOwnerReportsMenu();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
    }

    private int reportsMenu() {
        System.out.println(""" 
                 ----------  Reports Menu  ---------------------
                | 1) Pet Reports                                 | 
                | 2) Owner Reports                                  |
                

                | 0) Return to main menu                            | 
                  ----------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }

    private void runOwnerReportsMenu() {
        int option = ownersReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(ownerAPI.listOwners());
                case 2 -> System.out.println(findOwners());

                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = ownersReportsMenu();
        }
    }

    private String findOwners() {
        return ownerAPI.listOwnersStartsWith(ScannerInput.readNextLine("Enter start of owner name:"));
    }

    private int ownersReportsMenu() {
        System.out.println(""" 
                 ---------- Owners Reports Menu  ---------------------
                | 1) List all Owners                                  | 
                | 2) List all Owners that starts with                                  |
                

                | 0) Return to main menu                            | 
                  ----------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }





    //------------------------------------
    // Private methods for CRUD on Pet
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
    //    This method uses the XStream component to deserialise the Pet object and their associated owners from
    //    an XML file into the DayCare array list.


    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the animal object and their associated owners to
    //    an XML file.

}
