package models;



// Third Level: models.Star
public class Star extends StellarObject {
    public Star(String name, double mass, double diameter, PlanetarySystem planetarySystem, char spectralType, double luminosity) {
        super(name, mass, diameter, planetarySystem, spectralType, luminosity);
    }

    @Override
    public String displayInfo() {
        return "Star: " + super.displayInfo();
    }

    @Override
    public double calculateGravity() {
        return (getMass() * 6.67430e-11) / (Math.pow(getDiameter() / 2, 2));
    }

    @Override
    public String classifyBody() {
        return "Star";
    }

    @Override
    public String toString() {
        return "Star: " + super.toString() +
               "SpectralType: " + getSpectralType() +
               ", luminosity: " + getLuminosity();
    }
}



