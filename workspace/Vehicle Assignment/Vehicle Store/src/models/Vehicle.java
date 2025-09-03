package models;

import utils.Utilities;

import java.util.Objects;

public abstract  class Vehicle {

    private String regNumber = ""; // max 8 chars
    private String model = "";    // max 15 chars
    private float cost = 1000;   // >=1000

    private Manufacturer manufacturer;

    private int year = 2000;   // should be between and including 2000 and 2023 - default to 2000

    public Vehicle(String regNumber, String  model, float cost, Manufacturer manufacturer, int  year) {
        this.regNumber = Utilities.truncateString(regNumber,8);
        this.model = Utilities.truncateString(model,15);
        setCost(cost);
        this.manufacturer = manufacturer;
        setYear(year);
    }

    public abstract double getCarbonFootPrint();

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {

        if (Utilities.validStringlength(regNumber, 8))
            this.regNumber = regNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (Utilities.validStringlength(model,15))
          this.model = model;
    }
    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        if (cost >=1000)
            this.cost = cost;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (Utilities.validRange(year, 2000,2023))
            this.year = year;
    }

public int getAge() {
        return 2023-year;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Float.compare(vehicle.cost, cost) == 0 && year == vehicle.year && Objects.equals(regNumber, vehicle.regNumber) && Objects.equals(model, vehicle.model) && Objects.equals(manufacturer, vehicle.manufacturer);
    }


    @Override
    public String toString() {
        String ageStr = "";
        if (getAge() == 0 ) ageStr = "Brand New!";
        else if (getAge() == 1 )  ageStr = "1 year old";
        else ageStr = getAge() + " years old";
        return "Vehicle{" +
                "regNumber='" + regNumber + '\'' +
                ", model='" + model + '\'' +
                ", cost='" + cost + '\'' +
                ", manufacturer=" + manufacturer +
                ", age=" + ageStr +
                '}';
    }

}
