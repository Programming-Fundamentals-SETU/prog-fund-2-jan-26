package controllers;

public class LegoSetAPI {

    //TODO Declare an array list of lego sets


    //-------------------------------------
    //  ARRAYLIST CRUD
    //-------------------------------------

    //TODO Add a method, addLegoSet(LegoSet). The return type is boolean.
    //     This method will add the lego set object, passed as a parameter to the arraylist of lego sets.
    //     If the add was successful, return true, otherwise, return false.


    //TODO Add a method, updateLegoSet(int, String, int, double, int, String, int).  The return type is boolean.
    //     This method takes in, as the first parameter, the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     The remaining parameters hold the new data for each of the fields in LegoSet that are being updated
    //     i.e. they hold the name, code, cost, pieceCount, theme and minimum age).
    //     If the update was successful, then return true.


    //TODO Add a method, deleteLegoSet(int).  The return type is LegoSet.
    //     This method takes in the index of the lego set object that you want to delete.
    //     If the index is invalid (i.e. there is no lego set object at that location), return null.
    //     If the index is valid, remove the object at that index location.  Return the object you just deleted.


    //-------------------------------------
    //  ARRAYLIST - Stock Status Update
    //-------------------------------------

    //TODO Add a method, setLegoSetInStock(int).  The return type is boolean.
    //     This method takes in the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     If the index is valid, retrieve the object and:
    //        If the object is not in stock, set it to being in stock and return true.
    //        If the object is already in stock, return false.


    //TODO Add a method, setLegoSetOutOfStock(int).  The return type is boolean.
    //     This method takes in the index of the lego set object that you want to update.
    //     If the index is invalid (i.e. there is no lego set object at that location), return false.
    //     If the index is valid, retrieve the object and:
    //        If the object is already in stock, set it to being out of stock and return true.
    //        If the object is not in stock, return false.


    //-------------------------------------
    //  Counting Methods - Basic
    //-------------------------------------

    //TODO Add a method, numberOfLegoSets().  The return type is int.
    //     This method returns the number of lego set objects currently stored in the array list.


    //TODO Add a method, numberOfLegoSetsInStock().  The return type is int.
    //     This method returns the number of lego set objects in the array list that are in currently in stock.


    //TODO Add a method, numberOfLegoSetsOutOfStock().  The return type is int.
    //     This method returns the number of lego set objects in the array list that are out of stock.


    //-------------------------------------
    //  Counting Methods - Advanced
    //-------------------------------------

    //TODO Add a method, numberOfLegoSetsByTheme(String).  The return type is int.
    //     This method returns the number of lego set objects in the array list that match the
    //     theme (i.e. the parameter value).


    //TODO Add a method, numberOfLegoSetsForAgeRatingAndAbove(int).  The return type is int.
    //     This method returns the number of lego set objects in the array list that are equal to
    //     or above the age passed as a parameter value.


    //TODO Add a method, totalNumberOfInstructionBooklets().  The return type is int.
    //     This method returns the total number of instruction booklets across all the lego set objects
    //     currently stored in the array list.


    //------------------------------------
    // LISTING METHODS - Basic
    //------------------------------------

    //TODO Add a method, listAllLegoSets().  The return type is String.
    //     This method returns a list of the lego sets stored in the array list.
    //     Each lego set should be on a new line and should be preceded by the index number e.g.
    //        0: Lego Set 1 Details
    //        1: Lego Set 2 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".


    //TODO Add a method, listLegoSetsInStock().  The return type is String.
    //     This method returns a list of the IN STOCK lego sets stored in the array list.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        0: Lego Set 1 Details
    //        3: Lego Set 4 Details
    //    If there are no IN STOCK lego sets stored in the array list, the return string should
    //    have "No Lego sets in stock".


    //TODO Add a method, listLegoSetsOutOfStock().  The return type is String.
    //     This method returns a list of the OUT OF STOCK lego sets stored in the array list.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no OUT OF STOCK lego sets stored in the array list, the return string should
    //        have "No Lego sets are out of stock".


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


    //TODO Add a method, listLegoSetsForAgeRatingAndAbove(int).  The return type is String.
    //    This method returns a list of the lego sets that are equal or above the age supplied as a parameter.
    //     Each matching lego set should be on a new line and should be preceded by the index number e.g.
    //        1: Lego Set 2 Details
    //        4: Lego Set 5 Details
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets equal or above the age, the return string should have "No Lego sets available".


    //TODO Add a method, listAllInstructionBooklets().  The return type is String.
    //    This method returns a list of all the instruction booklets across all the lego set objects
    //    stored in the array list.
    //    Each instruction booklet should be on a new line and should contain the lego set name and code too e.g.
    //       Booket1.pdf (Fire Station, 43544)
    //       Booket2.pdf (Fire Station, 43544)
    //       Instructions1.pdf (Titanic, 54655)
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".


    //TODO Add a method, listStockStatusBySpecificTheme(String).  The return type is String.
    //    This method returns a report (the returned String) of the stock status of the lego sets in a specific theme.
    //    The report (the returned String) should include:
    //        the number or lego sets that are IN stock and the list of these lego sets (if no lego sets
    //             are in stock, this should be included in the returned string.
    //        the number or lego sets that are OUT OF stock and the list of these lego sets (if no lego sets
    //             are out of stock, this should be included in the returned string.
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets matching the theme, the return string should have "No Lego sets with theme".


    //------------------------------
    //  FINDING METHODS
    //-------------------------------

    //TODO Add a method, findLegoSet(int).  The return type is LegoSet.
    //    This method returns the lego set stored at the index that was passed as a parameter.
    //    However, if the index is not valid, null is returned.


    //TODO Add a method, findLegoSetByCode(int).  The return type is LegoSet.
    //    This method searches the array list for a lego set with a specific code (passed as a parameter).
    //    When a lego set is found for this code, it is returned back.
    //    If no lego set exists for that code, return null.
    // NOTE: the first lego set encountered is returned, even if more exist with that code.  For extra credit,
    //       you could add in validation to ensure that the code is unique when adding a LegoSet.


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


    //TODO Add a method, searchInstructionBookletsByFileName(String).  The return type is String.
    //    This method returns a list of instruction booklets whose file name contains the string passed
    //    as a parameter.
    //    Each matching booklet should be on a new line and should contain the lego set name and code e.g.
    //        InstructionBook1.pdf in Fire Station (45343)
    //        InstructionBk2.pdf in Titanic (65434)
    //    If there are no lego sets stored in the array list, return a string that contains "No Lego sets".
    //    If there are no lego sets whose name contains the supplied string, the return string should
    //    have "No instruction booklets found".


    //-------------------------
    // HELPER METHODS
    //-------------------------

    //TODO Add a method, isValidIndex(int).  The return type is boolean.
    //    This method returns true if the value passed as a parameter is a valid index in the arraylist.
    //    However, if the index is not valid, false is returned.


    //-------------------------
    // PERSISTENCE METHODS
    //-------------------------

    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the lego sets and their associated booklets from
    //    an XML file into the legoSets array list.


    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the lego sets and their associated booklets to
    //    an XML file.

}

