package models;

import utils.FuelTypeUtility;
import utils.Utilities;

import java.util.Objects;

public class CarbonFuelCar extends Car {
//    CarbonFuel extends Car (abstract):
//            - Fuel Type
//- Fuel Consumption (miles per gallon)
//- Carbon Emissions (note: zero for electric)
//- Engine Size (cc)
//- Transmission (manual or automatic - note: all electrics are automatic)

    private String fuelType = "petrol"; //Diesel or Petrol
    private float fuelConsumption = 5; //  in Litres/100K  valid 5 -> 20
    private float carbonEmission = 0.1f;  // >0
    private int engineSize = 800;//  800->2500
    private boolean automatic = false;

    public CarbonFuelCar(String regNumber, String make, float cost,   Manufacturer manufacturer,  int year, int power, int secs0To60, int topSpeed, float torque, String fuelType, float fuelConsumption, float carbonEmission, int engineSize, boolean automatic) {
        super(regNumber, make, cost, manufacturer, year, power, secs0To60, topSpeed, torque);
        setFuelType(fuelType);
        setFuelConsumption( fuelConsumption);
        setCarbonEmission(carbonEmission);
        setEngineSize( engineSize);
        this.automatic = automatic;
    }
    public  double getCarbonFootPrint(){
        //sample formula: (engine size * fuel consumption * carbon emission * age of car) / load factor for carbon car
        // (1200 * 6 * 5 * 6) / 2000 = 43.2
        return (engineSize * fuelConsumption * carbonEmission * (super.getAge())) / 2000;
    }
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        if (FuelTypeUtility.validFuelType(fuelType))
         this.fuelType = fuelType;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        if (Utilities.validRange(fuelConsumption, 5,20,.01F))
            this.fuelConsumption = fuelConsumption;
    }

    public float getCarbonEmission() {
        return carbonEmission;
    }

    public void setCarbonEmission(float carbonEmission) {

        if (carbonEmission >0)
            this.carbonEmission = carbonEmission;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        if (Utilities.validRange(engineSize, 800, 2500))
            this.engineSize = engineSize;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarbonFuelCar that)) return false;
        if (!super.equals(o)) return false;
        return Float.compare(that.fuelConsumption, fuelConsumption) == 0 && Float.compare(that.carbonEmission, carbonEmission) == 0 && engineSize == that.engineSize && automatic == that.automatic && Objects.equals(fuelType, that.fuelType);
    }

    @Override
    public String toString() {
        return "CarbonFuelCar{" + super.toString() + 
                "fuelType='" + fuelType + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", carbonEmission=" + carbonEmission +
                ", engineSize=" + engineSize +
                ", automatic=" + automatic +
                '}';
    }
}
