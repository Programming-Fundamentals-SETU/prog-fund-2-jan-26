package main;

import controllers.NoteAPI;
import models.Item;
import models.Note;
import utils.ScannerInput;
import utils.Utilities;
import static utils.CategoryUtility.*;

/**
 * This class runs the application and handles the I/O
 *
 * @author Siobhan Drohan, Mairead Meagher
 * @version 5.1
 */
public class Driver {

    private NoteAPI noteAPI = new NoteAPI();

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        runMenu();
    }

    private int mainMenu() {
        //todo menus - could reduce it to sub menus - or extra credit suggestion?
        //todo maybe ignore this for assignment?  or extra credit suggestion
        //NOTE Status (in utility?): if number items is:
        //     < 25% completed =  Less than 25% complete
        //     < 50% completed =  Less than 50% complete
        //     < 75% completed =  Less than 75% complete
        //     < 100% completed = More than 75% complete
        //     = 100% completed = Completed


        int option = ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |                         NOTE KEEPER APP                        |                         
                ------------------------------------------------------------------
                | NOTE MENU                                                      | 
                |   1) Add a note                                                |
                |   2) List all notes (all, active, archived)                    |
                |   3) Update a note                                             | 
                |   4) Delete a note                                             | 
                |   5) Archive a note                                            | 
                ------------------------------------------------------------------
                | ITEM MENU                                                      | 
                |   6) Add an item to a note                                     |
                |   7) Update item description on a note                         |
                |   8) Delete an item from a note                                |
                |   9) Mark item as complete/todo                                | 
                ------------------------------------------------------------------
                | REPORT MENU FOR NOTES                                          | 
                |   10) All notes and their items (active & archived)            |
                |   11) Archive notes whose items are all complete               |
                |   12) All notes within a selected Category                     |
                |   13) All notes within a selected Priority                     |
                |   14) Search for all notes (by note title)                     |
                ------------------------------------------------------------------
                | REPORT MENU FOR ITEMS                                          |                                
                |   15) All items that are todo (with note title)                |  
                |   16) Overall number of items todo/complete                    |
                |   17) Todo/complete items by specific Category                 |
                |   18) Search for all items (by item description )              |
                ------------------------------------------------------------------
                | SETTINGS MENU                                                  | 
                |   20) Save                                                     |  
                |   21) Load                                                     |  
                |   0)  Exit                                                     |  
                ------------------------------------------------------------------
                ==>>  """);
        return option;
    }

    private void runMenu() {
        int option = mainMenu();

        while (option != 0) {

            switch (option) {
                case 1  -> addNote();
                case 2  -> viewNotes();
                case 3  -> updateNote();
                case 4  -> deleteNote();
                case 5  -> archiveNote();
                case 6  -> addItemToNote();
                case 7  -> updateItemDescInNote();
                case 8  -> deleteItemFromNote();
                case 9  -> markCompletionOfItem();
                case 10 -> printActiveAndArchivedReport();
                case 11 -> archiveNotesWithAllItemsComplete();
                case 12 -> printNotesBySelectedCategory();
                case 13 -> printNotesByPriority();
                case 14 -> searchNotesByTitle();
                case 15 -> printAllTodoItems();
                case 16 -> printOverallItemsTodoComplete();
                case 17 -> printItemCompletionStatusByCategory();
                case 18 -> searchItemsByDescription();
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
    //NOTE MENU
    //------------------------------------

    //gather the note data from the user and create a new product object.
    private void addNote() {
        String noteTitle = ScannerInput.readNextLine("Enter a title for the note: ");
        int notePriority = ScannerInput.readNextInt("Enter a priority (1-low, 2, 3, 4, 5-high): ");
        String noteCategory = readValidCategory();

        boolean isAdded = noteAPI.add(new Note(noteTitle, notePriority, noteCategory));
        if (isAdded) {
            System.out.println("Added Successfully");
        } else {
            System.out.println("Add Failed");
        }
    }

    private void viewNotes() {
        if (noteAPI.numberOfNotes() > 0) {
            int option = ScannerInput.readNextInt("""
                    --------------------------------
                    |   1) View ALL notes          |
                    |   2) View ACTIVE notes       |
                    |   3) View ARCHIVED notes     |
                    --------------------------------
                    ==>>  """);

            switch (option) {
                case 1 -> printAllNotes();
                case 2 -> printActiveNotes();
                case 3 -> printArchivedNotes();
                default -> System.out.println("Invalid option entered: " + option);
            }
        }
        else{
            System.out.println("Option Invalid - No notes stored");
        }
    }

    private void updateNote() {
        printAllNotes();
        if (noteAPI.numberOfNotes() > 0) {
            //only ask the user to choose the note if notes exist
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the note to update: ");
            if (noteAPI.isValidIndex(indexToUpdate)) {
                String noteTitle = ScannerInput.readNextLine("Enter a title for the note: ");
                int notePriority = ScannerInput.readNextInt("Enter a priority (1-low, 2, 3, 4, 5-high): ");
                String noteCategory = ScannerInput.readNextLine("Enter a category: ");

                //pass the index of the note and the new note details to NoteAPI for updating and check for success.
                if (noteAPI.updateNote(indexToUpdate, noteTitle, notePriority, noteCategory)) {
                    System.out.println("Update Successful");
                } else {
                    System.out.println("Update Failed");
                }
            } else {
                System.out.println("There are no notes for this index number");
            }
        }
    }

    private void deleteNote() {
        printAllNotes();
        if (noteAPI.numberOfNotes() > 0) {
            //only ask the user to choose the note to delete if notes exist
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the note to delete: ");
            //pass the index of the note to NoteAPI for deleting and check for success.
            Note noteToDelete = noteAPI.deleteNote(indexToDelete);
            if (noteToDelete != null) {
                System.out.println("Delete Successful! Deleted note: " + noteToDelete.getNoteTitle());
            } else {
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void archiveNote() {
        printActiveNotes();
        if (noteAPI.numberOfActiveNotes() > 0) {
            //only ask the user to choose the note to archive if active notes exist
            int indexToArchive = ScannerInput.readNextInt("Enter the index of the note to archive: ");
            //pass the index of the note to NoteAPI for archiving and check for success.
            if (noteAPI.archiveNote(indexToArchive)) {
                System.out.println("Archive Successful!");
            } else {
                System.out.println("Archive NOT Successful");
            }
        }
    }

    //------------------------------------
    //ITEM MENU
    //------------------------------------
    private void addItemToNote() {
        Note note = askUserToSelectActiveNote();
        if (note != null){
            if (note.addItem(new Item(ScannerInput.readNextLine("\t Item Description: "))))
                System.out.println("Add Successful!");
            else
                System.out.println("Add NOT Successful");
        }
    }

    private void updateItemDescInNote() {
        Note note = askUserToSelectActiveNote();
        if (note != null){
            if(note.numberOfItems() > 0) {
                int itemIndex = ScannerInput.readNextInt("Enter the index of the item: ");
                if (note.isValidIndex(itemIndex)) {
                    String newDesc = ScannerInput.readNextLine("Enter a new description: ");
                    boolean noteStatus = note.getItems().get(itemIndex).isItemCompleted();
                    if (note.updateItem(itemIndex, newDesc, noteStatus )) {
                        System.out.println("Item description updated");
                    } else {
                        System.out.println("Item description NOT updated");
                    }
                } else {
                    System.out.println("Invalid Item Index");
                }
            }
            else{
                System.out.println("No items for this note");
            }
        }
    }

    private void deleteItemFromNote() {
        Note note = askUserToSelectActiveNote();
        if (note != null){
            if(note.numberOfItems() > 0) {
                if (note.deleteItem(ScannerInput.readNextInt("Enter the index of the item: ")) != null) {
                    System.out.println("Delete Successful!");
                } else {
                    System.out.println("Invalid Item Index");
                }
            }
            else{
                System.out.println("No items for this note");
            }
        }
    }

    private void markCompletionOfItem() {
        Note note = askUserToSelectActiveNote();
        if (note != null){
            if(note.numberOfItems() > 0) {
                Item item = note.findItem(ScannerInput.readNextInt("Enter the index of the item: "));
                if (item != null) {
                    boolean complete = Utilities.YNtoBoolean(ScannerInput.readNextChar("Is the item complete (y/n): "));
                    item.setItemCompleted(complete);
                    if (complete){
                        System.out.println("Item is complete");
                    }
                    else{
                        System.out.println("Item is NOT complete");
                    }
                } else {
                    System.out.println("Invalid Item Index");
                }
            }
            else{
                System.out.println("No items for this note");
            }
        }
    }

    //------------------------------------
    //DISPLAYING / REPORTING OF NOTES
    //------------------------------------
    private void printAllNotes() {
        if (noteAPI.numberOfNotes() > 0) {
            System.out.println(noteAPI.numberOfNotes() + " active and archived note(s): ");
        }
        System.out.println(noteAPI.listAllNotes());
    }

    private void printArchivedNotes() {
        if (noteAPI.numberOfArchivedNotes() > 0) {
            System.out.println(noteAPI.numberOfArchivedNotes() + " archived note(s): ");
        }
        System.out.println(noteAPI.listArchivedNotes());
    }

    private void printActiveNotes() {
        if (noteAPI.numberOfActiveNotes() > 0) {
            System.out.println(noteAPI.numberOfActiveNotes() + " active note(s): ");
        }
        System.out.println(noteAPI.listActiveNotes());
    }

    private void printActiveAndArchivedReport (){
        System.out.println(" ------------------------------------------------------------------");
        printActiveNotes();
        System.out.println(" ------------------------------------------------------------------");
        printArchivedNotes();
        System.out.println(" ------------------------------------------------------------------");
    }

    private void archiveNotesWithAllItemsComplete(){
        System.out.println(noteAPI.archiveNotesWithAllItemsComplete());
    }

    private void printNotesBySelectedCategory(){
        if (noteAPI.numberOfNotes() > 0) {
            String category = readValidCategory();
            System.out.println(noteAPI.listNotesBySelectedCategory(category));
        }
        else{
            System.out.println("Option Invalid - no notes stored");
        }
    }

    private void printNotesByPriority() {
        if (noteAPI.numberOfNotes() > 0) {
            System.out.println(noteAPI.listNotesBySelectedPriority(
                    ScannerInput.readNextInt("Enter a priority (1-low, 2, 3, 4, 5-high): ")));
        }
        else{
            System.out.println("Option Invalid - no notes stored");
        }
    }

    private void searchNotesByTitle() {
        if (noteAPI.numberOfNotes() > 0) {
            String searchString = ScannerInput.readNextLine("Enter the title to search by: ");
            System.out.println(noteAPI.searchNotesByTitle(searchString));
        }
        else{
            System.out.println("Search not valid - No notes stored");
        }
    }

    //------------------------------------
    //DISPLAYING / REPORTING OF ITEMS
    //------------------------------------
    private void printAllTodoItems(){
        System.out.println(noteAPI.listTodoItems());
    }

    private void printOverallItemsTodoComplete(){
        if (noteAPI.numberOfItems() > 0) {
            System.out.println("Number of Completed Items: " + noteAPI.numberOfCompleteItems());
            System.out.println("Number of TODO Items: " + noteAPI.numberOfTodoItems());
        }
        else{
            System.out.println("No items stored");
        }
    }

    private void printItemCompletionStatusByCategory() {
        if (noteAPI.numberOfItems() > 0) {
            String noteCategory = readValidCategory();
            System.out.println(noteAPI.listItemStatusByCategory(noteCategory));
        }
        else{
            System.out.println("No items stored");
        }
    }

    private void searchItemsByDescription() {
        if (noteAPI.numberOfItems() > 0) {
            String searchString = ScannerInput.readNextLine("Enter the item description to search by: ");
            System.out.println(noteAPI.searchItemByDescription(searchString));
        }
        else{
            System.out.println("Search not valid - No items stored");
        }
    }

    //------------------------------------
    // PERSISTENCE METHODS
    // ------------------------------------
    private void save() {
        try {
            noteAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void load() {
        try {
            noteAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    //------------------------------------
    // HELPER METHODS
    // ------------------------------------
    private Note askUserToSelectActiveNote(){
        printActiveNotes();
        if (noteAPI.numberOfActiveNotes() > 0) {
            Note note = noteAPI.findNote(ScannerInput.readNextInt("Enter the index of the note: "));
            if (note != null) {
                if (note.isNoteArchived()) {
                    System.out.println("Note is Archived; please select an Active note.");
                }
                else{
                    return note;  //selected note is active
                }
            }
            else{
                System.out.println("Note index is not valid");
            }
        }
        return null;  //selected note is not active
    }

    private String readValidCategory() {
        do {
            String category = ScannerInput.readNextLine("Enter the category " + getCategories() + ": ");
            if (isValidCategory(category)) {
                return category;
            } else {
                System.err.println("\tCategory not valid.");
            }
        } while (true);
    }

}