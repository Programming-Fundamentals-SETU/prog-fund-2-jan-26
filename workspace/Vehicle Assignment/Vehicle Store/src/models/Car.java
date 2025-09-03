package models;

import utils.Utilities;

//Car (abstract):
//        - Power (BHP)
//        - 0-60Mhp in seconds
//        - Top speed
//        - Torque
public abstract class Car extends Vehicle{
    int power = 120; // 120 - 300
    int secs0To60 = 25; //4 -> 25
    int topSpeed = 50; //50-> 300
    float torque = 100.0F; //100-> 400
    public Car(String regNumber, String make, float cost,   Manufacturer manufacturer,  int year, int power, int secs0To60, int topSpeed, float torque) {
        super(regNumber, make, cost, manufacturer, year);
        setPower( power) ;
        setSecs0To60(secs0To60);
        setTopSpeed(topSpeed);
        setTorque(torque);
    }

    public abstract double getCarbonFootPrint();

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (Utilities.validRange(power,120,300))
            this.power = power;
    }

    public int getSecs0To60() {
        return secs0To60;
    }

    public void setSecs0To60(int secs0To60) {
        if (Utilities.validRange(secs0To60, 4, 25))
             this.secs0To60 = secs0To60;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        if (Utilities.validRange(topSpeed, 50,300))
          this.topSpeed = topSpeed;
    }

    public float getTorque() {
        return torque;
    }

    public void setTorque(float torque) {
        if ((torque >= 100) && (torque <= 400))
          this.torque = torque;
    }

    @Override
    public String toString() {
        return "Car{" +
                super.toString() +
                "power=" + power +
                ", secs0To60=" + secs0To60 +
                ", topSpeed=" + topSpeed +
                ", torque=" + torque +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return power == car.power && secs0To60 == car.secs0To60 && topSpeed == car.topSpeed && Float.compare(car.torque, torque) == 0;
    }


}
