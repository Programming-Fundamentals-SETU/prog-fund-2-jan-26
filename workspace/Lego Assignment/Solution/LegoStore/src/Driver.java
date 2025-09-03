import controllers.LegoSetAPI;
import models.InstructionBooklet;
import models.LegoSet;
import utils.MinimumAgeUtility;
import utils.ScannerInput;
import utils.ThemeUtility;

public class Driver {

    private LegoSetAPI legoSetAPI = new LegoSetAPI();

    public static void main(String[] args) {
        new Driver();
    }

    //TODO Refer to the tutors instructions for building this class.  You are free to deviate in any way
    //     from the Driver menu that is in the tutors instructions, once you have these included:
    //       - CRUD on LegoSet
    //       - CRUD on Instruction Booklets
    //       - Search facility (for LegoSets and Booklets)
    //       - Reports
    //       - Persistence
    // Note:  This is the ONLY class that can have System.out.print, System.out.println and Scanner reads in it.

    public Driver() {
        runMenu();
    }

    private int mainMenu() {

        int option = ScannerInput.readNextInt("""
                 ------------------------------------------------------------------
                 |                          LEGO SET APP                          |
                 ------------------------------------------------------------------
                 | Lego Set MENU                                                  |
                 |   1)  Add a Lego Set                                           |
                 |   2)  List all Lego Sets                                       |
                 |   3)  Update a Lego Set                                        |
                 |   4)  Delete a Lego Set                                        |
                 |   5)  Set stock status of Lego Set                             |
                 |   6)  Find a specific Lego Set (by code)                       |
                 |   7)  Search for all Lego Sets (by name)                       |
                 ------------------------------------------------------------------
                 | Instruction Booklet MENU                                       |
                 |   8)  Add a Booklet to a Lego Set                              |
                 |   9)  List all Booklets                                        |
                 |   10) Update a Booklet on a Lego Set                           |
                 |   11) Delete a Booklet from a Lego Set                         |
                 |   12) Search for all booklets (by file name )                  |
                 ------------------------------------------------------------------
                 | REPORT MENU                                                    |
                 |   13) Print Overall Stock Report                               |
                 |   14) Print All Lego Sets by chosen theme                      |
                 |   15) Print All Lego Sets at or above a minimum age            |
                 |   16)     <report of own design>                               |
                 |   17)     <report of own design>                               |
                 |   18)     <report of own design>                               |
                 |   19)     <report of own design>                               |
                ------------------------------------------------------------------
                 | SETTINGS MENU                                                  |
                 |   20) Save                                                     |
                 |   21) Load                                                     |
                 |   0)  Exit                                                     |
                 ------------------------------------------------------------------
                 ==>> """);
        return option;
    }

