package models;

import utils.MinimumAgeUtility;
import utils.ThemeUtility;
import utils.Utilities;
import java.util.ArrayList;
import java.util.Objects;

public class LegoSet {

    // TODO The lego set name field (String) has a maximum 35 chars.
    //     Default value is "".
    //     When creating the lego set, truncate the name to 35 characters.
    //     When updating an existing lego set, only update the name if it is 35 characters or less.
    private String name = "";

    // TODO The code field (int) must be between 10000 and 99999 (both inclusive).  Default value is 10000.
    private int code = 10000;

    //TODO The cost field (double) must be greater than zero.  The default value is MAX_VALUE for Double.
    private double cost = Double.MAX_VALUE;

    //TODO The piece count field (int) must be between 1 and 2000 (both inclusive). The default value is 1.
    private int pieceCount = 1;

    //TODO The in stock field (boolean) has a default of true i.e. it is in stock.
    private boolean inStock = true;

    //TODO The theme field (String) has valid values of Classic, City, Creator, or Friends.
    //     The default value is "Classic";
    private String theme = ThemeUtility.getThemes().get(0);

    //TODO The minimum age field (int) has valid values of 4, 6, 9, 10, 13 or 18.
    //     The default value is 4;
    //     When the value is being returned in toString, a + should be added beside it
    private int minimumAge = MinimumAgeUtility.getAges().get(0);

    //TODO The instruction booklets field is an ArrayList of InstructionBooket objects.
    private ArrayList<InstructionBooklet> instructionBooklets = new ArrayList<>();

    //TODO Add the constructor, LegoSet(String, int, double, int, String, int), that adheres to the above validation rules
    public LegoSet(String name, int code, double cost, int pieceCount, String theme, int minimumAge) {
        this.name = Utilities.truncateString(name, 35);
        setCode(code);
        setCost(cost);
        setPieceCount(pieceCount);
        setTheme(theme);
        setMinimumAge(minimumAge);
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Utilities.validStringlength(name, 35)) {
            this.name = name;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        if (Utilities.validRange(code, 10000, 99999)) {
            this.code = code;
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost > 0) {
            this.cost = cost;
        }
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        if (Utilities.validRange(pieceCount, 1, 2000)) {
            this.pieceCount = pieceCount;
        }
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        if (ThemeUtility.isValidTheme(theme)){
            this.theme = theme;
        }
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        if (MinimumAgeUtility.isValidAge(minimumAge)) {
            this.minimumAge = minimumAge;
        }
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public ArrayList<InstructionBooklet> getInstructionBooklets() {
        return instructionBooklets;
    }

    public void setInstructionBooklets(ArrayList<InstructionBooklet> instructionBooklets) {
        this.instructionBooklets = instructionBooklets;
    }

    //TODO Add a generated equals method.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegoSet legoSet)) return false;

        if (code != legoSet.code) return false;
        if (Double.compare(legoSet.cost, cost) != 0) return false;
        if (pieceCount != legoSet.pieceCount) return false;
        if (inStock != legoSet.inStock) return false;
        if (minimumAge != legoSet.minimumAge) return false;
        if (!Objects.equals(name, legoSet.name)) return false;
        if (!Objects.equals(theme, legoSet.theme)) return false;
        return Objects.equals(instructionBooklets, legoSet.instructionBooklets);
    }

    //TODO toSring method: An example of the format of a string being returned would be:
    //     Train Station, City theme (60335) 907 pieces. €99.99 (in stock). Age: 9+.
    //         0: InstructionBook1.pdf (6 pages)
    //         1: InstructionBook2.pdf (0 pages)
    //         2: InstructionBook3.pdf (1 page)
    //    OR
    //     Lunar Space Station, Classic theme (60349) 500 pieces. €79.99 (not available). Age: 10+
    //         No Instruction Booklets
    @Override
    public String toString() {
        String pieces = pieceCount == 1 ? " piece. " : " pieces. ";
        String stockText = inStock ? " (in stock)." : " (not available).";
        return name
                + ", " + theme + " theme"
                + " (" + code + ") "
                + pieceCount + pieces
                + "€" + cost
                + stockText
                + " Age: " + minimumAge + "+"
                + listInstructionBooklets();
    }

    //-------------------
    // ArrayList handling
    //-------------------

    //TODO numberOfInstructionBooklets(): Add a method that will return the number of items in the ArrayList
    public int numberOfInstructionBooklets(){
        return instructionBooklets.size();
    }

    // TODO addInstructionBooklet(InstructionBooklet):  Add a method that will add an instruction booklet to
    //      the ArrayList.  It should return true if successful, false otherwise.
    public boolean addInstructionBooklet(InstructionBooklet instructionBooklet){
        return instructionBooklets.add(instructionBooklet);
    }

    // TODO listInstructionBooklets(): Add a method that will list all of the instruction booklets.  Each
    //      booklet should be on it's own line and should be preceeded with the index number in the array list e.g.
    //         0: InstructionBook1.pdf (6 pages)
    //         1: InstructionBook2.pdf (1 page)
    //         2: InstructionBook3.pdf (2 pages)
    public String listInstructionBooklets() {
        if (instructionBooklets.isEmpty()) {
            return "\n\tNo Instruction Booklets";
        } else {
            String listOfInstructionBooklets = "";
            for (int i = 0; i < instructionBooklets.size(); i++) {
                listOfInstructionBooklets += "\n\t" + i + ": " + instructionBooklets.get(i);
            }
            return listOfInstructionBooklets;
        }
    }

    //TODO isValidIndex(int): Add a method that will return true if the value of the index (passed as a
    //     parameter) is a valid index number in the instruction booklets array list.
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < instructionBooklets.size());
    }

    //TODO findInstructionBooklet(int): Add a method that will return the instruction booklet at a specific
    //     index in the array list.  If the index is invalid, null is returned instead.
    public InstructionBooklet findInstructionBooklet(int index) {
        if (isValidIndex(index)) {
            return instructionBooklets.get(index);
        }
        return null;
    }

    //TODO deleteInstructionBooklet(int): Add a method that will delete the instruction booklet at a specific
    //     index in the array list.  If the index is invalid, null is returned instead.
    public InstructionBooklet deleteInstructionBooklet(int index) {
        if (isValidIndex(index)) {
            return instructionBooklets.remove(index);
        }
        return null;
    }

    //TODO updateInstructionBooklet(int, String, int): Add a method that will take in three parameters:
    //     - The first parameter is the index of the instruction booklet in the arraylist.
    //     - The second parameter is the new value for the filename.
    //     - The third parameter is the new value for the number of pages.
    //     The method will update update the fileName and numberOfPages for an instruction booklet at a
    //     specific index in the array list.
    //     It will return true if the update was successful. If the index is invalid, false is returned instead.
    public boolean updateInstructionBooklet(int index, String fileName, int numberOfPages) {
        //find the object by the index number
        InstructionBooklet foundItem = findInstructionBooklet(index);

        //if the object exists, use the details passed in the updateDetails parameter to
        //update the found object in the ArrayList.
        if (foundItem != null) {
            foundItem.setFileName(fileName);
            foundItem.setNumberOfPages(numberOfPages);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }


}
