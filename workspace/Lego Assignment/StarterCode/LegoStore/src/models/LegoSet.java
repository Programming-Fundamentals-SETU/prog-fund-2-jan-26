package models;

public class LegoSet {

    // TODO The lego set name field (String) has a maximum 35 chars.
    //     Default value is "".
    //     When creating the lego set, truncate the name to 35 characters.
    //     When updating an existing lego set, only update the name if it is 35 characters or less.


    // TODO The code field (int) must be between 10000 and 99999 (both inclusive).  Default value is 10000.


    //TODO The cost field (double) must be greater than zero.  The default value is MAX_VALUE for Double.


    //TODO The piece count field (int) must be between 1 and 2000 (both inclusive). The default value is 1.


    //TODO The in stock field (boolean) has a default of true i.e. it is in stock.


    //TODO The theme field (String) has valid values of Classic, City, Creator, or Friends.
    //     The default value is "Classic";


    //TODO The minimum age field (int) has valid values of 4, 6, 9, 10, 13 or 18.
    //     The default value is 4;
    //     When the value is being returned in toString, a plus should be added beside it i.e. 9+, 4+


    //TODO The instruction booklets field is an ArrayList of InstructionBooket objects.


    //TODO Add the constructor, LegoSet(String, int, double, int, String, int), that adheres to the above validation rules.
    //     The order of the fields in the parameter list is the same as the order of fields above i.e. name is
    //     first, then code, then cost, and so on.


    //TODO Add a getter and setter for each field, that adheres to the above validation rules.


    //TODO Add a generated equals method.


    //TODO toSring method: An example of the format of a string being returned would be:
    //     Train Station, City theme (60335) 907 pieces. €99.99 (in stock). Age: 9+.
    //         0: InstructionBook1.pdf (6 pages)
    //         1: InstructionBook2.pdf (0 pages)
    //         2: InstructionBook3.pdf (1 page)
    //    OR
    //     Lunar Space Station, Classic theme (60349) 500 pieces. €79.99 (not available). Age: 10+
    //         No Instruction Booklets


    //-------------------
    // ArrayList handling
    //-------------------

    //TODO numberOfInstructionBooklets(): Add a method that will return the number of items stored in the ArrayList


    // TODO addInstructionBooklet(InstructionBooklet):  Add a method that will add an instruction booklet to
    //      the ArrayList.  It should return true if successful, false otherwise.


    // TODO listInstructionBooklets(): Add a method that will list all of the instruction booklets.  Each
    //      booklet should be on it's own line and should be preceded with the index number in the array list e.g.
    //         0: InstructionBook1.pdf (6 pages)
    //         1: InstructionBook2.pdf (1 page)
    //         2: InstructionBook3.pdf (2 pages)


    //TODO isValidIndex(int): Add a method that will return true if the value of the index (passed as a
    //     parameter) is a valid index number in the instruction booklets array list.


    //TODO findInstructionBooklet(int): Add a method that will return the instruction booklet at a specific
    //     index in the array list.  If the index is invalid, null is returned instead.


    //TODO deleteInstructionBooklet(int): Add a method that will delete the instruction booklet at a specific
    //     index in the array list.  If the index is invalid, null is returned instead.


    //TODO updateInstructionBooklet(int, String, int): Add a method that will take in three parameters:
    //     - The first parameter is the index of the instruction booklet in the arraylist.
    //     - The second parameter is the new value for the filename.
    //     - The third parameter is the new value for the number of pages.
    //     The method will update update the fileName and numberOfPages for an instruction booklet at a
    //     specific index in the array list.
    //     It will return true if the update was successful. If the index is invalid, false is returned instead.

}
