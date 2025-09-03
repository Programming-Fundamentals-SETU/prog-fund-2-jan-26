package models;

import utils.Utilities;

// SmartWatch class extending WearableDevices
public class SmartWatch extends WearableDevice {
    private String displayType = "LCD"; //default to LCD

    // Constructor


    public SmartWatch(String modelName, double price, Manufacturer manufacturer, String id, String size, String material, String displayType) {
        super(modelName, price, manufacturer, id, size, material);
        setDisplayType(displayType);
    }

    public String getDisplayType() {
        return displayType;
    }
//display type must "AMOLED" or "LCD" or "LED" or "TFT"
    public void setDisplayType(String displayType) {
        if(utils.DisplayTypeUtility.isValidDisplayType(displayType))
            this.displayType = displayType;
    }



    @Override
    public String toString() {
        return super.toString()+
                ", Display Type: " + displayType+
                ", Insurance Premium: €" + Utilities.toTwoDecimalPlaces(getInsurancePremium())+
                ", Internet Connection Details: " + connectToInternet();
    }

    @Override
    public String connectToInternet() {
        return "Connects to the internet via bluetooth";
    }

    @Override
    public double getInsurancePremium() {
        return super.getPrice()* .06;
    }
}