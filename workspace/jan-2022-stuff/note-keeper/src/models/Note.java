package models;

import utils.CategoryUtility;
import utils.Utilities;

import java.util.ArrayList;
import java.util.Objects;

public class Note {
    private String noteTitle = "No Title";
    private int notePriority = 1;
    private String noteCategory = "";
    private boolean isNoteArchived = false;
    private ArrayList<Item> items = new ArrayList<>();

    public Note(String noteTitle, int notePriority, String noteCategory) {
        this.noteTitle = Utilities.truncateString(noteTitle, 20);
        setNotePriority(notePriority);
        setNoteCategory(noteCategory);
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        if (Utilities.validStringlength(noteTitle, 20))
                this.noteTitle = noteTitle;
    }

    public int getNotePriority() {
        return notePriority;
    }

    public void setNotePriority(int notePriority) {
        if (Utilities.validRange(notePriority, 1, 5)) {
            this.notePriority = notePriority;
        }
    }

    public String getNoteCategory() {
        return noteCategory;
    }

    public void setNoteCategory(String noteCategory) {
        if (CategoryUtility.isValidCategory(noteCategory)){
            this.noteCategory = noteCategory;
        }
    }

    public boolean isNoteArchived() {
        return isNoteArchived;
    }

    public void setNoteArchived(boolean noteArchived) {
        isNoteArchived = noteArchived;
    }

    //-------------------
    // ArrayList handling
    //-------------------
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int numberOfItems(){
        return items.size();
    }

    public boolean checkNoteCompletionStatus(){
        if (!items.isEmpty()) {
            for (Item item : items) {
                if (!item.isItemCompleted()) {
                    return false;
                }
            }
            return true;
        }
        return true;  //a note with empty items can be archived.
    }


    public boolean addItem(Item item){
        return items.add(item);
    }

    public String listItems() {
        if (items.isEmpty()) {
            return "\n\tNo items added";
        } else {
            String listOfItems = "";
            for (int i = 0; i < items.size(); i++) {
                listOfItems += "\n\t" + i + ": " + items.get(i);
            }
            return listOfItems;
        }
    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < items.size());
    }

    public Item findItem(int index) {
        if (isValidIndex(index)) {
            return items.get(index);
        }
        return null;
    }

    public Item deleteItem(int index) {
        if (isValidIndex(index)) {
            return items.remove(index);
        }
        return null;
    }

    public boolean updateItem(int index, String itemDescription, boolean itemCompleted) {
        //find the object by the index number
        Item foundItem = findItem(index);

        //if the object exists, use the details passed in the updateDetails parameter to
        //update the found object in the ArrayList.
        if (foundItem != null) {
            foundItem.setItemDescription(itemDescription);
            foundItem.setItemCompleted(itemCompleted);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    //-------------------
    // ArrayList handling
    //-------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return notePriority == note.notePriority && isNoteArchived == note.isNoteArchived && Objects.equals(noteTitle, note.noteTitle) && Objects.equals(noteCategory, note.noteCategory) && Objects.equals(items, note.items);
    }

    @Override
    public String toString() {
        return  noteTitle +

                ", Priority=" + notePriority +
                ", Category=" + noteCategory +
                ", Archived=" + Utilities.booleanToYN(isNoteArchived)
                + listItems();
    }
}
