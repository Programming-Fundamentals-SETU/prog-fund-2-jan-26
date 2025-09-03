package models;

import utils.Utilities;

// Abstract class representing tablets, extending models.ComputingDevices
public  class Tablet extends ComputingDevice {
    private String operatingSystem = "Windows";

    // Constructor


    public Tablet(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage, String operatingSystem) {
        super(modelName, price, manufacturer, id, processor, storage);
        setOperatingSystem(operatingSystem);
    }

    // Getter and setter for operatingSystem
    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        if(utils.OperatingSystemUtility.isValidOperatingSystem(operatingSystem))
            this.operatingSystem=operatingSystem;
    }


    @Override
    public String toString() {
        return super.toString() +
                "\nOperating System: " + operatingSystem +
                ", Insurance Premium: €" + Utilities.toTwoDecimalPlaces(getInsurancePremium());

    }

    @Override
    public String connectToInternet() {
        return "Connects to the internet via Wi-Fi";
    }
    @Override
    public double getInsurancePremium() {
        return super.getPrice() * .01;
    }
}
