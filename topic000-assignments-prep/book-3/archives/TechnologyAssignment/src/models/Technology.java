package models;

import utils.Utilities;

// models.Technology class representing the base class
// Technology class representing the base class
public abstract class Technology  {
    private String modelName; //max 30 chars
    private double price = 20.0; // >=20 default to 20
    private Manufacturer manufacturer;
    private String id = "unknown"; //default unknown 10 chars or less
    // Constructor


    public Technology(String modelName, double price, Manufacturer manufacturer, String id) {
        this.modelName = Utilities.truncateString(modelName, 30);
        setPrice(price);
        this.manufacturer = manufacturer;
        setId(id);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(utils.Utilities.validStringlength(id, 10))
            this.id = id;
    }

    // Getters and setters
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if(utils.Utilities.validStringlength(modelName, 30))
            this.modelName = modelName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price>=20.0)
            this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    // Method to display information about the device including manufacturer details

    // Abstract method to connect to the internet
    public abstract String connectToInternet();
    public abstract double getInsurancePremium();
    @Override
    public String toString() {
        return "Model: " + modelName +
                ", Price: €" + Utilities.toTwoDecimalPlaces(price) +
                ", Manufacturer Details: " + manufacturer +
                ", ID: " + id;
    }
}