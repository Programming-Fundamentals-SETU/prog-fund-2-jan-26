package models;

import java.util.Objects;

public abstract class Bird extends Pet{
    private double wingSpan;
    private boolean canFly;

    public Bird(String name, int age, Owner owner, int id, double wingSpan, boolean canFly) {
        super(name, age, owner, id);
        this.wingSpan = wingSpan;
        this.canFly = canFly;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(double wingSpan) {
        this.wingSpan = wingSpan;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bird bird = (Bird) o;
        return Double.compare(wingSpan, bird.wingSpan) == 0 && canFly == bird.canFly;
    }

    @Override
    public String toString() {
        return "Bird: " + super.toString() +
                "WingSpan: " + wingSpan +
                ", canFly: " + (canFly == true ? "Yes" : "No") ;
    }
}
