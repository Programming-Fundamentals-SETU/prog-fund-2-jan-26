package models;

import utils.Utilities;

public class ElectricCar extends Car{
    private float engineKWatts = 40;//40-kw to 60-kw
    private int range = 100; //100km up to 500km
    public ElectricCar(String regNumber, String make, float cost, Manufacturer manufacturer,  int year, int power, int secs0To60, int topSpeed, float torque,float engineKWatts, int range) {
        super(regNumber, make, cost, manufacturer, year, power, secs0To60, topSpeed, torque);
        setEngineKWatts(engineKWatts);
        setRange(range);
    }
    public  double getCarbonFootPrint(){
        //sample formula?: (engine kw * age of car) / load factor for electric car
        // (40000 * 6) / 20000 = 12
        return (engineKWatts * (super.getAge())) / 20000;
    }

    public float getEngineKWatts() {
        return engineKWatts;
    }

    public void setEngineKWatts(float engineKWatts) {
        if ((engineKWatts>=40) && (engineKWatts<=60))
           this.engineKWatts = engineKWatts;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        if (Utilities.validRange(range, 100,500))
            this.range = range;
    }

    @Override
    public String toString() {
        return "ElectricCar{" + super.toString()+
                "engineKWatts=" + engineKWatts +
                ", range=" + range +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricCar that)) return false;
        if (!super.equals(o)) return false;
        return Float.compare(that.engineKWatts, engineKWatts) == 0 && range == that.range;
    }


}
