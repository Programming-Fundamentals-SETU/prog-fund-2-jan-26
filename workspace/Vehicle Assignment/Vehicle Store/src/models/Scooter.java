package models;

import utils.Utilities;

public class Scooter extends Vehicle {
    private int power =250; //250 -> 1000

    private float weight = 5.0F; //5 -> 100 kg
    private int topRiderWeight = 100; //100->120

    public Scooter(String regNumber, String  make, float cost, Manufacturer manufacturer, int  year, int power, float weight, int topRiderWeight) {
        super(regNumber, make, cost, manufacturer, year);
        setPower(power);
        setWeight(weight);
        setTopRiderWeight(topRiderWeight);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (Utilities.validRange(power,250,1000))
         this.power = power;
    }

    public int getTopRiderWeight() {
        return topRiderWeight;
    }

    public void setTopRiderWeight(int topRiderWeight) {
        if (Utilities.validRange(topRiderWeight, 100,120))
           this.topRiderWeight = topRiderWeight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        if (Utilities.validRange(weight, 5, 100, .01F))
            this.weight = weight;
    }

    @Override
    public double getCarbonFootPrint() {
        //sample formula?: (power * weight * age of scooter) / load factor for scoote(250 * 45 * 2) / 15000 = 1.5
        return (power * weight * super.getAge()) / 15000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scooter scooter)) return false;
        if (!super.equals(o)) return false;
        return power == scooter.power && topRiderWeight == scooter.topRiderWeight && Float.compare(scooter.weight, weight) == 0;
    }

    @Override
    public String toString() {
        return "Scooter{" +
                super.toString()+
                "power=" + power +
                ", top Rider Weight=" + topRiderWeight +
                ", Weight : " + weight +
                '}';
    }
}
