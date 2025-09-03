package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.InstructionBooklet;
import models.LegoSet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LegoSetAPI {

    //TODO Declare an array list of lego sets
    private ArrayList<LegoSet> legoSets = new ArrayList<>();


    //-------------------------------------
    //  ARRAYLIST CRUD
    //-------------------------------------

    //TODO Add a method, addLegoSet(LegoSet). The return type is boolean.
    //     This method will add the lego set object, passed as a parameter to the arraylist of lego sets.
    //     If the add was successful, return true, otherwise, return false.
    public boolean addLegoSet(LegoSet legoSet) {
        return legoSets.add(legoSet);
    }

    //TODO Add a method, updateLegoSet(int, String, int, double, int, String, int).  The return type is boolean.
    //     This method takes in, as the first parameter, the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     The remaining parameters hold the new data for each of the fields in LegoSet that are being updated.
    //     If the update was successful, then return true.
    public boolean updateLegoSet(int indexToUpdate, String name, int code, double cost,
                                 int pieceCount, String theme, int minimumAge) {

        //find the lego set object by the index number
        LegoSet foundLegoSet = findLegoSet(indexToUpdate);

        //if the lego set exists, use the details passed as parameters to
        //update the found lego set in the ArrayList.
        if (foundLegoSet != null) {
            foundLegoSet.setName(name);
            foundLegoSet.setCost(cost);
            foundLegoSet.setPieceCount(pieceCount);
            foundLegoSet.setTheme(theme);
            foundLegoSet.setMinimumAge(minimumAge);
            foundLegoSet.setCode(code);
            return true;
        }

        //if the lego set was not found, return false, indicating that the update was not successful
        return false;
    }

    //TODO Add a method, deleteLegoSet(int).  The return type is LegoSet.
    //     This method takes in the index of the lego set object that you want to delete.
    //     If the index is invalid (i.e. there is no lego set object at that location), return null.
    //     If the index is valid, remove the object at that index location.  Return the object you just deleted.
    public LegoSet deleteLegoSet(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return legoSets.remove(indexToDelete);
        }
        return null;
    }

    //-------------------------------------
    //  ARRAYLIST - Stock Status Update
    //-------------------------------------

    //TODO Add a method, setLegoSetInStock(int).  The return type is boolean.
    //     This method takes in the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     If the index is valid, retrieve the object and:
    //        If the object is not in stock, set it to being in stock and return true.
    //        If the object is already in stock, return false.
    public boolean setLegoSetInStock(int index){
        LegoSet legoSet = findLegoSet(index);
        if (legoSet != null){
            if (!legoSet.isInStock()){
                legoSet.setInStock(true);
                return true;
            }
        }
        return false;
    }

    //TODO Add a method, setLegoSetOutOfStock(int).  The return type is boolean.
    //     This method takes in the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     If the index is valid, retrieve the object and:
    //        If the object is already in stock, set it to being out of stock and return true.
    //        If the object is not in stock, return false.
    public boolean setLegoSetOutOfStock(int index){
        LegoSet legoSet = findLegoSet(index);
        if (legoSet != null){
            if (legoSet.isInStock()){
                legoSet.setInStock(false);
                return true;
            }
        }
        return false;
    }

    //-------------------------------------
    //  Counting Methods - Basic
    //-------------------------------------
    //TODO Add a method, numberOfLegoSets().  The return type is int.
    //     This method returns the number of lego set objects currently stored in the array list.
    public int numberOfLegoSets() {
        return legoSets.size();
    }

    //TODO Add a method, numberOfLegoSetsInStock().  The return type is int.
    //     This method returns the number of lego set objects in the array list that are in currently in stock.
    public int numberOfLegoSetsInStock() {
        return (int) legoSets
                .stream()
                .filter(LegoSet::isInStock)
                .count();
    }

    //TODO Add a method, numberOfLegoSetsOutOfStock().  The return type is int.
    //     This method returns the number of lego set objects in the array list that are out of stock.
    public int numberOfLegoSetsOutOfStock() {
        return (int) legoSets
                .stream()
                .filter(p -> !p.isInStock())
                .count();
    }

    //-------------------------------------
    //  Counting Methods - Advanced
    //-------------------------------------

    //TODO Add a method, numberOfLegoSetsByTheme(String).  The return type is int.
    //     This method returns the number of lego set objects in the array list that match the theme (parameter value).
    public int numberOfLegoSetsByTheme(String theme) {
        return (int) legoSets
                .stream()
                .filter(p -> p.getTheme().equalsIgnoreCase(theme))
                .count();
    }

    //TODO Add a method, numberOfLegoSetsForAgeRatingAndAbove(int).  The return type is int.
    //     This method returns the number of lego set objects in the array list that are equal to
    //     or above the age passed as a parameter value.
    public int numberOfLegoSetsForAgeRatingAndAbove(int minimumAge) {
        return (int) legoSets
                .stream()
                .filter(p -> p.getMinimumAge() >= minimumAge)
                .count();
    }

    //TODO Add a method, totalNumberOfInstructionBooklets().  The return type is int.
    //     This method returns the total number of instruction booklets across all the lego set objects
    //     currently stored in the array list.
    public int totalNumberOfInstructionBooklets() {
        return legoSets
                .stream()
                .mapToInt(LegoSet::numberOfInstructionBooklets)
                .sum();
    }

    //------------------------------------
    // LISTING METHODS - Basic
    //------------------------------------
    //TODO Add a method, listAllLegoSets().  The return type is String.
    //     This method returns a list of the lego sets stored in the array list.
    //     Each lego set should be on a new line and should be preceded by the index number e.g.
    //        0: Lego Set 1 Details
    //        1: Lego Set 2 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    public String listAllLegoSets() {
        if (legoSets.isEmpty()) {
            return "No Lego sets stored";
        } else {
            String listOfLegoSets = "";
            for (int i = 0; i < legoSets.size(); i++) {
                listOfLegoSets += i + ": " + legoSets.get(i) + "\n";
            }
            return listOfLegoSets;
        }
    }

    //TODO Add a method, listLegoSetsInStock().  The return type is String.
    //     This method returns a list of the IN STOCK lego sets stored in the array list.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        0: Lego Set 1 Details
    //        3: Lego Set 4 Details
    //    If there are no IN STOCK lego sets stored in the array list, the return string should have "No Lego sets in stock".
    public String listLegoSetsInStock() {
        if (numberOfLegoSetsInStock() == 0) {
            return "No Lego sets in stock";
        } else {
            String listOfLegoSets = "";
            for (LegoSet legoSet : legoSets) {
                if (legoSet.isInStock()) {
                    listOfLegoSets += legoSets.indexOf(legoSet) + ": " + legoSet + "\n";
                }
            }
            return listOfLegoSets;
        }
    }

    //TODO Add a method, listLegoSetsOutOfStock().  The return type is String.
    //     This method returns a list of the OUT OF STOCK lego sets stored in the array list.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no OUT OF STOCK lego sets stored in the array list, the return string should
    //        have "No Lego sets are out of stock".
    public String listLegoSetsOutOfStock() {
        if (numberOfLegoSetsOutOfStock() == 0) {
            return "No Lego sets are out of stock";
        } else {
            String listOfLegoSets = "";
            for (LegoSet legoSet : legoSets) {
                if (!legoSet.isInStock()) {
                    listOfLegoSets += legoSets.indexOf(legoSet) + ": " + legoSet + "\n";
                }
            }
            return listOfLegoSets;
        }
    }

    //------------------------------------
    // LISTING METHODS - Advanced
    //------------------------------------
    //TODO Add a method, listLegoSetsBySpecificTheme(String).  The return type is String.
    //    This method returns a list of the lego sets of a specific theme stored in the array list (i.e.
    //     that match the parameter value).
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets matching the theme, the return string should have "No Lego sets with theme".
    public String listLegoSetsBySpecificTheme(String theme) {
        if (legoSets.isEmpty()) {
            return "No Lego sets stored";
        } else if (numberOfLegoSetsByTheme(theme) == 0) {
            return "No Lego sets with theme: " + theme;
        } else {
            String listOfLegoSets = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getTheme().equalsIgnoreCase(theme)) {
                    listOfLegoSets += i + ": " + legoSets.get(i) + "\n";
                }
            }
            return numberOfLegoSetsByTheme(theme) + " lego sets with theme "
                    + theme.toUpperCase() + ":\n" + listOfLegoSets;
        }
    }

    //TODO Add a method, listLegoSetsForAgeRatingAndAbove(int).  The return type is String.
    //    This method returns a list of the lego sets that are equal or above the age supplied as a parameter.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets equal or above the age, the return string should have "No Lego sets available".
    public String listLegoSetsForAgeRatingAndAbove(int age) {
        if (legoSets.isEmpty()) {
            return "No Lego sets stored";
        } else if (numberOfLegoSetsForAgeRatingAndAbove(age) == 0) {
            return "No Lego sets available for age " + age + "+";
        } else {
            String listOfLegoSets = "";
            for (int i = 0; i < legoSets.size(); i++) {
                if (legoSets.get(i).getMinimumAge() >= age) {
                    listOfLegoSets += i + ": " + legoSets.get(i) + "\n";
                }
            }
            return numberOfLegoSetsForAgeRatingAndAbove(age) + " lego sets available for age " + age + "+"
                    + ":\n" + listOfLegoSets;
        }
    }

    //TODO Add a method, listAllInstructionBooklets().  The return type is String.
    //    This method returns a list of all the instruction booklets
    //    This method returns a list of all the instruction booklets across all the lego set objects
    //    stored in the array list.
    //    Each instruction booklet should be on a new line and should contain the lego set name and code too e.g.
    //       Booket1.pdf (Firestation, 43544)
    //       Booket2.pdf (Firestation, 43544)
    //       Instructions1.pdf (Titanic, 54655)
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    public String listAllInstructionBooklets() {
        if (numberOfLegoSets() == 0) {
            return "No Lego sets stored";
        } else {
            String listOfBooklets = "";
            for (LegoSet legoSet : legoSets) {
                for (InstructionBooklet instructionBooklet : legoSet.getInstructionBooklets()) {
                    listOfBooklets +=
                            instructionBooklet + " (Lego Set: " + legoSet.getName() + ")\n";
                }
            }
            return listOfBooklets;
        }
    }

    //TODO Add a method, listStockStatusBySpecificTheme(String).  The return type is String.
    //    This method returns a report (the returned String) of the stock status of the lego sets of a specific theme.
    //    The report (the returned String) should include:
    //        the number or lego sets that are IN stock and the list of these lego sets (if no lego sets
    //             are in stock, this should be included in the returned string.
    //        the number or lego sets that are OUT OF stock and the list of these lego sets (if no lego sets
    //             are out of stock, this should be included in the returned string.
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets matching the theme, the return string should have "No Lego sets with theme".
    public String listStockStatusBySpecificTheme(String theme) {
        if (numberOfLegoSets() == 0) {
            return "No Lego sets stored";
        } else if (numberOfLegoSetsByTheme(theme) == 0) {
            return "No Lego sets with theme: " + theme;
        } else {
            String listInStock = "", listOutOfStock = "";
            int numberInStock = 0, numberOutOfStock = 0;

            for (LegoSet legoSet : legoSets) {
                if (legoSet.getTheme().equalsIgnoreCase(theme)) {
                    if (legoSet.isInStock()) {
                        listInStock += legoSet.getName() + " (" + legoSet.getCode() + ")\n";
                        numberInStock++;
                    } else {
                        listOutOfStock += legoSet.getName() + " (" + legoSet.getCode() + ")\n";
                        numberOutOfStock++;
                    }
                }
            }

            String returnString = "";

            if (numberInStock != 0) {
                returnString += "Number of " + theme + " Lego Sets In Stock: " + numberInStock + "\n" + listInStock;
            } else {
                returnString += "Number of " + theme + " Lego Sets In Stock: 0 \n";
            }
            if (numberOutOfStock != 0) {
                returnString += "Number of " + theme + " Lego Sets Out of Stock: " + numberOutOfStock + "\n" + listOutOfStock;
            } else {
                returnString += "Number of " + theme + " Lego Sets Out of Stock: 0 \n";
            }
            return returnString;
        }
    }


    //------------------------------
    //  FINDING METHODS
    //-------------------------------
    //TODO Add a method, findLegoSet(int).  The return type is LegoSet.
    //    This method returns the lego set stored at the index that was passed as a parameter.
    //    However, if the index is not valid, null is returned.
    public LegoSet findLegoSet(int index) {
        if (isValidIndex(index)) {
            return legoSets.get(index);
        }
        return null;
    }

    //TODO Add a method, findLegoSetByCode(int).  The return type is LegoSet.
    //    This method searches the array list for a lego set with a specific code (passed as a parameter).
    //    When a lego set is found for this code, it is returned back.
    //    If no lego set exists for that code, return null.
    // NOTE: the first lego set encountered is returned, even if more exist with that code.  For extra credit,
    //       you could add in validation to ensure that the code is unique when adding a LegoSet.
    public LegoSet findLegoSetByCode(int code) {
        for (LegoSet legoSet : legoSets) {
            if (legoSet.getCode() == code) {
                return legoSet;
            }
        }
        return null;
    }

    //------------------------------
    //  SEARCHING METHODS
    //-------------------------------
    //TODO Add a method, searchLegoSetsByName(String).  The return type is String.
    //    This method returns a list of the lego sets whose name contains the string passed as a parameter.
    //    Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets whose name contains the supplied string, the return string should
    //    have "No Lego sets found".
    public String searchLegoSetsByName(String name) {
        if (numberOfLegoSets() == 0) {
            return "No Lego sets Stored.";
        } else {
            String foundLegoSets = "";
            for (LegoSet legoSet : legoSets) {
                if (legoSet.getName().toLowerCase().contains(name.toLowerCase())) {
                    foundLegoSets += legoSets.indexOf(legoSet) + ": " + legoSet + "\n";
                }
            }
            if (foundLegoSets.equals("")) {
                return "No Lego sets found for: " + name;
            }
            return foundLegoSets;
        }
    }

    //TODO Add a method, searchInstructionBookletsByFileName(String).  The return type is String.
    //    This method returns a list of instruction booklets whose file name contains the string passed
    //    as a parameter.
    //    Each matching booklet should be on a new line and should contain the lego set name and code e.g.
    //        InstructionBook1.pdf in Fire Station (45343)
    //        InstructionBk2.pdf in Titanic (65434)
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets whose name contains the supplied string, the return string should
    //    have "No instruction booklets found".
    public String searchInstructionBookletsByFileName(String fileName) {
        if (numberOfLegoSets() == 0) {
            return "No Lego sets stored.";
        } else {
            String listOfBooklets = "";
            for (LegoSet legoSet : legoSets) {
                for (InstructionBooklet instructionBooklet : legoSet.getInstructionBooklets()) {
                    if (instructionBooklet.getFileName().toLowerCase().contains(fileName.toLowerCase())) {
                        listOfBooklets += instructionBooklet +  " from..."
                                + legoSet.getName() + " (" + legoSet.getCode() + ")\n";
                    }
                }
            }
            if (listOfBooklets.equals("")) {
                return "No instruction booklets found for: " + fileName;
            }
            return listOfBooklets;
        }
    }

    //-------------------------
    // HELPER METHODS
    //-------------------------
    //TODO Add a method, isValidIndex(int).  The return type is boolean.
    //    This method returns true if the value passed as a parameter is a valid index in the arraylist.
    //    However, if the index is not valid, false is returned.
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < legoSets.size());
    }


    //-------------------------
    // PERSISTENCE METHODS
    //-------------------------
    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the lego sets and their associated booklets from
    //    an XML file into the legoSets array list.
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation
        Class<?>[] classes = new Class[]{LegoSet.class, InstructionBooklet.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("legosets.xml"));
        legoSets = (ArrayList<LegoSet>) is.readObject();
        is.close();
    }

    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the lego sets and their associated booklets to
    //    an XML file.
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("legosets.xml"));
        out.writeObject(legoSets);
        out.close();
    }
}

