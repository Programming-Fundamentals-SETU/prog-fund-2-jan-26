package models;

import utils.LanguageUtility;
import utils.Utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * A scaled down version of a models.Product class
 *
 * @author Mairead Meagher, Siobhan Drohan
 * @version 3.0
 */
public class Product {

    private String productName = "";
    private int productCode = -1;
    private double unitCost = 0;
    private boolean inCurrentProductLine = false;
    private Map<String,String> productDescriptions = new HashMap<>();

    /**
     * Constructor for objects of class models.Product
     * @param productName Name of the product
     * @param productCode Code of the product
     * @param unitCost Unit cost of the product
     */
    public Product(String productName, int productCode, double unitCost, boolean inCurrentProductLine) {
        this.productName = Utilities.truncateString(productName, 20);
        setProductCode(productCode);
        this.unitCost = unitCost;
        this.inCurrentProductLine = inCurrentProductLine;
    }

    //-------
    //getters
    //-------
    /**
     * Returns the Product Name
     * @return productName
     */
    public String getProductName(){
        return productName;
    }

    /**
     * Returns the Unit Cost
     * @return unitCost
     */
    public double getUnitCost(){
        return unitCost;
    }

    /**
     * Returns the Product Code
     * @return productCode
     */
    public int getProductCode() {
        return productCode;
    }

    /**
     * Returns a boolean indicating if the product is in the current product line
     * @return inCurrentProductLine
     */
    public boolean isInCurrentProductLine() {
        return inCurrentProductLine;
    }

    //-------
    //setters
    //-------
    /**
     * Updates the Product Code to the value passed as a parameter
     * @param productCode The new Product Code
     */
    public void setProductCode(int productCode) {
        if (Utilities.validRange(productCode, 1000, 9999)) {
            this.productCode = productCode;
        }
    }

    /**
     * Updates the Product Name to the value passed as a parameter
     * @param productName The new Product Name
     */
    public void setProductName(String productName) {
        if (Utilities.validateStringLength(productName, 20)) {
            this.productName = productName;
        }
    }

    /**
     * Updates the Unit Cost to the value passed as a parameter
     * @param unitCost The new unit cost for the product
     */
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * Updates the boolean indicating whether the product is in the current product line or not.
     * @param inCurrentProductLine Indicator that determines if the product is in the current product line or not.
     */
    public void setInCurrentProductLine(boolean inCurrentProductLine) {
        this.inCurrentProductLine = inCurrentProductLine;
    }

    //-------------------
    // HashMap handling
    //-------------------

    /**
     * This method returns the productDescriptions Map
     *
     * @return productDescriptions Map (key: language, value: description)
     */
    public Map<String, String> getProductDescriptions() {
        return productDescriptions;
    }

    /**
     * This method sets the productDescriptions Map
     * @param productDescriptions The Map that will be used to overwrite the existing productDescriptions Map.
     */
    public void setProductDescriptions(Map<String, String> productDescriptions) {
        this.productDescriptions = productDescriptions;
    }

    /**
     * Returns the number of entries in the productDescriptions Map
     * @return The number of entries in the  productDescriptions Map
     */
    public int numberOfDescriptions(){
        return productDescriptions.size();
    }

    /**
     * This method accepts details of a new Map entry.  If the language, passed as a parameter:
     * <p>
     * is not valid, then the add is abandoned and false is returned.
     * is valid, but an entry already exists, the add is abandoned and false is returned.
     * is valid and no entry exists, then the add is performed and true is returned.
     * </p>
     * @param language  Key of a new entry in the productDescriptions map.
     * @param description Description of a new entry in the productDescriptions map.
     * @return whether the add was successful or not.
     */
   public boolean addDescription(String language, String description){
        if (isAlreadyAddedLanguage(language)) {
            return false;
        }
        else{
            if (LanguageUtility.isValidLanguage(language)) {
                productDescriptions.put(language, description);
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a String containing all of the entries in the productDescriptions map.  Each entry is on a
     * new line and is in the format key: value.
     *
     * @return  A string containing all the entries in the productDescriptions map.
     */
    public String listDescriptions() {
        if (numberOfDescriptions() > 0){
            String listToReturn = "";
            for(String language : productDescriptions.keySet()){
                listToReturn += language + ": " + productDescriptions.get(language) + ".\n";
            }
            return listToReturn;
        }
        return "No descriptions added yet";
    }

    /**
     * This method checks to see if the language, passed as a parameter, is already an entry in the
     * productDescriptions Map.  If it is, true is returned, otherwise false.
     *
     * @param language Language (already in uppercase) to check for existence in the Map
     * @return  If language exists, true is returned, otherwise false.
     */
    public boolean isAlreadyAddedLanguage(String language) {
        return (findDescription(language) != null);
    }

    /**
     * This method takes in a language as a parameter and returns the associated description, if it exists.
     *
     * @param language The language (key) to search the productDescriptions Map with
     * @return The description associated with the language, or null if the key doesn't exist.
     */
    public String findDescription(String language) {
        return productDescriptions.get(language);
    }

    /**
     * This method takes in a language as a parameter and deletes the entry in the productDescription Map, if it exists.
     * If it doesn't exist, null is returned.
     *
     * @param language The language (key) to search the productDescriptions Map with
     * @return The description associated with the language, or null if the language key doesn't exist.
     */
    public String deleteDescription(String language) {
        return productDescriptions.remove(language);
    }

    /**
     * This method takes in a language as a parameter and updates the description in the productDescription Map, if it
     * exists (returns true).  If it doesn't exist, false is returned.
     *
     * @param language The language (key) to search the productDescriptions Map with
     * @param updatedDescription The new description for the language
     * @return true if updated successfully or false if the language key doesn't exist
     */
    public boolean updateDescription(String language, String updatedDescription) {
        //if the language has been added to the map, use the details passed in the
        //updateDescription parameter to update the description.
        if (isAlreadyAddedLanguage(language)) {
            productDescriptions.put(language, updatedDescription);
            return true;
        }

        //if the language was not found, return false, indicating that the update was not successful.
        return false;
    }



    /**
     * Builds a String representing a user friendly representation of the object state
     * @return Details of the specific product
     */
    public String toString()
    {
        return "Product description: " + productName
                + ", product code: " + productCode
                + ", unit cost: " + unitCost
                + ", currently in product line: " + Utilities.booleanToYN(inCurrentProductLine);
    }

}