package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Item;
import models.Note;
import utils.Utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This class saves all the entered Notes into an ArrayList.
 *
 * @author Mairead Meagher, Siobhan Drohan
 * @version 1.0
 */

public class NoteAPI {

    //todo can remove the interface above for them for assignment 1, but bring it into assignment 2?

    private ArrayList<Note> notes = new ArrayList<Note>();;

    /**
     * Add the note object, passed as a parameter, to the ArrayList.
     *
     * @param note Note object to be added to the ArrayList.
     */
    public boolean add(Note note) {
        return notes.add(note);
    }

    public boolean updateNote(int indexToUpdate, String noteTitle, int notePriority, String noteCategory) {
        //find the note object by the index number
        Note foundNote = findNote(indexToUpdate);

        //if the note exists, use the details passed as parameters to
        //update the found note in the ArrayList.
        if (foundNote != null) {
            foundNote.setNoteTitle(noteTitle);
            foundNote.setNotePriority(notePriority);
            foundNote.setNoteCategory(noteCategory);
            return true;
        }

        //if the note was not found, return false, indicating that the update was not successful
        return false;
    }

    public Note deleteNote(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return notes.remove(indexToDelete);
        }
        return null;
    }

    public boolean archiveNote(int indexToArchive) {
        if (isValidIndex(indexToArchive))  {
            Note noteToArchive = notes.get(indexToArchive);
            if ((!noteToArchive.isNoteArchived()) && (noteToArchive.checkNoteCompletionStatus())){
                noteToArchive.setNoteArchived(true);
                return true;
            }
        }
        return false;
    }

    public String archiveNotesWithAllItemsComplete(){
        if (numberOfActiveNotes() ==0){
            return "No active notes stored";
        }else {
            String listOfActiveNotesArchived = "";
            for (Note note: notes){
                if (!note.isNoteArchived()) {
                    if (note.checkNoteCompletionStatus()) {
                        note.setNoteArchived(true);
                        listOfActiveNotesArchived += notes.indexOf(note) + ": " + note + "\n";
                    }
                }
            }
            return listOfActiveNotesArchived;
        }
    }

    //-------------------------------------
    //  Counting Methods
    //-------------------------------------
    public int numberOfNotes() {
        return notes.size();
    }

    public int numberOfArchivedNotes() {
        return (int) notes.stream().filter(Note::isNoteArchived).count();
    }

    public int numberOfActiveNotes() {
        return (int) notes.stream().filter(p -> !p.isNoteArchived()).count();
    }

    public int numberOfNotesByCategory(String category) {
        return (int) notes.stream().filter(p -> p.getNoteCategory().equalsIgnoreCase(category)).count();
    }

    public int numberOfNotesByPriority(int priority) {
        return (int) notes.stream().filter(p -> p.getNotePriority() == priority).count();
    }

    public int numberOfItems(){
        int numberOfItems = 0;
        for (Note note: notes){
            for (Item item : note.getItems()){
                numberOfItems++;
            }
        }
        return numberOfItems;
    }

    public int numberOfCompleteItems(){
        int numberOfCompleteItems = 0;
        for (Note note: notes){
            for (Item item : note.getItems()){
                if (item.isItemCompleted()) {
                    numberOfCompleteItems++;
                }
            }
        }
        return numberOfCompleteItems;
    }

    public int numberOfTodoItems(){
        int numberOfTodoItems = 0;
        for (Note note: notes){
            for (Item item : note.getItems()){
                if (!item.isItemCompleted()) {
                    numberOfTodoItems++;
                }
            }
        }
        return numberOfTodoItems;
    }

    //------------------------------------
    // LISTING METHODS
    //------------------------------------

    /**
     * This method builds and returns a String containing all the notes in the ArrayList.
     * For each note stored, the associated index number is included.
     * If no notes are stored in the ArrayList, the String "No notes stored" is returned.
     *
     * @return A String containing all the notes in the ArrayList, or "No notes stored",
     * if empty.
     */
    public String listAllNotes() {
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            String listOfNotes = "";
            for (int i = 0; i < notes.size(); i++) {
                listOfNotes += i + ": " + notes.get(i) + "\n";
            }
            return listOfNotes;
        }
    }

    public String listActiveNotes(){
        if (numberOfActiveNotes() == 0) {
            return "No active notes stored";
        } else {
            String listOfActiveNotes = "";
            for (Note note: notes){
                if (!note.isNoteArchived()) {
                    listOfActiveNotes += notes.indexOf(note) + ": " + note + "\n";
                }
            }
            return listOfActiveNotes;
        }
    }

    public String listArchivedNotes() {
        if (numberOfArchivedNotes() == 0) {
            return "No archived notes stored";
        } else {
            String listOfArchivedNotes = "";
            for (Note note: notes){
                if (note.isNoteArchived()) {
                    listOfArchivedNotes += notes.indexOf(note) + ": " + note + "\n";
                }
            }
            return listOfArchivedNotes;
        }
    }

    public String listNotesBySelectedCategory(String category) {
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            String listOfNotes = "";
            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).getNoteCategory().equalsIgnoreCase(category)) {
                    listOfNotes += i + ": " + notes.get(i) + "\n";
                }
            }
            if (listOfNotes.equals("")){
                return "No notes with category: " + category;
            }
            else{
                return numberOfNotesByCategory(category) + " notes with category " + category.toUpperCase()
                        + ":\n" + listOfNotes;
            }
        }
    }

    public String listNotesBySelectedPriority(int priority) {
        if (notes.isEmpty()) {
            return "No notes stored";
        } else {
            String listOfNotes = "";
            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).getNotePriority() == priority) {
                    listOfNotes += i + ": " + notes.get(i) + "\n";
                }
            }
            if (listOfNotes.equals("")){
                return "No notes with priority: " + priority;
            }
            else{
                return numberOfNotesByPriority(priority) + " notes with priority " + priority
                        + ":\n" + listOfNotes;
            }
        }
    }

    public String listTodoItems(){
        if (numberOfNotes() == 0) {
            return "No notes stored";
        } else {
            String listOfTodoItems = "";
            for (Note note: notes){
                for (Item item : note.getItems()){
                    if (!item.isItemCompleted()){
                        listOfTodoItems += note.getNoteTitle() + ":   " + item + "\n";
                    }
                }
            }
            return listOfTodoItems;
        }
    }

    public String listItemStatusByCategory(String category){
        if (numberOfNotes() == 0) {
            return "No notes stored";
        } else {
            String listOfCompleteItems = "";
            int numberCompletedItems =0;
            String listOfTODOItems = "";
            int numberTODOItems =0;
            for (Note note: notes) {
                if (note.getNoteCategory().equalsIgnoreCase(category)) {
                    if (note.numberOfItems() > 0) {
                        for (Item item : note.getItems()) {
                            if (item.isItemCompleted()) {
                                listOfCompleteItems += item.getItemDescription()
                                        + " (Note: " + note.getNoteTitle() + ")\n";
                                numberCompletedItems++;
                            } else {
                                listOfTODOItems += item.getItemDescription()
                                        + " (Note: " + note.getNoteTitle() + ")\n";
                                numberTODOItems++;
                            }
                        }
                    }
                }
            }
            String returnString = "";
            if (numberCompletedItems != 0) {
                returnString += "Number Completed: " + numberCompletedItems + "\n" + listOfCompleteItems;
            }
            else {
                returnString += "Number Completed: 0 \n";
            }
            if (numberTODOItems != 0) {
                returnString += "Number TODO: " + numberTODOItems + "\n" + listOfTODOItems;
            }
            else {
                returnString += "Number TODO: 0 \n";
            }
            return returnString;
        }
    }


    //------------------------------
    //  FINDING / SEARCHING METHODS
    //-------------------------------

    public Note findNote(int index) {
        if (isValidIndex(index)) {
            return notes.get(index);
        }
        return null;
    }


    /* public Note findNote(String noteTitle) {
         for (Note note: notes){
             if (note.getNoteTitle().equalsIgnoreCase(noteTitle)) {
                 return note;
             }
         }
         return null;
     }*/


    public String searchNotesByTitle(String searchString){
        if (numberOfNotes() == 0) {
            return "No notes stored";
        } else {
            String listOfNotes = "";
            for (Note note: notes){
                if (note.getNoteTitle().toLowerCase().contains(searchString.toLowerCase())) {
                    listOfNotes += notes.indexOf(note) + ": " + note + "\n";
                }
            }
            if (listOfNotes.equals("")) {
                return "No notes found for: " + searchString;
            }
            return listOfNotes;
        }
    }

    public String searchItemByDescription(String searchString){
        if (numberOfNotes() == 0) {
            return "No notes stored";
        } else {
            String listOfNotes = "";
            for (Note note: notes){
                for(Item item : note.getItems()) {
                    if (item.getItemDescription().toLowerCase().contains(searchString.toLowerCase())) {
                        listOfNotes += notes.indexOf(note) + ": " + note.getNoteTitle() + "\n"
                                + "\t" + note.getItems().indexOf(item) + ": " + item + "\n";
                    }
                }
            }
            if (listOfNotes.equals("")) {
                return "No items found for: " + searchString;
            }
            return listOfNotes;
        }
    }

    //-------------------------
    // HELPER METHODS
    //-------------------------
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < notes.size());
    }


    //-------------------------
    // PERSISTENCE METHODS
    //-------------------------

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation
        Class<?>[] classes = new Class[] { Note.class, Item.class };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("notes.xml"));
        notes = (ArrayList<Note>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("notes.xml"));
        out.writeObject(notes);
        out.close();
    }

}
