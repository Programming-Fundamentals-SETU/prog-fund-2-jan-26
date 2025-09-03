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
    private int productCode = 9999;
    private double unitCost = 0;
    private boolean inCurrentProductLine = false;


    /**
     * Constructor for objects of class models.Product
     * @param productName Name of the product
     * @param productCode Code of the product
     * @param unitCost Unit cost of the product
     */
    public Product(String productName, int productCode, double unitCost, boolean inCurrentProductLine) {
        this.productName = Utilities.truncateString(productName, 20);
        setProductCode(productCode);
        setUnitCost(unitCost);
        this.inCurrentProductLine = inCurrentProductLine;
    }

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
        if (unitCost>= 0)
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