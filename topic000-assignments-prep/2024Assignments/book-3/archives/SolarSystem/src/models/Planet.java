package models;

import models.CelestialBody;
import utils.Utilities;


// Second Level: Planetary Body
abstract class Planet extends CelestialBody {
    protected double averageTemperature = 0.0;// Average surface temperature in °C must be a value between -400 and 400 default 0
    protected String surfaceType; // () → e.g., "rocky", "volcanic", "metallic"  max 20 chars
    protected boolean hasLiquidWater = false ; //default false




    public Planet(String name, double mass, double diameter, PlanetarySystem planetarySystem, double averageTemperature, String surfaceType, boolean hasLiquidWater) {
        super(name, mass, diameter, planetarySystem);
        setAverageTemperature(averageTemperature);
        this.surfaceType = Utilities.truncateString(surfaceType, 20);
        this.hasLiquidWater = hasLiquidWater;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        if (Utilities.validRange(averageTemperature, -400, 400))
            this.averageTemperature = averageTemperature;
    }
    public String getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        if (Utilities.validStringlength(surfaceType, 20))
        this.surfaceType = surfaceType;
    }

    public boolean isHasLiquidWater() {
        return hasLiquidWater;
    }

    public void setHasLiquidWater(boolean hasLiquidWater) {
        this.hasLiquidWater = hasLiquidWater;
    }
    @Override
    public double calculateGravity() {
        return (getMass() * 6.67430e-11) / (Math.pow(getDiameter() / 2, 2));
    }

    @Override
    public String toString() {
        return "Planet: " + super.toString() +
                "Gravity: " + calculateGravity() +
                ", AverageTemperature: " + averageTemperature;
    }
}
