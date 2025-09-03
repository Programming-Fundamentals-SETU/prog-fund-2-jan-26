package models;

import utils.Utilities;

abstract public class CelestialBody {
    private int id;
    private static int nextId = 1;
    private String name; //max 30 chars
    private double mass = 0.1; //measured in ronnagrams earth is approx 6.0rg must be > 0.1 default to 0.1
    private double diameter = 0.5;  // measured in kilometres must be > 0.5 default to 0.5
    private PlanetarySystem planetarySystem;

    public CelestialBody(String name, double mass, double diameter, PlanetarySystem planetarySystem) {
        this.name = Utilities.truncateString(name, 30);
        setMass(mass);
        setDiameter(diameter);
        this.planetarySystem = planetarySystem;
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(Utilities.validStringlength(name, 30))
            this.name = name;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        if(Utilities.validRange(mass, 0.1, Double.MAX_VALUE ))
            this.mass = mass;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        if(Utilities.validRange(diameter, 0.1, Double.MAX_VALUE ))
            this.diameter = diameter;
    }


    public PlanetarySystem getPlanetarySystem() {
        return planetarySystem;
    }

    public void setPlanetarySystem(PlanetarySystem planetarySystem) {
        this.planetarySystem = planetarySystem;
    }

    public abstract String displayInfo();
    public abstract double calculateGravity();
    public abstract String classifyBody();



    @Override
    public String toString() {
        return "CelestialBody: " +
               "Id: " + id +
               ", Name: " + name  +
               ", Mass: " + mass +
               ", Diameter: " + diameter +
               ", PlanetarySystem: " + planetarySystem ;
    }
}

/*
Extra Information
A celestial body is any object that astronomers observe in outer space.
They are also sometimes referred to as "celestial objects", "astronomical objects", or "heavenly bodies."
This can include planets, stars, moons, asteroids, comets, or even nebulae and galaxies.

 */