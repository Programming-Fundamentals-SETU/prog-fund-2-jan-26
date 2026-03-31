package models;

import utils.Utilities;

import java.util.Objects;

/**
 * A scaled down version of a Product class
 *
 * @author Siobhan Drohan, Mairead Meagher
 * @version 7.1
 */
public class Product {

    private String productName = "";
    private int productCode = -1;
    private double unitCost = 0;
    private boolean inCurrentProductLine = false;


    /**
     * Constructor for objects of class Product
     *
     * @param productName Name of the product, max 20 chars
     * @param productCode Code of the product, between 1000 and 999999 (both inclusive).  Default error value -1
     * @param unitCost Unit cost of the product, greater than 0 and less than or equal 99999.99.  Default value 0.
     */
    public Product(String productName, int productCode, double unitCost, boolean inCurrentProductLine) {
        this.productName = Utilities.truncateString(productName, 20);
        setProductCode(productCode);
        setUnitCost(unitCost);
        setInCurrentProductLine(inCurrentProductLine);
    }

    //-------
    //getters
    //-------
    /**
     * Returns the Product Name
     */
    public String getProductName(){
        return productName;
    }

    /**
     * Returns the Unit Cost
     */
    public double getUnitCost(){
        return unitCost;
    }

    /**
     * Returns the Product Code
     */
    public int getProductCode() {
        return productCode;
    }

    /**
     * Returns a boolean indicating if the product is in the current product line
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
        if (Utilities.validRange(productCode,1000, 999999)) {
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
        if (Utilities.validRangeExclIncl(unitCost, 0, 99999.99)){
            this.unitCost = unitCost;
        }
    }

    /**
     * Updates the boolean indicating whether the product is in the current product line or not.
     * @param inCurrentProductLine Indicator that determines if the product is in the current product line or not.
     */
    public void setInCurrentProductLine(boolean inCurrentProductLine) {
        this.inCurrentProductLine = inCurrentProductLine;
    }




    //-------------------
    // toString and equals methods
    //-------------------

    /**
     * This generated method checks whether the state of the passed object, o, is equal to
     * the current object.
     *
     * @param o The object to check against
     * @return True if the object state is equal and False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productCode == product.productCode && Double.compare(product.unitCost, unitCost) == 0 && inCurrentProductLine == product.inCurrentProductLine && Objects.equals(productName, product.productName);
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