    private void runMenu() {
        int option = mainMenu();

        while (option != 0) {

            switch (option) {
                // Lego Set Menu
                case 1 ->  addLegoSet();
                case 2 ->  viewLegoSetsMenu();
                case 3 ->  updateLegoSets();
                case 4 ->  deleteLegoSets();
                case 5 ->  setStockStatusforLegoSets();
                case 6 ->  findLegoSetByCode();
                case 7 ->  searchLegoSetsByName();
                // Instructional Booklet Menu
                case 8 ->  addBookletToLegoSet();
                case 9 ->  printAllBooklets();
                case 10 -> updateBookletInLegoSet();
                case 11 -> deleteBookletFromLegoSet();
                case 12 -> searchBookletsByFileName();
                // Reports Menu
                case 13 -> printStockReport();
                case 14 -> printLegoSetsBySelectedTheme();
                case 15 -> printLegoSetsByMinAge();
                case 16 -> System.out.println("14 - Design your own menu option in here");
                case 17 -> System.out.println("15 - Design your own menu option in here");
                case 18 -> System.out.println("14 - Design your own menu option in here");
                case 19 -> System.out.println("14 - Design your own menu option in here");
                // Settings Menu
                case 20 -> save();
                case 21 -> load();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting...bye");
        System.exit(0);
    }

    //------------------------------------
    // LEGO SET MENU
    //------------------------------------

    //gather the note data from the user and create a new product object.
    private void addLegoSet() {
        System.out.println("Enter the following lego set details:");
        String name = ScannerInput.readNextLine("\tLego Set Name: ");
        int code = ScannerInput.readNextInt("\tCode (10000-99999): ");
        double cost = ScannerInput.readNextDouble("\tCost (> 0): ");
        int pieceCount = ScannerInput.readNextInt("\tPiece Count (1-2000): ");
        String theme = readValidTheme();
        int minimumAge = readValidAge();

        boolean isAdded = legoSetAPI.addLegoSet(new LegoSet(name, code, cost, pieceCount, theme, minimumAge));

        if (isAdded) {
            System.out.println("Added Successfully");
        } else {
            System.out.println("Add Failed");
        }
    }

    private void viewLegoSetsMenu() {
        if (legoSetAPI.numberOfLegoSets() > 0) {
            int option = ScannerInput.readNextInt("""
                    --------------------------------
                    |   1) View ALL Lego Sets      |
                    |   2) View In Stock Sets      |
                    |   3) View Out Of Stock Sets  |
                    --------------------------------
                    ==>> """);

            switch (option) {
                case 1 -> printAllLegoSets();
                case 2 -> printInStockLegoSets();
                case 3 -> printOutOfStockLegoSets();
                default -> System.out.println("Invalid option entered: " + option);
            }
        } else {
            System.out.println("Option Invalid - No lego sets stored");
        }
    }

    private void printAllLegoSets() {
        if (legoSetAPI.numberOfLegoSets() > 0) {
            System.out.println(legoSetAPI.numberOfLegoSets() + " lego sets: ");
        }
        System.out.println(legoSetAPI.listAllLegoSets());
    }

    private void printInStockLegoSets() {
        if (legoSetAPI.numberOfLegoSetsInStock() > 0) {
            System.out.println(legoSetAPI.numberOfLegoSetsInStock() + " lego sets IN STOCK: ");
        }
        System.out.println(legoSetAPI.listLegoSetsInStock());
    }

    private void printOutOfStockLegoSets() {
        if (legoSetAPI.numberOfLegoSetsOutOfStock() > 0) {
            System.out.println(legoSetAPI.numberOfLegoSetsOutOfStock() + " lego sets OUT OF STOCK: ");
        }
        System.out.println(legoSetAPI.listLegoSetsOutOfStock());
    }

    private void updateLegoSets() {
        printAllLegoSets();
        if (legoSetAPI.numberOfLegoSets() > 0) {
            //only ask the user to choose the note if lego sets exist
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the lego set to update: ");
            if (legoSetAPI.isValidIndex(indexToUpdate)) {
                System.out.println("Enter the following lego set details:");
                String name = ScannerInput.readNextLine("\tLego Set Name: ");
                int code = ScannerInput.readNextInt("\tCode (10000-99999): ");
                double cost = ScannerInput.readNextDouble("\tCost (> 0): ");
                int pieceCount = ScannerInput.readNextInt("\tPiece Count (1-2000): ");
                String theme = readValidTheme();
                int minimumAge = readValidAge();

                //pass the index of the note and the new note details to NoteAPI for updating and check for success.
                if (legoSetAPI.updateLegoSet(indexToUpdate, name, code, cost, pieceCount, theme, minimumAge)) {
                    System.out.println("Update Successful");
                } else {
                    System.out.println("Update Failed");
                }
            } else {
                System.out.println("There are is lego sets for this index number");
            }
        }
    }

    private void deleteLegoSets() {
        printAllLegoSets();
        if (legoSetAPI.numberOfLegoSets() > 0) {
            //only ask the user to choose the lego set to delete if sets exist
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the lego set to delete: ");
            //pass the index of the lego set to the API for deleting and check for success.
            LegoSet legoSetToDelete = legoSetAPI.deleteLegoSet(indexToDelete);
            if (legoSetToDelete != null) {
                System.out.println("Delete Successful! Deleted lego set: " + legoSetToDelete.getName());
            } else {
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void setStockStatusforLegoSets() {
        if (legoSetAPI.numberOfLegoSets() > 0) {
            int option = ScannerInput.readNextInt("""
                    ------------------------------------------
                    |   1) Lego Set - update to In Stock     |
                    |   2) Lego Set - update to Out of Stock |
                    ------------------------------------------
                    ==>> """);

            switch (option) {

                case 1 -> {
                    //Ask user to select an out of stock lego set
                    LegoSet legoSet = askUserToSelectOutOfStockLegoSet();
                    if (legoSet != null) {
                        //set it to IN STOCK
                        legoSet.setInStock(true);
                        System.out.println("Lego Set is now set to IN STOCK");
                    }
                }

                case 2 -> {
                    //Ask user to select an in stock lego set
                    LegoSet legoSet = askUserToSelectInStockLegoSet();
                    if (legoSet != null) {
                        //set it to OUT OF STOCK
                        legoSet.setInStock(false);
                        System.out.println("Lego Set is now set to OUT OF STOCK");
                    }
                }

                default -> System.out.println("Invalid option entered: " + option);
            }
        } else {
            System.out.println("Option Invalid - No lego sets stored");
        }
    }

    private void findLegoSetByCode() {
        if (legoSetAPI.numberOfLegoSets() > 0) {
            int searchCode = ScannerInput.readNextInt("Enter the full code to search by: ");
            System.out.println(legoSetAPI.findLegoSetByCode(searchCode));
        } else {
            System.out.println("Find not valid - No lego sets stored");
        }
    }

    private void searchLegoSetsByName() {
        if (legoSetAPI.numberOfLegoSets() > 0) {
            String searchString = ScannerInput.readNextLine("Enter the name to search by: ");
            System.out.println(legoSetAPI.searchLegoSetsByName(searchString));
        } else {
            System.out.println("Search not valid - No lego sets stored");
        }
    }

    //------------------------------------
    //  INFORMATION BOOKLETS MENU
    //------------------------------------
    private void addBookletToLegoSet() {
        LegoSet legoSet = askUserToSelectLegoSet();
        if (legoSet != null) {
            int numberOfPages = ScannerInput.readNextInt("\t Number of Pages: ");
            String fileName = ScannerInput.readNextLine("\t File Name: ");
            if (legoSet.addInstructionBooklet(new InstructionBooklet(numberOfPages, fileName)))
                System.out.println("Add Successful!");
            else
                System.out.println("Add NOT Successful");
        }
    }

    private void printAllBooklets() {
        System.out.println(legoSetAPI.listAllInstructionBooklets());
    }

    private void updateBookletInLegoSet() {
        LegoSet legoSet = askUserToSelectLegoSet();
        if (legoSet != null) {
            if (legoSet.numberOfInstructionBooklets() > 0) {
                int index = ScannerInput.readNextInt("Enter the index of the booklet: ");
                if (legoSet.isValidIndex(index)) {
                    String newFileName = ScannerInput.readNextLine("Enter a new file name: ");
                    int numberOfPages = ScannerInput.readNextInt("Enter a new page count: ");
                    if (legoSet.updateInstructionBooklet(index, newFileName, numberOfPages)){
                        System.out.println("Booklet details updated");
                    } else {
                        System.out.println("Booklet details NOT updated");
                    }
                } else {
                    System.out.println("Invalid Booklet Index");
                }
            } else {
                System.out.println("No booklets for this lego set");
            }
        }
    }

    private void deleteBookletFromLegoSet() {
        LegoSet legoSet = askUserToSelectLegoSet();
        if (legoSet != null) {
            if (legoSet.numberOfInstructionBooklets() > 0) {
                if (legoSet.deleteInstructionBooklet(ScannerInput.readNextInt("Enter the index of the booklet: ")) != null) {
                    System.out.println("Delete Successful!");
                } else {
                    System.out.println("Invalid Booklet Index");
                }
            } else {
                System.out.println("No booklets for this lego set");
            }
        }
    }

    private void searchBookletsByFileName() {
        if (legoSetAPI.totalNumberOfInstructionBooklets() > 0) {
            String searchString = ScannerInput.readNextLine("Enter the file name to search by: ");
            System.out.println(legoSetAPI.searchInstructionBookletsByFileName(searchString));
        } else {
            System.out.println("Search not valid - No booklets stored");
        }
    }

    //------------------------------------
    //DISPLAYING / REPORTING OF LEGO SETS
    //------------------------------------


    private void printStockReport() {
        String divider = " ------------------------------------------------------------------";
        if (legoSetAPI.numberOfLegoSets() > 0) {
            System.out.println(divider);
            System.out.println("   Lego Sets in System: " + legoSetAPI.numberOfLegoSets()
                    + "\t\t In Stock: " + legoSetAPI.numberOfLegoSetsInStock()
                    + ", Out of Stock: " + legoSetAPI.numberOfLegoSetsOutOfStock());
            System.out.println(divider);
            for (String theme : ThemeUtility.getThemes()) {
                System.out.println(theme + " theme has "
                        + legoSetAPI.numberOfLegoSetsByTheme(theme) + " sets overall.\n"
                        + legoSetAPI.listStockStatusBySpecificTheme(theme));
                System.out.println(divider);
            }
        } else {
            System.out.println("No lego sets stored");
        }
    }


    private void printLegoSetsBySelectedTheme() {
        if (legoSetAPI.numberOfLegoSets() > 0) {
            String theme = readValidTheme();
            System.out.println(legoSetAPI.listLegoSetsBySpecificTheme(theme));
        } else {
            System.out.println("Option Invalid - no lego sets stored");
        }
    }

    private void printLegoSetsByMinAge() {
        if (legoSetAPI.numberOfLegoSets() > 0) {
            System.out.println(legoSetAPI.listLegoSetsForAgeRatingAndAbove(
                    ScannerInput.readNextInt("Enter a minimum age: ")));
        } else {
            System.out.println("Option Invalid - no lego sets stored");
        }
    }

    //------------------------------------
    // PERSISTENCE METHODS
    // ------------------------------------
    private void save() {
        try {
            legoSetAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void load() {
        try {
            legoSetAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    //------------------------------------
    // HELPER METHODS
    // ------------------------------------

        private LegoSet askUserToSelectLegoSet() {
            printInStockLegoSets();
            if (legoSetAPI.numberOfLegoSets() > 0) {
                LegoSet legoSet = legoSetAPI.findLegoSet(ScannerInput.readNextInt("Enter the index of the lego set: "));
                if (legoSet != null)
                    return legoSet;  //selected lego set - return it
                else
                    System.out.println("Lego set index is not valid");
            }
            return null;  //no in lego sets
        }

    private LegoSet askUserToSelectInStockLegoSet() {
        printInStockLegoSets();
        if (legoSetAPI.numberOfLegoSetsInStock() > 0) {
            LegoSet legoSet = legoSetAPI.findLegoSet(ScannerInput.readNextInt("Enter the index of the lego set: "));
            if (legoSet != null) {
                if (!legoSet.isInStock()) {
                    System.out.println("Lego Set is OUT OF STOCK; please select a lego set that is IN STOCK.");
                } else {
                    return legoSet;  //selected lego set is in stock - return it
                }
            } else {
                System.out.println("Lego set index is not valid");
            }
        }
        return null;  //no in stock lego sets
    }

    private LegoSet askUserToSelectOutOfStockLegoSet() {
        printOutOfStockLegoSets();
        if (legoSetAPI.numberOfLegoSetsOutOfStock() > 0) {
            LegoSet legoSet = legoSetAPI.findLegoSet(ScannerInput.readNextInt("Enter the index of the lego set: "));
            if (legoSet != null) {
                if (legoSet.isInStock()) {
                    System.out.println("Lego Set is IN STOCK; please select a lego set that is OUT OF STOCK STOCK.");
                } else {
                    return legoSet;  //selected lego set is out of stock - return it
                }
            } else {
                System.out.println("Lego set index is not valid");
            }
        }
        return null;  //no out of stock lego sets
    }

    private String readValidTheme() {
        do {
            String theme = ScannerInput.readNextLine("\tTheme " + ThemeUtility.getThemes() + ": ");
            if (ThemeUtility.isValidTheme(theme)) {
                return theme;
            } else {
                System.err.println("\tTheme not valid.");
            }
        } while (true);
    }

    private int readValidAge() {
        do {
            int age = ScannerInput.readNextInt("`\tMinimum Age`" + MinimumAgeUtility.getAges() + ": ");
            if (MinimumAgeUtility.isValidAge(age)) {
                return age;
            } else {
                System.err.println("\tAge not valid.");
            }
        } while (true);
    }
}