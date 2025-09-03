package models;


// Subclass of controllers.FitnessTracker for specific type of fitness tracker

import utils.Utilities;

// SmartBand class extending WearableDevices
public class SmartBand extends WearableDevice {
    private boolean heartRateMonitor;

    // Constructor


    public SmartBand(String modelName, double price, Manufacturer manufacturer, String id, String size, String material, boolean heartRateMonitor) {
        super(modelName, price, manufacturer, id, size, material);
        this.heartRateMonitor = heartRateMonitor;
    }

    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }

    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
    }



    @Override
    public String toString() {
        return super.toString()+
                (isHeartRateMonitor() ? ", Includes Heart Rate Monitor" : ", No Heart Rate Monitor included")+
                ", Insurance Premium: €" + Utilities.toTwoDecimalPlaces(getInsurancePremium());
    }

    @Override
    public String connectToInternet() {
        return "Connects to the internet via Companion App";
    }
    @Override
    public double getInsurancePremium(){
        return super.getPrice() * .07;
    }
}