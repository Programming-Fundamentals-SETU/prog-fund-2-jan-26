package main;


import controllers.PetsDayCareAPI;
import controllers.OwnerAPI;
import models.*;
import utils.ScannerInput;

import java.io.File;
import java.util.Scanner;

public class Driver {
    private Scanner scanner = new Scanner(System.in);
    private PetsDayCareAPI petsDaycareAPI = new PetsDayCareAPI("LeBarks", 50,new File("pets.xml") );
    private OwnerAPI ownerAPI= new OwnerAPI(new File("owners.xml"));
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
                 -------Pet Day Care -------------
                |  1) Pets CRUD MENU             |
                |  2) Owners CRUD MENU           |
                |  3) Reports MENU               |
                |--------------------------------|
                |  4) Search Pets                |
                |  5) Search Owners              |  
                |  4) Sort Pets                  | 
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
                case 1 ->runPetsAPIMenu();
                case 2 ->runOwnersAPIMenu();
                case 3 ->runPetReportsMenu();

                case 4 ->searchMenu();
                case 5 ->searchMenu();
                case 6 ->findDogMenu();

                case 7 ->calculateWeekly();
                case 10 -> {
                    saveOwners();
                    savePets();
                }
                case 11 -> {
                    loadPets();
                    loadOwners();
                }
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
                case 4 -> System.out.println("todo");
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = ownersAPIMenu();
        }
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
        int id = ScannerInput.readNextInt("\n Enter id:  ");
        String name = ScannerInput.readNextLine("Enter owner name: ");
        String phone = ScannerInput.readNextLine("Enter phone number: ");

        Owner owner = new Owner(id, name, phone);
        boolean res = ownerAPI.addOwner(owner);
        if(res)
            System.out.println("Sucessfully added owner \n" + owner);

    }

//---------------------
    //  App Store Menu
    //---------------------

    private int petsAPIMenu() {
        System.out.println(""" 
                 -----Pets CRUD Menu----- 
                | 1) Add a new Pet           |
                | 2) Delete a Pet        |
                | 3) List all  Pets      |
                | 4) Update Pet Information          |
                | 0) Return to main menu         |
                 ----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }
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

    private void runPetsAPIMenu() {
        int option = petsAPIMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addPet();
                case 2 -> deletePet();
                case 3 -> System.out.println(petsDaycareAPI.listAllPets());
                case 4 -> updateOwner();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = petsAPIMenu();
        }
    }

    private void deletePet() {
        int id = ScannerInput.readNextInt("Please enter index number to delete: ");

        if (petsDaycareAPI.isValidPetIndex(id)) {
            Pet t = petsDaycareAPI.removePet(id);
            if (t != null)
                System.out.println("Sucessful delete : " + t);
            else System.out.println("No Pet was removed from the list");
        }

    }

      private void addPet() {
        int pType = ScannerInput.readNextInt("""
                Which type of Pet do you wish to add? 
                1) Dog
                2) Cat 
                3) Parrot""");



        switch (pType) {
            case 1 -> {
          //       public Dog(String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered, String breed, boolean dangerousBreed) {

                    String name = ScannerInput.readNextLine("Please enter dog's name: ");
                    int age = ScannerInput.readNextInt("Please enter dog's age: ");
                    String ownerName = ScannerInput.readNextLine("Please enter dog owner's name: ");
                    Owner owner = ownerAPI.getOwnerByName(ownerName);
                    int id = ScannerInput.readNextInt("Please enter dog's id: ");
                    char sex = ScannerInput.readNextChar("Enter the dog's sex ('M' for male, 'F' for female): ");
                    char vacc = ScannerInput.readNextChar("Is the dog's vaccinated? (y/n): ");
                    boolean vaccinated = (vacc == 'y'?  true : false);
                    double weight = ScannerInput.readNextDouble("Please enter dog's weight: ");
                    char neut = ScannerInput.readNextChar("Is the dog neutered? (y/n): ");
                    boolean neutered = (neut == 'y'?  true : false);
                    String breed = ScannerInput.readNextLine("Please enter dog's breed: ");
                    char dang = ScannerInput.readNextChar("Is the dog's dangerous? (y/n): ");
                    boolean dangerous = (dang == 'y'?  true : false);
                    petsDaycareAPI.addPet(new Dog(name, age, owner, id, sex, vaccinated, weight, neutered, breed, dangerous));

            }
            case 2 -> {
                        //cat
                   //TBD
                    }



            default -> throw new IllegalStateException("Unexpected value: " + pType);
        }}




    public void runReportsMenu() {
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runPetReportsMenu();
                case 2 -> runOwnerReportsMenu();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
    }



    private void runPetReportsMenu() {
      int option = petsReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(petsDaycareAPI.listAllPets());
                case 2 -> System.out.println(petsDaycareAPI.listAllPets());
                case 3 -> System.out.println(petsDaycareAPI.listAllPets());
                case 4 -> System.out.println(petsDaycareAPI.listAllDangerousDogs());
                //case 5 ->
               // case 6 -> /
               // case 7 -> /
                //case 8 ->

                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = petsReportsMenu();
        }
    }



    private int petsReportsMenu() {
        System.out.println(""" 
                 ---------- Pet Reports Menu  ---------------------
                | 1) List all Pets                                  | 
                | 2) List all Dogs                                  |
                | 3) List all Cats                                  |
                | 4) List all Parrots                               |
                | 5) List all Dangerous Dogs                        |
                | 6) List all Indoor Cats                           |
                | 7) List all dogs older than an age                |
                | 8) List all cats by favourite toy                 |
                | 9) List all parrots by number of words            |
                | 10) List all animals that are neutered            |
                | 11) Produce Weekly Income Report                  |

                | 0) Return to main menu                            | 
                  ----------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
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

    private int reportsMenu() {

    
    return 0;
    }

    private void dogMenu() {
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
    private void saveOwners() {
        try {
            ownerAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + ownerAPI.fileName());
        }
    }

    //load all the products into the store from a file on the hard disk
    private void loadOwners() {
        try {
            ownerAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + ownerAPI.fileName());
        }
    }
    private void savePets() {
        try {
            petsDaycareAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + petsDaycareAPI.fileName());
        }
    }

    //load all the products into the store from a file on the hard disk
    private void loadPets() {
        try {
            petsDaycareAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + petsDaycareAPI.fileName());
        }
    }


    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the Dog object and their associated owners from
    //    an XML file into the DayCare array list.


    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the dog object and their associated owners to
    //    an XML file.

}
